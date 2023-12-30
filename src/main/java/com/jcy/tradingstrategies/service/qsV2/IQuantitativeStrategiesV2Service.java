package com.jcy.tradingstrategies.service.qsV2;

import com.jcy.tradingstrategies.domain.dto.CommonDto;

import java.util.List;

public interface IQuantitativeStrategiesV2Service {
    List<QuantitativeStrategiesV2Dto> quantitativeStrategiesV2(String startDate);
}
