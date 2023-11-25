package com.jcy.tradingstrategies.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@TableName("zt_pool")
public class ZTPoolEntity {

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
    private String changeRatio;

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
    private String turnoverRatio;

    /**
     * 封板资金
     */
    private BigDecimal ceilingAmount;

    /**
     * 首次封板时间
     */
    private String firstCeilingTime;

    /**
     * 最后封板时间
     */
    private String lastCeilingTime;

    /**
     * 炸板次数
     */
    private Integer bombNum;

    /**
     * 连扳次数
     */
    private Integer lbNum;

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

    /**
     * 股票涨停原因
     */
    private String stockReason;

    /**
     * 主题涨停原因
     */
    private String plateReason;

    /**
     * 涨停主题
     */
    private String plateName;
}





















































