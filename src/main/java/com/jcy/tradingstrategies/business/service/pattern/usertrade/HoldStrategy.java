package com.jcy.tradingstrategies.business.service.pattern.usertrade;

import com.jcy.tradingstrategies.business.dao.UserTradeInfoDao;
import com.jcy.tradingstrategies.business.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.BaseKLineReq;
import com.jcy.tradingstrategies.business.domain.vo.req.UserTradeInfoReq;
import com.jcy.tradingstrategies.business.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.business.service.IUserTradeInfoService;
import com.jcy.tradingstrategies.business.service.adaptor.BaseKLineInfoAdaptor;
import com.jcy.tradingstrategies.business.service.adaptor.UserTradeInfoAdaptor;
import com.jcy.tradingstrategies.common.util.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HoldStrategy implements UserTradeInfoStrategy {

    @Autowired
    private UserTradeInfoDao userTradeInfoDao;

    @Autowired
    private IBaseKLineInfoService baseKLineInfoService;

    @Autowired
    @Lazy
    private IUserTradeInfoService userTradeInfoService;


    @Override
    public UserTradeInfoEntity addUserTradeInfo(UserTradeInfoReq req) {
        UserTradeInfoEntity lastInfo = userTradeInfoService.getLastCode(req);

        UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildHoldInfo(req);
        userTradeInfoEntity.setStockNumber(lastInfo.getStockNumber());
        userTradeInfoEntity.setBuyPrice(lastInfo.getBuyPrice());
        userTradeInfoEntity = computeTodayProfit(lastInfo, userTradeInfoEntity);

        userTradeInfoDao.insert(userTradeInfoEntity);
        return userTradeInfoEntity;
    }

    private UserTradeInfoEntity computeTodayProfit(UserTradeInfoEntity userTradeInfoLastDay, UserTradeInfoEntity userTradeInfoEntity) {
        BaseKLineReq req = BaseKLineInfoAdaptor.buildBaseKLineReq(userTradeInfoEntity.getCode(), userTradeInfoEntity.getDate());
        BaseKLineInfoDto kLine = baseKLineInfoService.getBaseKLineInfoByDay(req);
        BigDecimal change = kLine.getChange();

        String todayProfit = BigDecimalUtils.multiply(change, new BigDecimal(userTradeInfoLastDay.getStockNumber())).toString();

        userTradeInfoEntity.setTodayProfit(todayProfit);
        userTradeInfoEntity.setTodayClosePrice(kLine.getClose().toString());
        userTradeInfoEntity.setTotalProfit(BigDecimalUtils.add(new BigDecimal(userTradeInfoLastDay.getTotalProfit()), new BigDecimal(todayProfit)).toString());
        return userTradeInfoEntity;
    }
}