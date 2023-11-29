package com.jcy.tradingstrategies.adaptor;

import com.jcy.tradingstrategies.domain.dto.CommonDto;

public class CommonDtoAdaptor {

    public static CommonDto buildCommonDto(String code,String name){
        CommonDto commonDto = new CommonDto();
        commonDto.setCode(code);
        commonDto.setName(name);
        return commonDto;
    }
}
