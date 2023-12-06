package com.jcy.tradingstrategies.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.constant.ChangeMarkEnum;
import com.jcy.tradingstrategies.domain.dto.FSCJLDto;

public class FSCJLAdaptor {
    public static FSCJLDto buildFSCJLDto(JSONObject jsonDetailInfo) {
        String time = jsonDetailInfo.getString("time");
        String price = jsonDetailInfo.getString("price");
        String shoushu = jsonDetailInfo.getString("shoushu");
        String bsbz = jsonDetailInfo.getString("bsbz");

        FSCJLDto fscjlDto = new FSCJLDto();
        fscjlDto.setTime(time);
        fscjlDto.setPrice(price);
        fscjlDto.setShoushu(shoushu);
        fscjlDto.setBsbz(ChangeMarkEnum.of(bsbz).getMsg());
        return fscjlDto;
    }
}
