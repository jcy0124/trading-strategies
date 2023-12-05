package com.jcy.tradingstrategies.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommonExcel {

    @ExcelProperty("代码")
    @ColumnWidth(20)
    private String code;

    @ExcelProperty("名称")
    @ColumnWidth(20)
    private String name;

    @ExcelProperty("上一个涨停日")
    @ColumnWidth(20)
    private String lastZTDate;

    @ExcelProperty("当前涨停日")
    @ColumnWidth(20)
    private String nowZTDate;

}
