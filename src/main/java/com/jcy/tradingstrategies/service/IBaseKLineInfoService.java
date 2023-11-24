package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.domain.dto.BaseKLineInfoDto;

import java.util.List;

public interface IBaseKLineInfoService {
    List<BaseKLineInfoDto> getBaseKLineInfo(String response);
}
