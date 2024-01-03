package com.jcy.tradingstrategies.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.dao.UserTradeInfoDao;
import com.jcy.tradingstrategies.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.domain.enums.CodeStatusEnum;
import com.jcy.tradingstrategies.domain.vo.req.BaseKLineReq;
import com.jcy.tradingstrategies.domain.vo.req.UserTradeInfoReq;
import com.jcy.tradingstrategies.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.service.IUserInfoService;
import com.jcy.tradingstrategies.service.IUserTradeInfoService;
import com.jcy.tradingstrategies.service.adaptor.BaseKLineInfoAdaptor;
import com.jcy.tradingstrategies.service.adaptor.UserTradeInfoAdaptor;
import com.jcy.tradingstrategies.util.BigDecimalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class UserTradeInfoServiceImpl implements IUserTradeInfoService {

    public static final int PROFIT_LOSS_RATIO = 3;
    @Autowired
    private UserTradeInfoDao userTradeInfoDao;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IBaseKLineInfoService baseKLineInfoService;


    @Override
    public List<UserTradeInfoEntity> getAll() {
        LambdaQueryWrapper<UserTradeInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserTradeInfoEntity::getUserName, "3w小姜");
        return userTradeInfoDao.selectList(lqw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserTradeInfoReq req) {
        if (StrUtil.equals(req.getCodeStatus(), CodeStatusEnum.BUY.getCode())) {
            UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildBuyInfo(req);
            computeTodayProfit(userTradeInfoEntity);
            computeLossPrice(userTradeInfoEntity);
            computeProfitPoint(userTradeInfoEntity);
            userTradeInfoDao.insert(userTradeInfoEntity);
            return;
        }

        if (StrUtil.equals(req.getCodeStatus(), CodeStatusEnum.HOLD.getCode())) {
            LambdaQueryWrapper<UserTradeInfoEntity> lqw = new LambdaQueryWrapper<>();
            lqw.eq(UserTradeInfoEntity::getUserName, req.getUserName());
            lqw.eq(UserTradeInfoEntity::getCode, req.getCode());
            lqw.eq(UserTradeInfoEntity::getCodeStatus, CodeStatusEnum.BUY.getCode());
            lqw.orderByDesc(UserTradeInfoEntity::getDate);
            lqw.last("limit 1");
            UserTradeInfoEntity userTradeInfoInTable = userTradeInfoDao.selectOne(lqw);

            UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildHoldInfo(req);
            userTradeInfoEntity = computeTodayProfit(userTradeInfoInTable, userTradeInfoEntity);

            userTradeInfoDao.insert(userTradeInfoEntity);
            return;
        }

        if (StrUtil.equals(req.getCodeStatus(), CodeStatusEnum.SELL.getCode())) {
            LambdaQueryWrapper<UserTradeInfoEntity> lqw = new LambdaQueryWrapper<>();
            lqw.eq(UserTradeInfoEntity::getUserName, req.getUserName());
            lqw.eq(UserTradeInfoEntity::getCode, req.getCode());
            lqw.eq(UserTradeInfoEntity::getCodeStatus, CodeStatusEnum.BUY.getCode());
            lqw.orderByDesc(UserTradeInfoEntity::getDate);
            lqw.last("limit 1");
            UserTradeInfoEntity userTradeInfoInTable = userTradeInfoDao.selectOne(lqw);

            UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildSellInfo(req);
            userTradeInfoEntity = computeTotalProfit(userTradeInfoInTable, userTradeInfoEntity);

            userTradeInfoDao.insert(userTradeInfoEntity);
        }
    }


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

    private void computeTodayProfit(UserTradeInfoEntity userTradeInfoEntity) {
        BigDecimal buyPrice = new BigDecimal(userTradeInfoEntity.getBuyPrice());

        BaseKLineReq req = BaseKLineInfoAdaptor.buildBaseKLineReq(userTradeInfoEntity.getCode(), userTradeInfoEntity.getDate());
        BaseKLineInfoDto kLine = baseKLineInfoService.getBaseKLineInfoByDay(req);

        BigDecimal close = kLine.getClose();

        BigDecimal subtract = BigDecimalUtils.subtract(close, buyPrice);
        String todayProfit = BigDecimalUtils.multiply(subtract, new BigDecimal(userTradeInfoEntity.getStockNumber())).toString();

        userTradeInfoEntity.setTodayProfit(todayProfit);
        userTradeInfoEntity.setTodayClosePrice(close.toString());
    }

    private UserTradeInfoEntity computeTodayProfit(UserTradeInfoEntity userTradeInfoInTable, UserTradeInfoEntity userTradeInfoEntity) {
        BigDecimal buyPrice = new BigDecimal(userTradeInfoInTable.getBuyPrice());

        BaseKLineReq req = BaseKLineInfoAdaptor.buildBaseKLineReq(userTradeInfoEntity.getCode(), userTradeInfoEntity.getDate());
        BaseKLineInfoDto kLine = baseKLineInfoService.getBaseKLineInfoByDay(req);

        BigDecimal close = kLine.getClose();

        BigDecimal subtract = BigDecimalUtils.subtract(close, buyPrice);
        String todayProfit = BigDecimalUtils.multiply(subtract, new BigDecimal(userTradeInfoEntity.getStockNumber())).toString();

        userTradeInfoEntity.setTodayProfit(todayProfit);
        userTradeInfoEntity.setTodayClosePrice(close.toString());
        return userTradeInfoEntity;
    }

    private UserTradeInfoEntity computeTotalProfit(UserTradeInfoEntity userTradeInfoInTable, UserTradeInfoEntity userTradeInfoEntity) {
        BigDecimal sellPrice = new BigDecimal(userTradeInfoEntity.getSellPrice());
        BigDecimal buyPrice = new BigDecimal(userTradeInfoInTable.getBuyPrice());

        BigDecimal subtract = BigDecimalUtils.subtract(sellPrice, buyPrice);
        String totalProfit = BigDecimalUtils.multiply(subtract, new BigDecimal(userTradeInfoEntity.getStockNumber())).toString();

        userTradeInfoEntity.setTotalProfit(totalProfit);
        return userTradeInfoEntity;
    }
}
























