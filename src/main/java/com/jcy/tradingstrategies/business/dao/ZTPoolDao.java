package com.jcy.tradingstrategies.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.business.domain.entity.ZTPoolEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZTPoolDao extends BaseMapper<ZTPoolEntity> {
    String isExistByDate(String date);

    List<ZTPoolEntity> selectLBList(@Param("code") String code, @Param("date") String date);

    ZTPoolDto selectLastZTByCode(@Param("code") String code, @Param("date") String date);
}
