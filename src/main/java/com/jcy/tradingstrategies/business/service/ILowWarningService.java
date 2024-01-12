package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.entity.LowWarningEntity;

import java.util.List;

public interface ILowWarningService {
    List<LowWarningEntity> selectNeedWarningToday(String date);


    void updateIsAlert(LowWarningEntity lowWarningEntity);
}
