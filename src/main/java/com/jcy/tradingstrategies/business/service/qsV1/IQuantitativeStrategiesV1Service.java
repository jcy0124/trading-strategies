package com.jcy.tradingstrategies.business.service.qsV1;

import com.jcy.tradingstrategies.business.domain.dto.CommonDto;

import java.util.List;

public interface IQuantitativeStrategiesV1Service {

    List<CommonDto> quantitativeStrategiesV1(String date);
}
