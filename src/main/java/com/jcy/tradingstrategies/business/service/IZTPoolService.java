package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.dto.ELBDto;
import com.jcy.tradingstrategies.business.domain.dto.LBDto;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;

import java.util.List;

public interface IZTPoolService{

    Integer insert(String response, String date);

    List<ZTPoolDto> selectByDate(String date);

    List<ELBDto> gerErBan(String date);

    String isExistByDate(String date);

    List<LBDto> getLBan(String date);

    ZTPoolDto selectLastZTByCode(String code,String date);

    ZTPoolDto selectByCodeAndDate(String code, String lastWorkDay);
}
