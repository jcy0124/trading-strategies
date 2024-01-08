package com.jcy.tradingstrategies.business.service.adaptor;

import com.jcy.tradingstrategies.business.domain.dto.FBDto;

public class FBAdaptor {

    public static FBDto buildFBDto(String code,String name){
        FBDto fbDto = new FBDto();
        fbDto.setCode(code);
        fbDto.setName(name);
        return fbDto;
    }
}
