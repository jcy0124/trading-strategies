package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.UserTradeInfoReq;

import java.util.List;

public interface IUserTradeInfoService {
    UserTradeInfoEntity add(UserTradeInfoReq req);

    List<UserTradeInfoEntity> getAll();

}
