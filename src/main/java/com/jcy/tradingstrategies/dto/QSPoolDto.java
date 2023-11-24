package com.jcy.tradingstrategies.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class QSPoolDto {

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 涨跌幅%
     */
    private BigDecimal changeRatio;

    /**
     * 最新价
     */
    private BigDecimal lastPrice;

    /**
     * 成交额
     */
    private BigDecimal amount;

    /**
     * 换手率%
     */
    private BigDecimal turnoverRatio;

    /**
     * 涨停统计
     */
    private String zttj;

    /**
     * 涨速
     */
    private String zs;

    /**
     * 是否新高 0-否 1-是
     */
    private String nh;

    /**
     * 量比
     */
    private String lb;

    /**
     * 时间
     */
    private String time;

}
