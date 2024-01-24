package com.jcy.tradingstrategies.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;
import com.jcy.tradingstrategies.business.domain.entity.PriceWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.PriceWarningReq;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IPriceWarningService {
    List<PriceWarningEntity> selectNeedWarningToday(String date);


    void updateIsAlert(PriceWarningEntity priceWarningEntity);


    void insertBatch(List<PriceWarningReq> list);

    List<PriceWarningEntity> selectNoAlertList();

    void updateCurrent(Integer id, BigDecimal current);

    IPage<PriceWarningEntity> page(Map<String, String> map);

    void insert(PriceWarningReq req);

    void delete(String id);

    SSGPDto findKline(String code);

    PriceWarningEntity getById(String id);

    void edit(PriceWarningReq req);
}
