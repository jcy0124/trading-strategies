package com.jcy.tradingstrategies.service.qsV2.strategy;

import com.jcy.tradingstrategies.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.service.qsV2.QuantitativeStrategiesV2Dto;

public interface Strategy {

    QuantitativeStrategiesV2Dto doStrategy(ZTPoolDto ztPoolDto);
}
