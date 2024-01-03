package com.jcy.tradingstrategies.service.qsV2;

import com.jcy.tradingstrategies.common.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuantitativeStrategiesV2Enum {

    REASON_1(1, "第一天涨停板后，第二天在涨停板高位横盘，第三天继续涨停，强势！！！"),
    REASON_2(2,"第一天涨停板后，第二天第三天收盘价均高于第一天涨停板的收盘价，持续关注！！！"),
    REASON_3(3,"第一天涨停板后，第二天没有高开，但是收盘价高于第一天涨停板，且第三天的实体K线的最低价格高于第二天的开盘价，持续关注！！！")
    ;


    private final Integer code;
    private final String msg;
}
