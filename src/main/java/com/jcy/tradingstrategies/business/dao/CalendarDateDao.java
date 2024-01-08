package com.jcy.tradingstrategies.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.business.domain.entity.CalendarDateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CalendarDateDao extends BaseMapper<CalendarDateEntity> {
    List<CalendarDateEntity> selectLast14Day(Integer id);

    List<String> selectWorkDateBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<CalendarDateEntity> selectNext14Day(Integer id);
}
