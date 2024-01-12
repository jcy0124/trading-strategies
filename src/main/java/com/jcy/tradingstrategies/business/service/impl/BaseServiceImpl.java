package com.jcy.tradingstrategies.business.service.impl;

import com.jcy.tradingstrategies.common.constant.UrlConstant;
import com.jcy.tradingstrategies.business.domain.vo.req.BaseKLineReq;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.common.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BaseServiceImpl implements IBaseService {

    @Override
    public String getBaseKLineResp(BaseKLineReq req) {
        String url = UrlConstant.BASE_K_LINE_URL;
        url = String.format(url, req.getCode(), req.getEndDate(), req.getStartDate(), req.getCalculationCycle());
        return HttpClientUtil.doGet(url, "UTF-8");
    }

    @Override
    public String getZTPoolResp(String date) {
        String url = UrlConstant.ZT_POOL_URL + date;
        return HttpClientUtil.doGet(url, "UTF-8");
    }

    @Override
    public String getAllAStock() {
        String url = UrlConstant.ALL_CODE_URL;
        return HttpClientUtil.doGet(url, "UTF-8");
    }

    @Override
    public String getRenQiPoolResp() {
        String url = UrlConstant.REN_QI_URL;
        return HttpClientUtil.doGet(url, "UTF-8");
    }

    @Override
    public String getSSJGResp(String code) {
        String url = UrlConstant.SSWT_URL;
        url = String.format(url,code);
        return HttpClientUtil.doGet(url, "UTF-8");
    }
}
























