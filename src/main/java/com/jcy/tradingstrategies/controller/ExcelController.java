package com.jcy.tradingstrategies.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.common.ResultEnum;
import com.jcy.tradingstrategies.domain.dto.ELBDto;
import com.jcy.tradingstrategies.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.domain.excel.ELBExcel;
import com.jcy.tradingstrategies.domain.excel.ZTPoolExcel;
import com.jcy.tradingstrategies.service.IExcelService;
import com.jcy.tradingstrategies.service.IZTPoolService;
import com.jcy.tradingstrategies.util.EasyExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("excel")
@Slf4j
public class ExcelController {

    @Autowired
    private IExcelService excelService;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private IZTPoolService ztPoolService;

    public static String filePath = "C:\\Users\\78701\\Desktop\\excel\\%s.xlsx";

    @GetMapping("exportZTPool/{date}")
    @DateValid
    public Result exportZTPool(@PathVariable String date) throws IOException {


        List<ZTPoolDto> ztPoolDtoList = ztPoolService.selectByDate(date);
        if (CollectionUtil.isEmpty(ztPoolDtoList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<ZTPoolExcel> ztPoolExcelList = new ArrayList<>();
        for (ZTPoolDto ztPoolDto : ztPoolDtoList) {
            ZTPoolExcel ztPoolExcel = BeanUtil.copyProperties(ztPoolDto, ZTPoolExcel.class);
            ztPoolExcelList.add(ztPoolExcel);
        }

        filePath = String.format(filePath, "【" + date + "】涨停板股票");
        EasyExcelUtil.exportToExcel(new ZTPoolExcel(), ztPoolExcelList, filePath, "涨停板股票");

        return Result.ok();
    }

    @GetMapping("exportEBPool/{date}")
    @DateValid
    public Result exportEBPool(@PathVariable String date) throws IOException {


        List<ELBDto> elbDtoList = ztPoolService.gerErBan(date);
        if (CollectionUtil.isEmpty(elbDtoList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<ELBExcel> elbExcelList = new ArrayList<>();
        for (ELBDto elbDto : elbDtoList) {
            ELBExcel elbExcel = BeanUtil.copyProperties(elbDto, ELBExcel.class);
            elbExcelList.add(elbExcel);
        }

        filePath = String.format(filePath, "【" + date + "】二连板股票");
        EasyExcelUtil.exportToExcel(new ELBExcel(), elbExcelList, filePath, "二连板股票");

        return Result.ok();
    }

}





































