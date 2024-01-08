package com.jcy.tradingstrategies.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTradeInfoDao extends BaseMapper<UserTradeInfoEntity> {
    void updateFinishFlag(UserTradeInfoEntity userTradeInfoEntity);
}
