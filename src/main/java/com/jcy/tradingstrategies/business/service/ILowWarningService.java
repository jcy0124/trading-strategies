package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.entity.LowWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.LowWarningReq;

import java.util.List;

public interface ILowWarningService {
    List<LowWarningEntity> selectNeedWarningToday(String date);


    void updateIsAlert(LowWarningEntity lowWarningEntity);


    void insertBatch(List<LowWarningReq> list);

    List<LowWarningEntity> selectNoAlertList();

}
