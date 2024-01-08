package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.vo.req.UserInfoInsertReq;
import com.jcy.tradingstrategies.business.domain.vo.req.UserInfoUpdateReq;


public interface IUserInfoService {
    void add(UserInfoInsertReq userInfoReq);

    void update(UserInfoUpdateReq req);

    void updateAmount(String userName, String todayProfit);
}
