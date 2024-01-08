package com.jcy.tradingstrategies.business.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodeStatusEnum {

    BUY("1", "买入"),
    HOLD("2", "持有"),
    SELL("3", "卖出");;


    private final String code;
    private final String status;
}
