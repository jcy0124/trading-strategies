package com.jcy.tradingstrategies.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.jcy.tradingstrategies.domain.dto.RenQiDto;
import com.jcy.tradingstrategies.domain.excel.RenQiExcel;
import com.jcy.tradingstrategies.service.IBaseService;
import com.jcy.tradingstrategies.service.IExcelService;
import com.jcy.tradingstrategies.service.IRenQiService;
import com.jcy.tradingstrategies.util.EasyExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExcelServiceImpl implements IExcelService {

    @Autowired
    private IBaseService baseService;

    @Autowired
    private IRenQiService renQiService;

    private String filePath = "C:\\Users\\78701\\Desktop\\excel\\%s.xlsx";

    @Override
    @Scheduled(cron = "59 0/30 9-16 * * ?")
    public void exportRenQiPool() {
        String response = baseService.getRenQiPoolResp();
        List<RenQiDto> renQiDtoList = renQiService.convert(response);

        List<RenQiExcel> renQiExcelList = new ArrayList<>();
        for (RenQiDto renQiDto : renQiDtoList) {
            RenQiExcel renQiExcel = BeanUtil.copyProperties(renQiDto, RenQiExcel.class);
            renQiExcelList.add(renQiExcel);
        }
        log.info("人气股票：{}",renQiExcelList);

        String newFilePath = String.format(filePath, "【"+ DateUtil.now() +"】人气股票");
        EasyExcelUtil.exportToExcel(new RenQiExcel(), renQiExcelList, newFilePath, "人气股票");

    }

}






















