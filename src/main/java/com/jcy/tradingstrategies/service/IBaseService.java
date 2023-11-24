package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.domain.req.BaseKLineReq;

public interface IBaseService {


    String getBaseKLineResp(BaseKLineReq req);

    String getZTPoolResp(String date);

    String getAllAStock();

    String getQsPoolResp(String date);
}









