package com.jcy.tradingstrategies.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.common.ResultEnum;
import com.jcy.tradingstrategies.constant.TimeConstant;
import com.jcy.tradingstrategies.domain.dto.ELBDto;
import com.jcy.tradingstrategies.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.domain.excel.ELBExcel;
import com.jcy.tradingstrategies.domain.excel.UserTradeInfoExcel;
import com.jcy.tradingstrategies.domain.excel.ZTPoolExcel;
import com.jcy.tradingstrategies.service.IUserTradeInfoService;
import com.jcy.tradingstrategies.service.IZTPoolService;
import com.jcy.tradingstrategies.service.adaptor.ZTPoolAdaptor;
import com.jcy.tradingstrategies.util.EasyExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("excel")
@Slf4j
@Api(tags = "Excel导出相关接口-ExcelController")
public class ExcelController {

    @Autowired
    private IZTPoolService ztPoolService;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private IUserTradeInfoService userTradeInfoService;

    private String filePath = "C:\\Users\\78701\\Desktop\\excel\\%s.xlsx";

    private String txtFilePath = "C:\\Users\\78701\\Desktop\\txt\\%s.txt";

    private String userInfoPath = "C:\\Users\\78701\\Desktop\\%s.xlsx";

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

        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<String> codeList = ztPoolExcelList.stream().map(ZTPoolExcel::getCode).collect(Collectors.toList());
                String newTxtFilePath = String.format(txtFilePath, "【" + date + "】涨停板股票");
                BufferedWriter bw;
                try {
                    bw = new BufferedWriter(new FileWriter(newTxtFilePath));
                    for (String code : codeList) {
                        bw.write(code);
                        bw.newLine();
                        bw.flush();
                    }
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

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

        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<String> codeList = elbExcelList.stream().map(ELBExcel::getCode).collect(Collectors.toList());
                String newTxtFilePath = String.format(txtFilePath, "【" + date + "】二连板股票");
                BufferedWriter bw;
                try {
                    bw = new BufferedWriter(new FileWriter(newTxtFilePath));
                    for (String code : codeList) {
                        bw.write(code);
                        bw.newLine();
                        bw.flush();
                    }
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        String newFilePath = String.format(filePath, "【" + date + "】二连板股票");
        EasyExcelUtil.exportToExcel(new ELBExcel(), elbExcelList, newFilePath, "二连板股票");

        return Result.ok();
    }


    @GetMapping("exportUserTradeInfo")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    @ApiOperation(value = "Excel用户交易记录导出-exportUserTradeInfo")
    public Result exportUserTradeInfo() {


        List<UserTradeInfoEntity> userTradeInfoEntities = userTradeInfoService.getAll();

        if (CollectionUtil.isEmpty(userTradeInfoEntities)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<UserTradeInfoExcel> result = BeanUtil.copyToList(userTradeInfoEntities, UserTradeInfoExcel.class);


        String newFilePath = String.format(userInfoPath, "3w小姜交易记录");
        EasyExcelUtil.exportToExcel(new UserTradeInfoExcel(), result, newFilePath, "用户交易记录");

        return Result.ok();
    }
}





































