package com.jcy.tradingstrategies.business.service.adaptor;

import com.alibaba.fastjson.JSONArray;
import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;

import java.math.BigDecimal;

public class SSGPAdaptor {
    public static SSGPDto buildSSGPDto(JSONArray data) {
        SSGPDto ssgpDto = new SSGPDto();
        String current = data.getJSONObject(0).getString("current");
        String buy5 = data.getJSONObject(0).getString("buy5");
        String sell5 = data.getJSONObject(0).getString("sell5");
        ssgpDto.setBuy5(new BigDecimal(buy5));
        ssgpDto.setSell5(new BigDecimal(sell5));
        return ssgpDto;
    }
}
