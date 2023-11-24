package com.jcy.tradingstrategies.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.List;

public class EasyExcelUtil {

    public static <T> void exportToExcel(T t, List<T> list, String filePath,String sheetName) {
        // 创建 ExcelWriterBuilder
        ExcelWriterBuilder writerBuilder = EasyExcel.write(filePath, t.getClass());

        // 定义写入的sheet名
        WriteSheet writeSheet = EasyExcel.writerSheet("用户信息").build();

        // 将数据写入 Excel 文件
        writerBuilder.sheet().doWrite(list);
    }
}

