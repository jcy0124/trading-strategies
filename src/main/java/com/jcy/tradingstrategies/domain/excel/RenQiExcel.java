package com.jcy.tradingstrategies.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RenQiExcel {

    @ExcelProperty("代码")
    @ColumnWidth(20)
    private String code;

    @ExcelProperty("名称")
    @ColumnWidth(20)
    private String name;

    @ExcelProperty("涨跌幅")
    @ColumnWidth(20)
    private String changeRatio;

    @ExcelProperty("价格")
    @ColumnWidth(20)
    private String price;
}
