package com.jcy.tradingstrategies.service.adaptor;

import com.jcy.tradingstrategies.domain.dto.FBDto;

public class FBAdaptor {

    public static FBDto buildFBDto(String code,String name){
        FBDto fbDto = new FBDto();
        fbDto.setCode(code);
        fbDto.setName(name);
        return fbDto;
    }
}
