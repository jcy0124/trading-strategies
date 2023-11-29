package com.jcy.tradingstrategies.controller;

import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.constant.TimeConstant;
import com.jcy.tradingstrategies.domain.dto.FBDto;
import com.jcy.tradingstrategies.service.IQuantitativeStrategiesService;
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
    private IQuantitativeStrategiesService quantitativeStrategiesService;

    /**
     *  当日N字的股票
     * @param date
     * @return
     */
    @GetMapping("quantitativeStrategiesV1/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    public Result quantitativeStrategiesV1(@PathVariable String date) {

        quantitativeStrategiesService.quantitativeStrategiesV1(date);

        return null;
    }




    /**
     *  筛选可能反包的股票（已涨停板为前提条件，第二天下跌，第三天可能涨停反包）
     * @param date
     * @return
     */
    @GetMapping("quantitativeStrategiesV2/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    public Result quantitativeStrategiesV2(@PathVariable String date) {

        List<FBDto> list = quantitativeStrategiesService.quantitativeStrategiesV2(date);

        return Result.ok(list);
    }





    @GetMapping("computeZTBSFHPGP/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    public Result computeZTBSFHPGP(@PathVariable String date) {
        log.info("开始计算第一个涨停板最高价横盘股票");

        log.info("结束计算第一个涨停板最高价横盘股票");
        return null;
    }

}



















