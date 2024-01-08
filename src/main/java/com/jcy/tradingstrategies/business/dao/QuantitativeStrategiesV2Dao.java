package com.jcy.tradingstrategies.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.business.domain.entity.QuantitativeStrategiesV2Entity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuantitativeStrategiesV2Dao extends BaseMapper<QuantitativeStrategiesV2Entity> {
    void insertBatch(@Param("list") List<QuantitativeStrategiesV2Entity> quantitativeStrategiesV2Entities);
}
