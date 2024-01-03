package com.jcy.tradingstrategies.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.domain.entity.UserTradeInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTradeInfoDao extends BaseMapper<UserTradeInfoEntity> {
}
