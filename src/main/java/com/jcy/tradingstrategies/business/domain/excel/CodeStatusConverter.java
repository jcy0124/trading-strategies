package com.jcy.tradingstrategies.business.domain.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.jcy.tradingstrategies.business.domain.enums.CodeStatusEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * Excel 性别类型装换器
 *
 * @author wang suo
 * @version 1.0
 * @date 2021/9/14 15:06
 */
@Slf4j
public class CodeStatusConverter implements Converter<String> {

    @Override
    public Class supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public String convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if ("1".equals(value)) {
            return new WriteCellData(CodeStatusEnum.BUY.getStatus());
        }
        if ("2".equals(value)) {
            return new WriteCellData(CodeStatusEnum.HOLD.getStatus());
        }
        if ("3".equals(value)) {
            return new WriteCellData(CodeStatusEnum.SELL.getStatus());
        }
        return null;
    }
}
