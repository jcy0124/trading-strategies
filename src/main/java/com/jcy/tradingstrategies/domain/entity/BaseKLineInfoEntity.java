package com.jcy.tradingstrategies.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@TableName("base_k_line_info")
public class BaseKLineInfoEntity {

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
     * 日期
     */
    private String time;

    /**
     * 换手率
     */
    private BigDecimal turnoverRatio;

    /**
     * 成交额
     */
    private BigDecimal amount;

    /**
     * 总市值
     */
    private BigDecimal totalCapital;

    /**
     * 均价
     */
    private BigDecimal avgPrice;

    /**
     * 涨跌
     */
    private BigDecimal change;

    /**
     * 总股本
     */
    private BigDecimal totalShares;

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
