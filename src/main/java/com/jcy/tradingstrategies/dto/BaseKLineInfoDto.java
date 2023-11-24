package com.jcy.tradingstrategies.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class BaseKLineInfoDto {


    /**
     * 自增id
     */
    private Integer id;

    /**
     * 代码
     */
    private String code;

    /**
     * 换手率
     */
    private BigDecimal turnoverRatio;

    /**
     * 成交额
     */
    private BigDecimal amount;

    /**
     * 均价
     */
    private BigDecimal avgPrice;

    /**
     * 涨跌
     */
    private BigDecimal change;

    /**
     * 最高价
     */
    private BigDecimal high;

    /**
     * 最低价
     */
    private BigDecimal low;

    /**
     * 涨跌幅
     */
    private BigDecimal changeRatio;

    /**
     * 收盘价
     */
    private BigDecimal close;

    /**
     * 开盘价
     */
    private BigDecimal open;
}
