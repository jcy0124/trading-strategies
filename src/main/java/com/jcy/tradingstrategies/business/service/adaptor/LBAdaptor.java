package com.jcy.tradingstrategies.business.service.adaptor;

import com.jcy.tradingstrategies.business.domain.dto.LBDto;
import com.jcy.tradingstrategies.business.domain.entity.ZTPoolEntity;

import java.util.List;

public class LBAdaptor {

    public static LBDto buildLBDto(List<ZTPoolEntity> list) {
        LBDto lbDto = new LBDto();
        lbDto.setCode(list.get(0).getCode());
        lbDto.setName(list.get(0).getName());
        lbDto.setLbcs(list.size());
        return lbDto;
    }
}
