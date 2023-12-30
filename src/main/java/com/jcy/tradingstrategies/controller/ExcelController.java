package com.jcy.tradingstrategies.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.adaptor.ZTPoolAdaptor;
import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.common.ResultEnum;
import com.jcy.tradingstrategies.constant.TimeConstant;
import com.jcy.tradingstrategies.domain.dto.ELBDto;
import com.jcy.tradingstrategies.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.domain.excel.ELBExcel;
import com.jcy.tradingstrategies.domain.excel.ZTPoolExcel;
import com.jcy.tradingstrategies.service.IZTPoolService;
import com.jcy.tradingstrategies.util.EasyExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("excel")
@Slf4j
@Api(tags = "Excel导出相关接口-ExcelController")
public class ExcelController {

    @Autowired
    private IZTPoolService ztPoolService;

    private String filePath = "C:\\Users\\78701\\Desktop\\excel\\%s.xlsx";

    @GetMapping("exportZTPool/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    @ApiOperation(value = "Excel涨停板股票导出-exportZTPool")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "date", value = "日期：yyyy-MM-dd", dataType = "String", required = true),
    })
    public Result exportZTPool(@PathVariable String date) throws IOException {


        List<ZTPoolDto> ztPoolDtoList = ztPoolService.selectByDate(date);
        if (CollectionUtil.isEmpty(ztPoolDtoList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<ZTPoolExcel> ztPoolExcelList = new ArrayList<>();
        for (ZTPoolDto ztPoolDto : ztPoolDtoList) {
            ZTPoolExcel ztPoolExcel = BeanUtil.copyProperties(ztPoolDto, ZTPoolExcel.class);
            ztPoolExcel = ZTPoolAdaptor.buildZTPoolExcel(ztPoolExcel);
            ztPoolExcelList.add(ztPoolExcel);
        }

        String newFilePath = String.format(filePath, "【" + date + "】涨停板股票");
        EasyExcelUtil.exportToExcel(new ZTPoolExcel(), ztPoolExcelList, newFilePath, "涨停板股票");

        return Result.ok();
    }

    @GetMapping("exportEBPool/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    @ApiOperation(value = "Excel二连板股票导出-exportEBPool")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "date", value = "日期：yyyy-MM-dd", dataType = "String", required = true),
    })
    public Result exportEBPool(@PathVariable String date) throws IOException {


        List<ELBDto> elbDtoList = ztPoolService.gerErBan(date);
        if (CollectionUtil.isEmpty(elbDtoList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<ELBExcel> elbExcelList = BeanUtil.copyToList(elbDtoList, ELBExcel.class);

        String newFilePath = String.format(filePath, "【" + date + "】二连板股票");
        EasyExcelUtil.exportToExcel(new ELBExcel(), elbExcelList, newFilePath, "二连板股票");

        return Result.ok();
    }
}





































