package com.jcy.tradingstrategies.business.service.pattern.usertrade;

import com.jcy.tradingstrategies.business.dao.UserTradeInfoDao;
import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.UserTradeInfoReq;
import com.jcy.tradingstrategies.business.service.IUserTradeInfoService;
import com.jcy.tradingstrategies.business.service.adaptor.UserTradeInfoAdaptor;
import com.jcy.tradingstrategies.common.util.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SellStrategy implements UserTradeInfoStrategy {

    @Autowired
    private UserTradeInfoDao userTradeInfoDao;

    @Autowired
    @Lazy
    private IUserTradeInfoService userTradeInfoService;

    @Override
    public UserTradeInfoEntity addUserTradeInfo(UserTradeInfoReq req) {
        UserTradeInfoEntity lastInfo = userTradeInfoService.getLastCode(req);
        UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildSellInfo(req);

        userTradeInfoEntity.setBuyPrice(userTradeInfoEntity.getBuyPrice());
        userTradeInfoEntity = computeTotalProfit(lastInfo, userTradeInfoEntity);

        userTradeInfoDao.insert(userTradeInfoEntity);
        userTradeInfoDao.updateFinishFlag(userTradeInfoEntity);
        return userTradeInfoEntity;
    }

    private UserTradeInfoEntity computeTotalProfit(UserTradeInfoEntity userTradeInfoInTable, UserTradeInfoEntity userTradeInfoEntity) {
        BigDecimal sellPrice = new BigDecimal(userTradeInfoEntity.getSellPrice());
        BigDecimal buyPrice = new BigDecimal(userTradeInfoInTable.getBuyPrice());

        BigDecimal subtract = BigDecimalUtils.subtract(sellPrice, buyPrice);
        String totalProfit = BigDecimalUtils.multiply(subtract, new BigDecimal(userTradeInfoEntity.getStockNumber())).toString();

        userTradeInfoEntity.setTodayProfit(BigDecimalUtils.multiply(BigDecimalUtils.subtract(sellPrice, new BigDecimal(userTradeInfoInTable.getTodayClosePrice())), new BigDecimal(userTradeInfoEntity.getStockNumber())).toString());
        userTradeInfoEntity.setTotalProfit(totalProfit);
        return userTradeInfoEntity;
    }
}