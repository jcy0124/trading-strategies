package com.jcy.tradingstrategies.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jcy.tradingstrategies.business.domain.dto.UserTradeInfoDto;
import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.UserTradeInfoReq;

import java.util.List;
import java.util.Map;

public interface IUserTradeInfoService {
    UserTradeInfoEntity add(UserTradeInfoReq req);

    List<UserTradeInfoEntity> getAll();

    UserTradeInfoEntity getLastCode(UserTradeInfoReq req);

    IPage<UserTradeInfoEntity> page(Map<String, String> map);

    void delete(String id);

    UserTradeInfoDto getById(String id);
}
