package com.jcy.tradingstrategies.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
     * 日期
     */
    private String time;

    /**
     * 换手率
     */
    private String turnoverRatio;

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
    private String changeRatio;

    /**
     * 收盘价
     */
    private BigDecimal close;

    /**
     * 开盘价
     */
    private BigDecimal open;
}
