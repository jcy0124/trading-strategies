package com.jcy.tradingstrategies.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.dao.UserTradeInfoDao;
import com.jcy.tradingstrategies.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.domain.enums.CodeStatusEnum;
import com.jcy.tradingstrategies.domain.vo.req.UserTradeInfoReq;
import com.jcy.tradingstrategies.service.IUserTradeInfoService;
import com.jcy.tradingstrategies.service.adaptor.UserTradeInfoAdaptor;
import com.jcy.tradingstrategies.util.BigDecimalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class UserTradeInfoServiceImpl implements IUserTradeInfoService {

    @Autowired
    private UserTradeInfoDao userTradeInfoDao;

    @Override
    public void add(UserTradeInfoReq req) {
        if (StrUtil.equals(req.getCodeStatus(), CodeStatusEnum.BUY.getCode())) {
            UserTradeInfoEntity userTradeInfoEntity = UserTradeInfoAdaptor.buildBuyInfo(req);
            userTradeInfoDao.insert(userTradeInfoEntity);
            return;
        }

        if (StrUtil.equals(req.getCodeStatus(), CodeStatusEnum.HOLD.getCode())) {



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

            userTradeInfoEntity = computeProfit(userTradeInfoInTable, userTradeInfoEntity);

            userTradeInfoDao.insert(userTradeInfoEntity);
        }
    }

    private UserTradeInfoEntity computeProfit(UserTradeInfoEntity userTradeInfoInTable, UserTradeInfoEntity userTradeInfoEntity) {
        BigDecimal sellPrice = new BigDecimal(userTradeInfoEntity.getSellPrice());
        BigDecimal buyPrice = new BigDecimal(userTradeInfoInTable.getBuyPrice());

        BigDecimal subtract = BigDecimalUtils.subtract(sellPrice, buyPrice);
        String totalProfit = BigDecimalUtils.multiply(subtract, new BigDecimal(userTradeInfoEntity.getStockNumber())).toString();

        userTradeInfoEntity.setTotalProfit(totalProfit);
        return userTradeInfoEntity;
    }
}
























