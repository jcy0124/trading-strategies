package com.jcy.tradingstrategies.domain.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class BaseKLineInfoResp {



    /**
     * 自增id
     */
    @ApiModelProperty("自增id")
    private Integer id;

    /**
     * 代码
     */
    @ApiModelProperty("股票代码")
    private String code;

    /**
     * 日期
     */
    @ApiModelProperty("日期")
    private String time;

    /**
     * 换手率
     */
    @ApiModelProperty("换手率")
    private String turnoverRatio;

    /**
     * 成交额
     */
    @ApiModelProperty("成交额")
    private BigDecimal amount;

    /**
     * 总市值
     */
    @ApiModelProperty("总市值")
    private BigDecimal totalCapital;

    /**
     * 均价
     */
    @ApiModelProperty("均价")
    private BigDecimal avgPrice;

    /**
     * 涨跌
     */
    @ApiModelProperty("涨跌")
    private BigDecimal change;

    /**
     * 总股本
     */
    @ApiModelProperty("总股本")
    private BigDecimal totalShares;

    /**
     * 最高价
     */
    @ApiModelProperty("最高价")
    private BigDecimal high;

    /**
     * 最低价
     */
    @ApiModelProperty("最低价")
    private BigDecimal low;

    /**
     * 涨跌幅
     */
    @ApiModelProperty("涨跌幅")
    private String changeRatio;

    /**
     * 收盘价
     */
    @ApiModelProperty("收盘价")
    private BigDecimal close;

    /**
     * 开盘价
     */
    @ApiModelProperty("开盘价")
    private BigDecimal open;
}
