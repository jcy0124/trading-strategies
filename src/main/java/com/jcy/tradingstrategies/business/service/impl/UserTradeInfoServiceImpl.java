package com.jcy.tradingstrategies.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.business.dao.UserTradeInfoDao;
import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.UserTradeInfoReq;
import com.jcy.tradingstrategies.business.service.IAStockService;
import com.jcy.tradingstrategies.business.service.IUserTradeInfoService;
import com.jcy.tradingstrategies.business.service.pattern.usertrade.UserTradeInfoStrategy;
import com.jcy.tradingstrategies.business.service.pattern.usertrade.UserTradeInfoStrategyHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserTradeInfoServiceImpl implements IUserTradeInfoService {

    public static final int PROFIT_LOSS_RATIO = 3;
    @Autowired
    private UserTradeInfoDao userTradeInfoDao;

    @Autowired
    private IAStockService iaStockService;

    @Autowired
    private UserTradeInfoStrategyHolder userTradeInfoStrategyHolder;

    @Override
    public List<UserTradeInfoEntity> getAll() {
        LambdaQueryWrapper<UserTradeInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserTradeInfoEntity::getUserName, "3w小姜");
        return userTradeInfoDao.selectList(lqw);
    }

    @Override
    public UserTradeInfoEntity getLastCode(UserTradeInfoReq req) {
        LambdaQueryWrapper<UserTradeInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserTradeInfoEntity::getUserName, req.getUserName());
        lqw.eq(UserTradeInfoEntity::getCode, req.getCode());
        lqw.eq(UserTradeInfoEntity::getFinishFlag, "0");
        lqw.orderByDesc(UserTradeInfoEntity::getDate);
        lqw.last("limit 1");
        return userTradeInfoDao.selectOne(lqw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserTradeInfoEntity add(UserTradeInfoReq req) {
        String name = iaStockService.selectNameByCode(req.getCode());
        req.setName(name);

        UserTradeInfoStrategy userTradeInfoStrategy = userTradeInfoStrategyHolder.of(req.getCodeStatus());
        if (Objects.isNull(userTradeInfoStrategy)) {
            return null;
        }

        return userTradeInfoStrategy.addUserTradeInfo(req);
    }
}




/*        if (StrUtil.equals(req.getCodeStatus(), CodeStatusEnum.BUY.getCode())) {
            UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildBuyInfo(req);
            computeTodayProfit(userTradeInfoEntity);
            computeLossPrice(userTradeInfoEntity);
            computeProfitPoint(userTradeInfoEntity);
            userTradeInfoDao.insert(userTradeInfoEntity);
            return userTradeInfoEntity;
        }

        if (StrUtil.equals(req.getCodeStatus(), CodeStatusEnum.HOLD.getCode())) {
            LambdaQueryWrapper<UserTradeInfoEntity> lqw = new LambdaQueryWrapper<>();
            lqw.eq(UserTradeInfoEntity::getUserName, req.getUserName());
            lqw.eq(UserTradeInfoEntity::getCode, req.getCode());
            lqw.eq(UserTradeInfoEntity::getFinishFlag, "0");
            lqw.orderByDesc(UserTradeInfoEntity::getDate);
            lqw.last("limit 1");
            UserTradeInfoEntity userTradeInfoLastDay = userTradeInfoDao.selectOne(lqw);

            UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildHoldInfo(req);
            userTradeInfoEntity.setStockNumber(userTradeInfoLastDay.getStockNumber());
            userTradeInfoEntity.setBuyPrice(userTradeInfoLastDay.getBuyPrice());
            userTradeInfoEntity = computeTodayProfit(userTradeInfoLastDay, userTradeInfoEntity);

            userTradeInfoDao.insert(userTradeInfoEntity);
            return userTradeInfoEntity;
        }

        if (StrUtil.equals(req.getCodeStatus(), CodeStatusEnum.SELL.getCode())) {
            LambdaQueryWrapper<UserTradeInfoEntity> lqw = new LambdaQueryWrapper<>();
            lqw.eq(UserTradeInfoEntity::getUserName, req.getUserName());
            lqw.eq(UserTradeInfoEntity::getCode, req.getCode());
            lqw.eq(UserTradeInfoEntity::getFinishFlag, "0");
            lqw.orderByDesc(UserTradeInfoEntity::getDate);
            lqw.last("limit 1");
            UserTradeInfoEntity userTradeInfoInTable = userTradeInfoDao.selectOne(lqw);

            UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildSellInfo(req);

            userTradeInfoEntity.setBuyPrice(userTradeInfoEntity.getBuyPrice());
            userTradeInfoEntity = computeTotalProfit(userTradeInfoInTable, userTradeInfoEntity);

            userTradeInfoDao.insert(userTradeInfoEntity);
            userTradeInfoDao.updateFinishFlag(userTradeInfoEntity);
            return userTradeInfoEntity;
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
        userTradeInfoEntity.setTotalProfit(todayProfit);
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
*/