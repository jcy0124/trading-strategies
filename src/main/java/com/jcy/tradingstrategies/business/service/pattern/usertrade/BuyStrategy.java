package com.jcy.tradingstrategies.business.service.pattern.usertrade;

import com.jcy.tradingstrategies.business.dao.UserTradeInfoDao;
import com.jcy.tradingstrategies.business.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.BaseKLineReq;
import com.jcy.tradingstrategies.business.domain.vo.req.UserTradeInfoReq;
import com.jcy.tradingstrategies.business.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.business.service.IUserInfoService;
import com.jcy.tradingstrategies.business.service.adaptor.BaseKLineInfoAdaptor;
import com.jcy.tradingstrategies.business.service.adaptor.UserTradeInfoAdaptor;
import com.jcy.tradingstrategies.common.util.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BuyStrategy implements UserTradeInfoStrategy {

    public static final int PROFIT_LOSS_RATIO = 3;

    @Autowired
    private UserTradeInfoDao userTradeInfoDao;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IBaseKLineInfoService baseKLineInfoService;

    @Override
    public UserTradeInfoEntity addUserTradeInfo(UserTradeInfoReq req) {
        UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildBuyInfo(req);
        computeTodayProfit(userTradeInfoEntity);
        computeLossPrice(userTradeInfoEntity);
        computeProfitPoint(userTradeInfoEntity);
        userTradeInfoDao.insert(userTradeInfoEntity);
        return userTradeInfoEntity;
    }

    //计算当日盈亏
    private void computeTodayProfit(UserTradeInfoEntity userTradeInfoEntity) {
        BigDecimal buyPrice = new BigDecimal(userTradeInfoEntity.getBuyPrice());

        BaseKLineReq req = BaseKLineInfoAdaptor.buildBaseKLineReq(userTradeInfoEntity.getCode(), userTradeInfoEntity.getDate());
        BaseKLineInfoDto kLine = baseKLineInfoService.getBaseKLineInfoByDay(req);

        BigDecimal close = kLine.getClose();

        BigDecimal subtract = BigDecimalUtils.subtract(close, buyPrice);
        String todayProfit = BigDecimalUtils.multiply(subtract, new BigDecimal(userTradeInfoEntity.getStockNumber())).toString();

        userTradeInfoEntity.setTodayProfit(todayProfit);
        userTradeInfoEntity.setTodayClosePrice(close.toString());
        userTradeInfoEntity.setTotalProfit(todayProfit);
    }

    //计算止损点
    private void computeLossPrice(UserTradeInfoEntity userTradeInfoEntity) {
        BigDecimal lossPoint = new BigDecimal(userTradeInfoEntity.getLossPoint());
        BigDecimal buyPrice = new BigDecimal(userTradeInfoEntity.getBuyPrice());
        BigDecimal stockNumber = new BigDecimal(userTradeInfoEntity.getStockNumber());

        BigDecimal subtract = BigDecimalUtils.subtract(buyPrice, lossPoint);
        BigDecimal lossPrice = BigDecimalUtils.multiply(subtract, stockNumber);

        BigDecimal lossRatio = BigDecimalUtils.multiply(BigDecimalUtils.divide(BigDecimalUtils.subtract(buyPrice, lossPoint), buyPrice), new BigDecimal(100));

        userTradeInfoEntity.setLossPrice(lossPrice.toString());
        userTradeInfoEntity.setLossRatio(lossRatio.toString().substring(0, 4) + "%");
    }

    //计算止盈点
    private void computeProfitPoint(UserTradeInfoEntity userTradeInfoEntity) {
        BigDecimal lossPoint = new BigDecimal(userTradeInfoEntity.getLossPoint());
        BigDecimal buyPrice = new BigDecimal(userTradeInfoEntity.getBuyPrice());

        BigDecimal subtract = BigDecimalUtils.subtract(buyPrice, lossPoint);
        BigDecimal multiply = BigDecimalUtils.multiply(subtract, new BigDecimal(PROFIT_LOSS_RATIO));

        BigDecimal profitPoint = BigDecimalUtils.add(buyPrice, multiply);

        BigDecimal profitPrice = BigDecimalUtils.multiply(new BigDecimal(userTradeInfoEntity.getStockNumber()), BigDecimalUtils.subtract(profitPoint, buyPrice));

        BigDecimal profitRatio = BigDecimalUtils.multiply(BigDecimalUtils.divide(BigDecimalUtils.subtract(profitPoint, buyPrice), buyPrice), new BigDecimal(100));


        userTradeInfoEntity.setProfitPoint(profitPoint.toString());
        userTradeInfoEntity.setProfitRatio(profitRatio.toString().substring(0, 4) + "%");
        userTradeInfoEntity.setProfitPrice(profitPrice.toString());
    }
}