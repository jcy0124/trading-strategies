package com.jcy.tradingstrategies.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.entity.QSPoolEntity;

import java.math.BigDecimal;

public class QSPoolAdaptor {

    public static QSPoolEntity buildQSPoolEntity(JSONObject jsonDetailInfo) {
        String code = jsonDetailInfo.getString("code");
        String name = jsonDetailInfo.getString("name");
        BigDecimal changeRatio = jsonDetailInfo.getBigDecimal("changeRatio");
        BigDecimal lastPrice = jsonDetailInfo.getBigDecimal("lastPrice");
        BigDecimal amount = jsonDetailInfo.getBigDecimal("amount");
        BigDecimal flowCapital = jsonDetailInfo.getBigDecimal("flowCapital");
        BigDecimal totalCapital = jsonDetailInfo.getBigDecimal("totalCapital");
        BigDecimal turnoverRatio = jsonDetailInfo.getBigDecimal("turnoverRatio");
        String zttj = jsonDetailInfo.getString("zttj");
        String zs = jsonDetailInfo.getString("zs");
        String nh = jsonDetailInfo.getString("nh");
        String lb = jsonDetailInfo.getString("lb");
        String industry = jsonDetailInfo.getString("industry");
        String time = jsonDetailInfo.getString("time");
        String gl = jsonDetailInfo.getString("gl");

        QSPoolEntity qsPoolEntity = new QSPoolEntity();
        qsPoolEntity.setCode(code);
        qsPoolEntity.setName(name);
        qsPoolEntity.setChangeRatio(changeRatio);
        qsPoolEntity.setLastPrice(lastPrice);
        qsPoolEntity.setAmount(amount);
        qsPoolEntity.setFlowCapital(flowCapital);
        qsPoolEntity.setTotalCapital(totalCapital);
        qsPoolEntity.setTurnoverRatio(turnoverRatio);
        qsPoolEntity.setZttj(zttj);
        qsPoolEntity.setZs(zs);
        qsPoolEntity.setNh(nh);
        qsPoolEntity.setLb(lb);
        qsPoolEntity.setIndustry(industry);
        qsPoolEntity.setTime(time);
        qsPoolEntity.setGl(gl);
        return qsPoolEntity;
    }
}
