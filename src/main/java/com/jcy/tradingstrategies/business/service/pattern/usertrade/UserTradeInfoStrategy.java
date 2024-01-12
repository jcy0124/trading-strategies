package com.jcy.tradingstrategies.business.service.pattern.usertrade;

import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.UserTradeInfoReq;

public interface UserTradeInfoStrategy {

    UserTradeInfoEntity addUserTradeInfo(UserTradeInfoReq req);
}
