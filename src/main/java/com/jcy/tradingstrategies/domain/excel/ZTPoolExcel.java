package com.jcy.tradingstrategies.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ZTPoolExcel {

    /**
     * 时间
     */
    @ExcelProperty("时间")
    @ColumnWidth(20)
    private String time;

    /**
     * 代码
     */
    @ExcelProperty("代码")
    @ColumnWidth(20)
    private String code;

    /**
     * 名称
     */
    @ExcelProperty("名称")
    @ColumnWidth(20)
    private String name;


    /**
     * 最新价
     */
    @ExcelProperty("最新价")
    @ColumnWidth(20)
    private BigDecimal lastPrice;


    /**
     * 换手率%
     */
    @ExcelProperty("换手率")
    @ColumnWidth(20)
    private BigDecimal turnoverRatio;


    /**
     * 首次封板时间
     */
    @ExcelProperty("首次封板时间")
    @ColumnWidth(20)
    private String firstCeilingTime;

    /**
     * 最后封板时间
     */
    @ExcelProperty("最后封板时间")
    @ColumnWidth(20)
    private String lastCeilingTime;

    /**
     * 连扳次数
     */
    @ExcelProperty("连扳次数")
    @ColumnWidth(20)
    private Integer lbNum;

    /**
     * 所属行业
     */
    @ExcelProperty("所属行业")
    @ColumnWidth(20)
    private String industry;

    /**
     * 概念
     */
    @ExcelProperty("概念")
    @ColumnWidth(20)
    private String gl;

    /**
     * 股票涨停原因
     */
    @ExcelProperty("股票涨停原因")
    @ColumnWidth(20)
    private String stockReason;

    /**
     * 主题涨停原因
     */
    @ExcelProperty("主题涨停原因")
    @ColumnWidth(20)
    private String plateReason;

    /**
     * 涨停主题
     */
    @ExcelProperty("涨停主题")
    @ColumnWidth(20)
    private String plateName;
}
