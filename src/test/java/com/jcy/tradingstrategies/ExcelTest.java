package com.jcy.tradingstrategies;

import com.jcy.tradingstrategies.service.IExcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelTest {

    @Autowired
    private IExcelService excelService;

    @Test
    public void testExcel(){
        excelService.exportRenQiPool();
    }
}



























