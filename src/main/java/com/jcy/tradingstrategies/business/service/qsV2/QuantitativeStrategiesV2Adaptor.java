package com.jcy.tradingstrategies.business.service.qsV2;

public class QuantitativeStrategiesV2Adaptor {

    public static QuantitativeStrategiesV2Dto buildQuantitativeStrategiesV2Dto(String code, String name,QuantitativeStrategiesV2Enum v2Enum){
        QuantitativeStrategiesV2Dto quantitativeStrategiesV2Dto = new QuantitativeStrategiesV2Dto();
        quantitativeStrategiesV2Dto.setCode(code);
        quantitativeStrategiesV2Dto.setName(name);
        quantitativeStrategiesV2Dto.setReason(v2Enum.getMsg());
        return quantitativeStrategiesV2Dto;
    }

}
