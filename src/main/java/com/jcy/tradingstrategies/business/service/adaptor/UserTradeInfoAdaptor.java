package com.jcy.tradingstrategies.business.service.adaptor;

import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.UserTradeInfoReq;

public class UserTradeInfoAdaptor {


    public static UserTradeInfoEntity buildBuyInfo(UserTradeInfoReq req) {
        UserTradeInfoEntity userTradeInfoEntity = new UserTradeInfoEntity();
        userTradeInfoEntity.setUserName(req.getUserName());
        userTradeInfoEntity.setCode(req.getCode());
        userTradeInfoEntity.setName(req.getName());
        userTradeInfoEntity.setDate(req.getDate());
        userTradeInfoEntity.setCodeStatus(req.getCodeStatus());
        userTradeInfoEntity.setBuyPrice(req.getBuyPrice());
        userTradeInfoEntity.setStockNumber(req.getStockNumber());
        userTradeInfoEntity.setLossPoint(req.getLossPoint());
        userTradeInfoEntity.setFinishFlag("0");
        return userTradeInfoEntity;
    }

    public static UserTradeInfoEntity buildSellInfo(UserTradeInfoReq req) {
        UserTradeInfoEntity userTradeInfoEntity = new UserTradeInfoEntity();
        userTradeInfoEntity.setUserName(req.getUserName());
        userTradeInfoEntity.setCode(req.getCode());
        userTradeInfoEntity.setName(req.getName());
        userTradeInfoEntity.setDate(req.getDate());
        userTradeInfoEntity.setCodeStatus(req.getCodeStatus());
        userTradeInfoEntity.setSellPrice(req.getSellPrice());
        userTradeInfoEntity.setStockNumber(req.getStockNumber());
        userTradeInfoEntity.setFinishFlag("1");
        return userTradeInfoEntity;
    }

    public static UserTradeInfoEntity buildHoldInfo(UserTradeInfoReq req) {
        UserTradeInfoEntity userTradeInfoEntity = new UserTradeInfoEntity();
        userTradeInfoEntity.setUserName(req.getUserName());
        userTradeInfoEntity.setCode(req.getCode());
        userTradeInfoEntity.setName(req.getName());
        userTradeInfoEntity.setDate(req.getDate());
        userTradeInfoEntity.setCodeStatus(req.getCodeStatus());
        userTradeInfoEntity.setFinishFlag("0");
        return userTradeInfoEntity;
    }
}














