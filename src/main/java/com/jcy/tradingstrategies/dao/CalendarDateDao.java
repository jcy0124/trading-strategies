package com.jcy.tradingstrategies.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.domain.entity.CalendarDateEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarDateDao extends BaseMapper<CalendarDateEntity> {
    List<CalendarDateEntity> selectLast14Day(Integer id);
}
