package com.jcy.tradingstrategies.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.domain.dto.RenQiDto;

public class RenQiAdaptor {
    public static RenQiDto buildRenQiDto(JSONObject jsonDetailInfo) {
        String code = jsonDetailInfo.getString("code");
        String name = jsonDetailInfo.getString("name");
        String changeRatio = jsonDetailInfo.getString("changeRatio");
        String price = jsonDetailInfo.getString("price");

        RenQiDto renQiDto = new RenQiDto();
        renQiDto.setCode(code);
        renQiDto.setName(name);
        renQiDto.setChangeRatio(changeRatio);
        renQiDto.setPrice(price);
        return renQiDto;
    }
}
