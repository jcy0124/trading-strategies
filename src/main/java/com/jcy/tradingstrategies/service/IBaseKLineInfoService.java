package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.domain.vo.req.BaseKLineReq;

import java.util.List;

public interface IBaseKLineInfoService {
    List<BaseKLineInfoDto> getBaseKLineInfo(String response);

    BaseKLineInfoDto getBaseKLineInfoByDay(BaseKLineReq req);
}
