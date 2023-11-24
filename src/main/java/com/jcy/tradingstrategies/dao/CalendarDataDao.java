package com.jcy.tradingstrategies.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.domain.entity.CalendarDataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarDataDao extends BaseMapper<CalendarDataEntity> {
    List<CalendarDataEntity> selectLast14Day(Integer id);
}
