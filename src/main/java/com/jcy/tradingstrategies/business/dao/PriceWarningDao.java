package com.jcy.tradingstrategies.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.business.domain.entity.PriceWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.PriceWarningReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PriceWarningDao extends BaseMapper<PriceWarningEntity> {
    void insertBatch(@Param("list") List<PriceWarningReq> list, @Param("time") String time);

    void updateAlert(@Param("id") Integer id);
}
