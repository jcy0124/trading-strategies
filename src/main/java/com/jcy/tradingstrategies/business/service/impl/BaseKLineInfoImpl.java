package com.jcy.tradingstrategies.business.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.business.dao.BaseKLineInfoDao;
import com.jcy.tradingstrategies.business.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.business.domain.vo.req.BaseKLineReq;
import com.jcy.tradingstrategies.business.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.business.service.adaptor.BaseKLineInfoAdaptor;
import com.jcy.tradingstrategies.common.constant.BaseConstant;
import com.jcy.tradingstrategies.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class BaseKLineInfoImpl implements IBaseKLineInfoService {

    @Autowired
    private BaseKLineInfoDao baseKLineInfoDao;

    @Autowired
    private IBaseService baseService;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Override
    public List<BaseKLineInfoDto> getBaseKLineInfo(String response) {

        JSONArray data = JsonUtil.getData(response, BaseConstant.KXXX);

        List<BaseKLineInfoDto> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            BaseKLineInfoDto baseKLineInfoDto = BaseKLineInfoAdaptor.buildBaseKLineInfo(jsonDetailInfo);
            list.add(baseKLineInfoDto);
        }

        return list;
    }

    @Override
    public BaseKLineInfoDto getBaseKLineInfoByDay(BaseKLineReq req) {
        String resp = baseService.getBaseKLineResp(req);
        JSONArray data = JsonUtil.getData(resp, BaseConstant.KXXX);

        BaseKLineInfoDto baseKLineInfoDto = null;

        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            baseKLineInfoDto = BaseKLineInfoAdaptor.buildBaseKLineInfo(jsonDetailInfo);
        }

        return baseKLineInfoDto;
    }

    @Override
    public Map<String, BaseKLineInfoDto> getBaseKLineMap(List<String> codeList, String date) {

        CountDownLatch countDownLatch = new CountDownLatch(codeList.size());
        Map<String, BaseKLineInfoDto> result = new HashMap<>();

        long t1 = System.currentTimeMillis();
        log.info("多线程发起http同步请求，需同步数量：{}", codeList.size());

        for (String code : codeList) {
            executor.submit(() -> {

                try {
                    BaseKLineInfoDto kLine = getKLine(code, date);
                    result.put(code, kLine);
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
        log.info("多线程结束http同步请求，同步时间：{}，同步数量：{}", (t2 - t1), result.size());
        if (codeList.size() != result.size()) {
            log.error("多线程同步http请求数量发生异常，重新发起请求");
            return getBaseKLineMap(codeList, date);
        }
        return result;
    }

    private BaseKLineInfoDto getKLine(String code, String date) {
        BaseKLineReq reqNext = BaseKLineInfoAdaptor.buildBaseKLineReq(code, date);
        return getBaseKLineInfoByDay(reqNext);
    }
}