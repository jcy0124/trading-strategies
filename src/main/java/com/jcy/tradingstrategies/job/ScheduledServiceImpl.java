package com.jcy.tradingstrategies.job;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;
import com.jcy.tradingstrategies.business.domain.entity.PriceWarningEntity;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.business.service.IPriceWarningService;
import com.jcy.tradingstrategies.business.service.adaptor.SSGPAdaptor;
import com.jcy.tradingstrategies.common.constant.BaseConstant;
import com.jcy.tradingstrategies.common.util.BigDecimalUtils;
import com.jcy.tradingstrategies.common.util.DateUtil;
import com.jcy.tradingstrategies.common.util.JOptionPaneUtil;
import com.jcy.tradingstrategies.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScheduledServiceImpl implements IScheduledService {

    @Autowired
    private IPriceWarningService priceWarningService;

    @Autowired
    private IBaseService iBaseService;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    @Scheduled(cron = "0/10 0-59 9,10,11 ? * 1-5 ")
    public void lowWarningMorning() {
        String time = DateUtil.getTime();
        if (time.compareTo("11:30:00") >= 0 || time.compareTo("09:30:00") <= 0) {
            return;
        }
        log.info("发起定时任务，执行上午预警");
        List<PriceWarningEntity> list = lowWarning();

        buildMessage(list);

        log.info("结束定时任务，执行上午预警");
    }

    @Override
    @Scheduled(cron = "0/10 0-59 13,14 ? * 1-5 ")
//    @Scheduled(cron = "0/5 * * * * ? ")
    public void lowWarningAfternoon() {
        log.info("发起定时任务，执行下午预警");
        List<PriceWarningEntity> list = lowWarning();

        buildMessage(list);

        log.info("结束定时任务，执行下午预警");
    }

    private void buildMessage(List<PriceWarningEntity> list) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }

        List<String> msg = list.stream().map(item -> {
            StringBuilder sb = new StringBuilder();
            String code = item.getCode();
            String name = item.getName();
            String reason = item.getReason();
            BigDecimal priceLimitWarning = item.getPriceLimitWarning();
            return sb.append(code).append("   ").append(name).append("   ").append(priceLimitWarning).append("   ").append(reason).toString();
        }).collect(Collectors.toList());

        executor.execute(()->{
            JOptionPaneUtil.showMessageDialogWithList("预警", msg);
        });
    }

    private List<PriceWarningEntity> lowWarning() {

        List<PriceWarningEntity> priceWarningEntityList = priceWarningService.selectNoAlertList();

        if (CollectionUtil.isEmpty(priceWarningEntityList)) {
            return null;
        }

        CountDownLatch countDownLatch = new CountDownLatch(priceWarningEntityList.size());
        Map<String, SSGPDto> ssgpDtoMap = new HashMap<>();

        long t1 = System.currentTimeMillis();
        log.info("多线程发起http同步请求，需同步数量：{}", priceWarningEntityList.size());

        // 设置每秒最多允许5个请求
        RateLimiter rateLimiter = RateLimiter.create(5.0);

        for (PriceWarningEntity priceWarningEntity : priceWarningEntityList) {
            executor.submit(() -> {
                rateLimiter.acquire();
                String code = priceWarningEntity.getCode();
                try {
                    String response = iBaseService.getSSJGResp(code);
                    JSONObject data = JsonUtil.getData(response, BaseConstant.SSGP);
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
        log.info("多线程结束http同步请求，同步时间：{}，同步数量：{}", (t2 - t1), ssgpDtoMap.size());

        List<PriceWarningEntity> result = new ArrayList<>();


        for (PriceWarningEntity priceWarningEntity : priceWarningEntityList) {
            String code = null;
            BigDecimal priceLimitWarning = null;
            BigDecimal current = null;
            BigDecimal buy5 = null;
            BigDecimal sell5 = null;
            try {
                code = priceWarningEntity.getCode();
                priceLimitWarning = priceWarningEntity.getPriceLimitWarning();
                SSGPDto ssgpDto = ssgpDtoMap.get(code);
                current = ssgpDto.getCurrent();
                buy5 = ssgpDto.getBuy5();
                sell5 = ssgpDto.getSell5();
            } catch (Exception e) {
                log.error("【{}】执行同步线程发生异常，跳过本次运行", code);
                continue;
            }

            priceWarningService.updateCurrent(priceWarningEntity.getId(), current);

            if (BigDecimalUtils.compare(current, priceLimitWarning) == 0) {
                log.info("【{}】出发预警，预警价格：【{}】", code, priceLimitWarning);
                result.add(priceWarningEntity);
                priceWarningService.updateIsAlert(priceWarningEntity);
                continue;
            }

            //向下预警
            if (BigDecimalUtils.compare(current, priceLimitWarning) > 0 && BigDecimalUtils.compare(buy5, priceLimitWarning) <= 0) {
                log.info("【{}】出发预警，预警价格：【{}】", code, priceLimitWarning);
                result.add(priceWarningEntity);
                priceWarningService.updateIsAlert(priceWarningEntity);
                continue;
            }

            //向上预警
            if (BigDecimalUtils.compare(current, priceLimitWarning) < 0 && BigDecimalUtils.compare(sell5, priceLimitWarning) >= 0) {
                log.info("【{}】出发预警，预警价格：【{}】", code, priceLimitWarning);
                result.add(priceWarningEntity);
                priceWarningService.updateIsAlert(priceWarningEntity);
                continue;
            }
        }


        return result;
    }


}


















