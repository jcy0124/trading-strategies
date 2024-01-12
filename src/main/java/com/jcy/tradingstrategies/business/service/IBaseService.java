package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.vo.req.BaseKLineReq;

public interface IBaseService {


    String getBaseKLineResp(BaseKLineReq req);

    String getZTPoolResp(String date);

    String getAllAStock();

    String getRenQiPoolResp();

    String getSSJGResp(String code);
}









