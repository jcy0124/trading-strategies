package com.jcy.tradingstrategies.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.common.ResultEnum;
import com.jcy.tradingstrategies.constant.TimeConstant;
import com.jcy.tradingstrategies.domain.dto.CommonDto;
import com.jcy.tradingstrategies.domain.vo.resp.CommonResp;
import com.jcy.tradingstrategies.service.ICalendarDateService;
import com.jcy.tradingstrategies.service.qsV1.IQuantitativeStrategiesV1Service;
import com.jcy.tradingstrategies.service.qsV2.IQuantitativeStrategiesV2Service;
import com.jcy.tradingstrategies.service.qsV2.QuantitativeStrategiesV2Dto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        return Result.ok(resp);
    }
}