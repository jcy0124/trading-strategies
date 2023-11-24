package tradingstrategies.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.entity.BaseKLineInfoEntity;

import java.math.BigDecimal;


public class BaseKLineInfoAdaptor {

    public static BaseKLineInfoEntity buildBaseKLineInfo(JSONObject jsonDetailInfo) {
        String code = jsonDetailInfo.getString("code");
        String time = jsonDetailInfo.getString("time");
        BigDecimal turnoverRatio = jsonDetailInfo.getBigDecimal("turnoverRatio");
        BigDecimal amount = jsonDetailInfo.getBigDecimal("amount");
        BigDecimal totalCapital = jsonDetailInfo.getBigDecimal("totalCapital");
        BigDecimal avgPrice = jsonDetailInfo.getBigDecimal("avgPrice");
        BigDecimal change = jsonDetailInfo.getBigDecimal("change");
        BigDecimal totalShares = jsonDetailInfo.getBigDecimal("totalShares");
        BigDecimal high = jsonDetailInfo.getBigDecimal("high");
        BigDecimal low = jsonDetailInfo.getBigDecimal("low");
        BigDecimal changeRatio = jsonDetailInfo.getBigDecimal("changeRatio");
        BigDecimal close = jsonDetailInfo.getBigDecimal("close");
        BigDecimal open = jsonDetailInfo.getBigDecimal("open");

        code = code.substring(0,6);
        BaseKLineInfoEntity baseKLineInfoEntity = new BaseKLineInfoEntity();
        baseKLineInfoEntity.setCode(code);
        baseKLineInfoEntity.setTime(time);
        baseKLineInfoEntity.setTurnoverRatio(turnoverRatio);
        baseKLineInfoEntity.setAmount(amount);
        baseKLineInfoEntity.setTotalCapital(totalCapital);
        baseKLineInfoEntity.setAvgPrice(avgPrice);
        baseKLineInfoEntity.setChange(change);
        baseKLineInfoEntity.setTotalShares(totalShares);
        baseKLineInfoEntity.setHigh(high);
        baseKLineInfoEntity.setLow(low);
        baseKLineInfoEntity.setChangeRatio(changeRatio);
        baseKLineInfoEntity.setClose(close);
        baseKLineInfoEntity.setOpen(open);
        return baseKLineInfoEntity;

    }
}
