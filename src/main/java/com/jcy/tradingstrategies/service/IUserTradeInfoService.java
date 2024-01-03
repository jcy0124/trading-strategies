package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.domain.vo.req.UserTradeInfoReq;

import java.util.List;

public interface IUserTradeInfoService {
    void add(UserTradeInfoReq req);

    List<UserTradeInfoEntity> getAll();

}
