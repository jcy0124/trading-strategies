package com.jcy.tradingstrategies.business.service.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;
import org.aspectj.weaver.ast.Var;

import java.math.BigDecimal;

public class SSGPAdaptor {
    public static SSGPDto buildSSGPDto(JSONObject data) {
        SSGPDto ssgpDto = new SSGPDto();
        String name = data.getString("name");
        String current = data.getString("current");
        String buy5 = data.getString("buy5");
        String sell5 = data.getString("sell5");
        ssgpDto.setName(name);
        ssgpDto.setCurrent(new BigDecimal(current));
        ssgpDto.setBuy5(new BigDecimal(buy5));
        ssgpDto.setSell5(new BigDecimal(sell5));
        return ssgpDto;
    }

    public static SSGPDto buildSSGPDto(JSONObject data,String code) {
        SSGPDto ssgpDto = new SSGPDto();
        String name = data.getString("name");
        String current = data.getString("current");
        String buy5 = data.getString("buy5");
        String sell5 = data.getString("sell5");
        ssgpDto.setCode(code);
        ssgpDto.setName(name);
        ssgpDto.setCurrent(new BigDecimal(current));
        ssgpDto.setBuy5(new BigDecimal(buy5));
        ssgpDto.setSell5(new BigDecimal(sell5));
        return ssgpDto;
    }
}
