package com.jcy.tradingstrategies.domain.vo.resp;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ZTPoolResp {

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 最新价
     */
    private BigDecimal lastPrice;

    /**
     * 换手率%
     */
    private BigDecimal turnoverRatio;


}
