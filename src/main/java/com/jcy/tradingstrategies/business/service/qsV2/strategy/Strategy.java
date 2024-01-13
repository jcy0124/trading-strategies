package com.jcy.tradingstrategies.business.service.qsV2.strategy;

import com.jcy.tradingstrategies.business.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.business.service.qsV2.QuantitativeStrategiesV2Dto;

public interface Strategy {

    QuantitativeStrategiesV2Dto doStrategy(ZTPoolDto ztPoolDto, BaseKLineInfoDto secondKLine,BaseKLineInfoDto thirdKLine);
}
