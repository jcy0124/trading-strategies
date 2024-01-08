package com.jcy.tradingstrategies.business.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserTradeInfoExcel {

    @ExcelProperty("用户名称")
    @ColumnWidth(15)
    private String userName;

    @ExcelProperty("股票代码")
    @ColumnWidth(15)
    private String code;

    @ExcelProperty("股票名称")
    @ColumnWidth(15)
    private String name;

    @ExcelProperty("交易日期")
    @ColumnWidth(15)
    private String date;

    @ExcelProperty(value = "交易状态" ,converter = CodeStatusConverter.class)
    @ColumnWidth(15)
    private String codeStatus;

    @ExcelProperty("买入价格")
    @ColumnWidth(15)
    private String buyPrice;

    @ExcelProperty("当日收盘价格")
    @ColumnWidth(20)
    private String todayClosePrice;

    @ExcelProperty("卖出价格")
    @ColumnWidth(15)
    private String sellPrice;

    @ExcelProperty("买入数量")
    @ColumnWidth(15)
    private String stockNumber;

    @ExcelProperty("当日盈亏")
    @ColumnWidth(15)
    private String todayProfit;

    @ExcelProperty("总盈亏")
    @ColumnWidth(15)
    private String totalProfit;

    @ExcelProperty("止损价格")
    @ColumnWidth(15)
    private String lossPoint;

    @ExcelProperty("止损点")
    @ColumnWidth(15)
    private String lossRatio;

    @ExcelProperty("止损金额")
    @ColumnWidth(15)
    private String lossPrice;

    @ExcelProperty("建议盈利价格")
    @ColumnWidth(20)
    private String profitPoint;

    @ExcelProperty("建议盈利点")
    @ColumnWidth(20)
    private String profitRatio;

    @ExcelProperty("建议盈利金额")
    @ColumnWidth(20)
    private String profitPrice;
}
