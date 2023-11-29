package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.domain.dto.FBDto;

import java.util.List;

public interface IQuantitativeStrategiesService {

    List<BaseKLineInfoDto> quantitativeStrategiesV1(String date);

    List<FBDto> quantitativeStrategiesV2(String date);
}
