package com.jcy.tradingstrategies.business.service.adaptor;

import com.jcy.tradingstrategies.business.domain.dto.CommonDto;

public class CommonDtoAdaptor {

    public static CommonDto buildCommonDto(String code, String name){
        CommonDto commonDto = new CommonDto();
        commonDto.setCode(code);
        commonDto.setName(name);
        return commonDto;
    }

    public static CommonDto buildCommonDto(String code, String name, String nowZTDate, String lastZTDate){
        CommonDto commonDto = new CommonDto();
        commonDto.setCode(code);
        commonDto.setName(name);
        commonDto.setNowZTDate(nowZTDate);
        commonDto.setLastZTDate(lastZTDate);
        return commonDto;
    }
}
