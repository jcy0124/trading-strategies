package com.jcy.tradingstrategies.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jcy.tradingstrategies.business.domain.dto.ELBDto;
import com.jcy.tradingstrategies.business.domain.dto.LBDto;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.business.domain.entity.AStockEntity;
import com.jcy.tradingstrategies.business.domain.entity.ZTPoolEntity;
import com.jcy.tradingstrategies.business.domain.vo.resp.ELBResp;

import java.util.List;
import java.util.Map;

public interface IZTPoolService{

    Integer insert(String response, String date);

    List<ZTPoolDto> selectByDate(String date);

    List<ELBDto> gerErBan(String date);

    String isExistByDate(String date);

    List<LBDto> getLBan(String date);

    ZTPoolDto selectLastZTByCode(String code,String date);

    ZTPoolDto selectByCodeAndDate(String code, String lastWorkDay);

    IPage<ZTPoolEntity> ztpage(Map<String, String> map);

    IPage<ZTPoolEntity> ebpage(Map<String, String> map);

    ZTPoolEntity getById(String id);
}
