package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.business.domain.vo.req.BaseKLineReq;

import java.util.List;

public interface IBaseKLineInfoService {
    List<BaseKLineInfoDto> getBaseKLineInfo(String response);

    BaseKLineInfoDto getBaseKLineInfoByDay(BaseKLineReq req);
}