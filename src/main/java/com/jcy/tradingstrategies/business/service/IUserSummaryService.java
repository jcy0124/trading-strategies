package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.entity.UserSummaryEntity;

import java.util.Map;


public interface IUserSummaryService {
    UserSummaryEntity insert(Integer id);

    UserSummaryEntity getByUserTradeId(String userTradeId);

    void update(Map<String, String> map);
}
