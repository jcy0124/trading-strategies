package com.jcy.tradingstrategies.service.qsV1;

import com.jcy.tradingstrategies.domain.dto.CommonDto;

import java.util.List;

public interface IQuantitativeStrategiesV1Service {

    List<CommonDto> quantitativeStrategiesV1(String date);
}
