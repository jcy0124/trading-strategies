package com.jcy.tradingstrategies.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.business.domain.entity.LowWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.LowWarningReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LowWarningDao extends BaseMapper<LowWarningEntity> {
    void insertBatch(@Param("list") List<LowWarningReq> list, @Param("time") String time);
}
