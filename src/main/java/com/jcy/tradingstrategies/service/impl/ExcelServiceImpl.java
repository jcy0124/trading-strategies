package com.jcy.tradingstrategies.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
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

    private String filePath = "C:\\Users\\78701\\Desktop\\excel\\人气股票\\%s.xlsx";

    @Override
    @Scheduled(cron = "59 0/30 9-15 * * ?")
    public void exportRenQiPool() {
        log.info("- - - - - - - - - 开始人气股票导出定时任务 - - - - - - - - - ");
        try {
            String response = baseService.getRenQiPoolResp();
            List<RenQiDto> renQiDtoList = renQiService.convert(response);

            List<RenQiExcel> renQiExcelList = new ArrayList<>();
            for (RenQiDto renQiDto : renQiDtoList) {
                RenQiExcel renQiExcel = BeanUtil.copyProperties(renQiDto, RenQiExcel.class);
                renQiExcelList.add(renQiExcel);
            }


            String time = DateUtil.format(DateUtil.date(), "yyyy-MM-dd-HHmm");

            String newFilePath = String.format(filePath, "【" + time + "】人气股票");
            EasyExcelUtil.exportToExcel(new RenQiExcel(), renQiExcelList, newFilePath, "人气股票");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            log.info("- - - - - - - - - 结束人气股票导出定时任务 - - - - - - - - - ");
        }
    }

}






















