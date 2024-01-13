package com.jcy.tradingstrategies.business.service.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;

import java.math.BigDecimal;

public class SSGPAdaptor {
    public static SSGPDto buildSSGPDto(JSONObject data) {
        SSGPDto ssgpDto = new SSGPDto();

        String buy5 = data.getString("buy5");
        String sell5 = data.getString("sell5");
        ssgpDto.setBuy5(new BigDecimal(buy5));
        ssgpDto.setSell5(new BigDecimal(sell5));
        return ssgpDto;
    }
}
