package com.jcy.tradingstrategies.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.business.domain.entity.LowWarningEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LowWarningDao extends BaseMapper<LowWarningEntity> {
}
