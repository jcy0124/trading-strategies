package com.jcy.tradingstrategies.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@TableName("qs_pool")
public class QSPoolEntity {

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 流动市值
     */
    private BigDecimal flowCapital;

    /**
     * 总市值
     */
    private BigDecimal totalCapital;

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
     * 所属行业
     */
    private String industry;

    /**
     * 时间
     */
    private String time;

    /**
     * 概念
     */
    private String gl;

}
