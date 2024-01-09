package com.jcy.tradingstrategies.business.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.business.domain.dto.CommonDto;
import com.jcy.tradingstrategies.business.domain.vo.resp.CommonResp;
import com.jcy.tradingstrategies.business.service.ICalendarDateService;
import com.jcy.tradingstrategies.business.service.qsV1.IQuantitativeStrategiesV1Service;
import com.jcy.tradingstrategies.business.service.qsV2.IQuantitativeStrategiesV2Service;
import com.jcy.tradingstrategies.business.service.qsV2.QuantitativeStrategiesV2Dto;
import com.jcy.tradingstrategies.common.annotation.DateValid;
import com.jcy.tradingstrategies.common.base.Result;
import com.jcy.tradingstrategies.common.base.ResultEnum;
import com.jcy.tradingstrategies.common.constant.TimeConstant;
import com.jcy.tradingstrategies.common.util.FIleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("quantitativeStrategies")
@Slf4j
public class QuantitativeStrategiesController {

    @Autowired
    private ICalendarDateService calendarDateService;

    @Autowired
    private IQuantitativeStrategiesV1Service quantitativeStrategiesV1Service;

    @Autowired
    private IQuantitativeStrategiesV2Service quantitativeStrategiesV2Service;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    private String qs1FilePath = "C:\\Users\\78701\\Desktop\\qs1\\%s.txt";

    private String qs2FilePath = "C:\\Users\\78701\\Desktop\\qs2\\%s.txt";

    /**
     * @param date
     * @return
     */
    @GetMapping("quantitativeStrategiesV1/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    public Result quantitativeStrategiesV1(@PathVariable String date) {

        List<CommonDto> commonDtoList = quantitativeStrategiesV1Service.quantitativeStrategiesV1(date);

        if (CollectionUtil.isEmpty(commonDtoList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<CommonResp> resp = BeanUtil.copyToList(commonDtoList, CommonResp.class);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<String> codeList = resp.stream().map(CommonResp::getCode).collect(Collectors.toList());
                String newTxtFilePath = String.format(qs1FilePath, "【" + date + "】qs1股票策略");
                BufferedWriter bw;
                try {
                    FIleUtil.writeTxt(codeList, newTxtFilePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        return Result.ok(resp);
    }

    /**
     * @param date
     * @return
     */
    @GetMapping("quantitativeStrategiesV2/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    public Result quantitativeStrategiesV2(@PathVariable String date) {

        String startDate = date;

        for (int i = 0; i < 2; i++) {
            startDate = calendarDateService.selectLastWorkDay(startDate);
        }

        List<QuantitativeStrategiesV2Dto> result = quantitativeStrategiesV2Service.quantitativeStrategiesV2(startDate);

        if (CollectionUtil.isEmpty(result)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<CommonResp> resp = BeanUtil.copyToList(result, CommonResp.class);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<String> codeList = resp.stream().map(CommonResp::getCode).collect(Collectors.toList());
                String newTxtFilePath = String.format(qs2FilePath, "【" + date + "】qs2股票策略");
                BufferedWriter bw;
                try {
                    FIleUtil.writeTxt(codeList, newTxtFilePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return Result.ok(resp);
    }
}