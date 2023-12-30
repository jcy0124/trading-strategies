package com.jcy.tradingstrategies.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.domain.entity.QuantitativeStrategiesV2Entity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuantitativeStrategiesV2Dao extends BaseMapper<QuantitativeStrategiesV2Entity> {
    void insertBatch(@Param("list") List<QuantitativeStrategiesV2Entity> quantitativeStrategiesV2Entities);
}
