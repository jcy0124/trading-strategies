package com.jcy.tradingstrategies.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.domain.entity.AStockEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AStockDao extends BaseMapper<AStockEntity> {
    void insertBatch(List<AStockEntity> list);

    void deleteAll();
}
