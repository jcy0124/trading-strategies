package com.jcy.tradingstrategies.service.qsV2;

import com.jcy.tradingstrategies.domain.dto.CommonDto;

public class QuantitativeStrategiesV2Adaptor {

    public static QuantitativeStrategiesV2Dto buildQuantitativeStrategiesV2Dto(String code, String name,QuantitativeStrategiesV2Enum v2Enum){
        QuantitativeStrategiesV2Dto quantitativeStrategiesV2Dto = new QuantitativeStrategiesV2Dto();
        quantitativeStrategiesV2Dto.setCode(code);
        quantitativeStrategiesV2Dto.setName(name);
        quantitativeStrategiesV2Dto.setReason(v2Enum.getMsg());
        return quantitativeStrategiesV2Dto;
    }

}
