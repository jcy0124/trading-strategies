package com.jcy.tradingstrategies.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.business.domain.entity.UserSummaryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserSummaryDao extends BaseMapper<UserSummaryEntity> {
    void add(UserSummaryEntity item);

    void updateSummary(Map<String, String> map);
}
