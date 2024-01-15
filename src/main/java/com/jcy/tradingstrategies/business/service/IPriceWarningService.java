package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.entity.PriceWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.PriceWarningReq;

import java.util.List;

public interface IPriceWarningService {
    List<PriceWarningEntity> selectNeedWarningToday(String date);


    void updateIsAlert(PriceWarningEntity priceWarningEntity);


    void insertBatch(List<PriceWarningReq> list);

    List<PriceWarningEntity> selectNoAlertList();

}
