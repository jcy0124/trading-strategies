package com.jcy.tradingstrategies.job;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;
import com.jcy.tradingstrategies.business.domain.entity.LowWarningEntity;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.business.service.ILowWarningService;
import com.jcy.tradingstrategies.business.service.adaptor.SSGPAdaptor;
import com.jcy.tradingstrategies.common.constant.BaseConstant;
import com.jcy.tradingstrategies.common.util.BigDecimalUtils;
import com.jcy.tradingstrategies.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class ScheduledServiceImpl implements IScheduledService {

    @Autowired
    private ILowWarningService lowWarningService;

    @Autowired
    private IBaseService iBaseService;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Override
    @Scheduled(cron = "0/10 30/1 9,10,11 ? * 1-5 ")
    public void lowWarningMorning() {
        log.info("发起定时任务，执行上午最低预警");
        lowWarning();
        log.info("结束定时任务，执行上午最低预警");
    }

    @Override
    @Scheduled(cron = "0/10 0-59 13,14 ? * 1-5 ")
    public void lowWarningAfternoon() {
        log.info("发起定时任务，执行下午最低预警");
        lowWarning();
        log.info("结束定时任务，执行下午最低预警");
    }

    private void lowWarning() {

        List<LowWarningEntity> lowWarningEntityList = lowWarningService.selectNoAlertList();

        if (CollectionUtil.isEmpty(lowWarningEntityList)) {
            return;
        }

        CountDownLatch countDownLatch = new CountDownLatch(lowWarningEntityList.size());
        Map<String, SSGPDto> ssgpDtoMap = new HashMap<>();

        long t1 = System.currentTimeMillis();
        log.info("多线程发起http同步请求，需同步数量：{}", lowWarningEntityList.size());

        for (LowWarningEntity lowWarningEntity : lowWarningEntityList) {
            executor.submit(() -> {
                String code = lowWarningEntity.getCode();
                try {
                    String response = iBaseService.getSSJGResp(code);
                    JSONArray data = JsonUtil.getData(response, BaseConstant.SSGP);
                    SSGPDto ssgpDto = SSGPAdaptor.buildSSGPDto(data);
                    ssgpDtoMap.put(code, ssgpDto);
                } catch (Exception e) {
                    log.error("同步【{}】异常:", code, e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long t2 = System.currentTimeMillis();
        log.info("多线程结束http同步请求，同步时间：{}，同步数量：{}", (t2 - t1), lowWarningEntityList.size());

        List<String> codeResult = new ArrayList<>();

        for (LowWarningEntity lowWarningEntity : lowWarningEntityList) {
            String code = lowWarningEntity.getCode();
            BigDecimal lowLimitWarning = lowWarningEntity.getLowLimitWarning();
            SSGPDto ssgpDto = ssgpDtoMap.get(code);
            BigDecimal sell5 = ssgpDto.getSell5();
            if (BigDecimalUtils.compare(sell5, lowLimitWarning) <= 0) {
                log.info("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！【{}】出发预警", code);
                codeResult.add(code);
                lowWarningService.updateIsAlert(lowWarningEntity);
            }
        }
    }
}


















