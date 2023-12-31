package com.jcy.tradingstrategies.business.service.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.business.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.business.domain.vo.req.BaseKLineReq;

import java.math.BigDecimal;


public class BaseKLineInfoAdaptor {

    public static BaseKLineInfoDto buildBaseKLineInfo(JSONObject jsonDetailInfo) {
        String code = jsonDetailInfo.getString("code");
        String time = jsonDetailInfo.getString("time");
        String turnoverRatio = jsonDetailInfo.getString("turnoverRatio");
        BigDecimal amount = jsonDetailInfo.getBigDecimal("amount");
        BigDecimal totalCapital = jsonDetailInfo.getBigDecimal("totalCapital");
        BigDecimal avgPrice = jsonDetailInfo.getBigDecimal("avgPrice");
        BigDecimal change = jsonDetailInfo.getBigDecimal("change");
        BigDecimal totalShares = jsonDetailInfo.getBigDecimal("totalShares");
        BigDecimal high = jsonDetailInfo.getBigDecimal("high");
        BigDecimal low = jsonDetailInfo.getBigDecimal("low");
        String changeRatio = jsonDetailInfo.getString("changeRatio");
        BigDecimal close = jsonDetailInfo.getBigDecimal("close");
        BigDecimal open = jsonDetailInfo.getBigDecimal("open");

        code = code.substring(0, 6);
        BaseKLineInfoDto baseKLineInfoDto = new BaseKLineInfoDto();
        baseKLineInfoDto.setCode(code);
        baseKLineInfoDto.setTime(time);
        baseKLineInfoDto.setTurnoverRatio(turnoverRatio);
        baseKLineInfoDto.setAmount(amount);
        baseKLineInfoDto.setTotalCapital(totalCapital);
        baseKLineInfoDto.setAvgPrice(avgPrice);
        baseKLineInfoDto.setChange(change);
        baseKLineInfoDto.setTotalShares(totalShares);
        baseKLineInfoDto.setHigh(high);
        baseKLineInfoDto.setLow(low);
        baseKLineInfoDto.setChangeRatio(changeRatio);
        baseKLineInfoDto.setClose(close);
        baseKLineInfoDto.setOpen(open);
        return baseKLineInfoDto;

    }

    public static BaseKLineReq buildBaseKLineReq(String code,String date){
        BaseKLineReq baseKLineReq = new BaseKLineReq();
        baseKLineReq.setCode(code);
        baseKLineReq.setStartDate(date);
        baseKLineReq.setEndDate(date);
        baseKLineReq.setCalculationCycle("100");

        return baseKLineReq;
    }
}
