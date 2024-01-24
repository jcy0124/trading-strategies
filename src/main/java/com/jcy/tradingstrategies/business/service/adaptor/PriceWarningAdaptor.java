package com.jcy.tradingstrategies.business.service.adaptor;

import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;
import com.jcy.tradingstrategies.business.domain.entity.PriceWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.PriceWarningReq;
import com.jcy.tradingstrategies.common.util.BigDecimalUtils;

public class PriceWarningAdaptor {

    public static PriceWarningEntity buildPriceWarning(SSGPDto ssgp, String name, PriceWarningReq req) {
        PriceWarningEntity priceWarningEntity = new PriceWarningEntity();
        priceWarningEntity.setCode(req.getCode());
        priceWarningEntity.setName(name);
        priceWarningEntity.setComparePrice(ssgp.getCurrent());
        priceWarningEntity.setPriceLimitWarning(req.getPriceLimitWarning());
        priceWarningEntity.setReason(req.getReason());
        String upOrLow = BigDecimalUtils.compare(req.getPriceLimitWarning(), ssgp.getCurrent()) >= 0 ? "up" : "low";
        priceWarningEntity.setUpOrLow(upOrLow);
        return priceWarningEntity;
    }
}
