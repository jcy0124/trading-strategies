package com.jcy.tradingstrategies.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.domain.dto.YouZiDto;

public class YouZiAdaptor {
    public static YouZiDto buildYouZiDto(JSONObject jsonDetailInfo) {
        String yzmc = jsonDetailInfo.getString("yzmc");
        String yyb = jsonDetailInfo.getString("yyb");
        String gpdm = jsonDetailInfo.getString("gpdm");
        String gpmc = jsonDetailInfo.getString("gpmc");
        String mrje = jsonDetailInfo.getString("mrje");
        String mcje = jsonDetailInfo.getString("mcje");
        String jlrje = jsonDetailInfo.getString("jlrje");
        String rq = jsonDetailInfo.getString("rq");
        String gl = jsonDetailInfo.getString("gl");

        YouZiDto youZiDto = new YouZiDto();
        youZiDto.setYzmc(yzmc);
        youZiDto.setYyb(yyb);
        youZiDto.setGpdm(gpdm);
        youZiDto.setGpmc(gpmc);
        youZiDto.setMrje(mrje);
        youZiDto.setMcje(mcje);
        youZiDto.setJlrje(jlrje);
        youZiDto.setRq(rq);
        youZiDto.setGl(gl);
        return youZiDto;
    }
}
