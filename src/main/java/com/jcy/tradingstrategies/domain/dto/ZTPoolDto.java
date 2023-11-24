package com.jcy.tradingstrategies.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ZTPoolDto {

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
     * 换手率%
     */
    private BigDecimal turnoverRatio;

    /**
     * 首次封板时间
     */
    private String firstCeilingTime;

    /**
     * 连扳次数
     */
    private Integer lbNum;

    /**
     * 时间
     */
    private String time;

}





















































