package com.jcy.tradingstrategies.business.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FBExcel {

    @ExcelProperty("代码")
    @ColumnWidth(20)
    private String code;

    @ExcelProperty("名称")
    @ColumnWidth(20)
    private String name;

}
