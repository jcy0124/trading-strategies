package com.jcy.tradingstrategies.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.domain.entity.ZTPoolEntity;
import com.jcy.tradingstrategies.domain.excel.ZTPoolExcel;
import com.jcy.tradingstrategies.util.DateUtil;

import java.math.BigDecimal;


public class ZTPoolAdaptor {

    public static ZTPoolEntity buildZTPoolEntity(JSONObject jsonDetailInfo) {
        String code = jsonDetailInfo.getString("code");
        String name = jsonDetailInfo.getString("name");
        String changeRatio = jsonDetailInfo.getString("changeRatio");
        BigDecimal lastPrice = jsonDetailInfo.getBigDecimal("lastPrice");
        BigDecimal amount = jsonDetailInfo.getBigDecimal("amount");
        BigDecimal flowCapital = jsonDetailInfo.getBigDecimal("flowCapital");
        BigDecimal totalCapital = jsonDetailInfo.getBigDecimal("totalCapital");
        String turnoverRatio = jsonDetailInfo.getString("turnoverRatio");
        BigDecimal ceilingAmount = jsonDetailInfo.getBigDecimal("ceilingAmount");
        String firstCeilingTime = jsonDetailInfo.getString("firstCeilingTime");
        String lastCeilingTime = jsonDetailInfo.getString("lastCeilingTime");
        Integer bombNum = jsonDetailInfo.getInteger("bombNum");
        Integer lbNum = jsonDetailInfo.getInteger("lbNum");
        String industry = jsonDetailInfo.getString("industry");
        String time = jsonDetailInfo.getString("time");
        String gl = jsonDetailInfo.getString("gl");
        String stockReason = jsonDetailInfo.getString("stockReason");
        String plateReason = jsonDetailInfo.getString("plateReason");
        String plateName = jsonDetailInfo.getString("plateName");

        ZTPoolEntity ztPoolEntity = new ZTPoolEntity();
        ztPoolEntity.setCode(code);
        ztPoolEntity.setName(name);
        ztPoolEntity.setChangeRatio(changeRatio);
        ztPoolEntity.setLastPrice(lastPrice);
        ztPoolEntity.setAmount(amount);
        ztPoolEntity.setFlowCapital(flowCapital);
        ztPoolEntity.setTotalCapital(totalCapital);
        ztPoolEntity.setTurnoverRatio(turnoverRatio);
        ztPoolEntity.setCeilingAmount(ceilingAmount);
        ztPoolEntity.setFirstCeilingTime(firstCeilingTime);
        ztPoolEntity.setLastCeilingTime(lastCeilingTime);
        ztPoolEntity.setBombNum(bombNum);
        ztPoolEntity.setLbNum(lbNum);
        ztPoolEntity.setIndustry(industry);
        ztPoolEntity.setTime(time);
        ztPoolEntity.setGl(gl);
        ztPoolEntity.setStockReason(stockReason);
        ztPoolEntity.setPlateReason(plateReason);
        ztPoolEntity.setPlateName(plateName);
        return ztPoolEntity;
    }

    public static ZTPoolExcel buildZTPoolExcel(ZTPoolExcel ztPoolExcel){
        String turnoverRatio = ztPoolExcel.getTurnoverRatio();
        turnoverRatio = turnoverRatio.substring(0, 5) + "%";
        ztPoolExcel.setTurnoverRatio(turnoverRatio);

        String firstCeilingTime = ztPoolExcel.getFirstCeilingTime();
        firstCeilingTime = DateUtil.convertToTimeFormat(firstCeilingTime);
        ztPoolExcel.setFirstCeilingTime(firstCeilingTime);

        String lastCeilingTime = ztPoolExcel.getLastCeilingTime();
        lastCeilingTime = DateUtil.convertToTimeFormat(lastCeilingTime);
        ztPoolExcel.setLastCeilingTime(lastCeilingTime);

        return ztPoolExcel;

    }


}






























