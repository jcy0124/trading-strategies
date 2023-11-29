package com.jcy.tradingstrategies.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.common.ResultEnum;
import com.jcy.tradingstrategies.constant.TimeConstant;
import com.jcy.tradingstrategies.domain.dto.CommonDto;
import com.jcy.tradingstrategies.domain.dto.FBDto;
import com.jcy.tradingstrategies.domain.vo.resp.CommonResp;
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
     *  7个工作日内有两个涨停的情况，且第二个涨停板高于第一个涨停板
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

        if (CollectionUtil.isEmpty(list)){
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<CommonResp> resp = BeanUtil.copyToList(list, CommonResp.class);

        return Result.ok(resp);
    }


    /**
     *  第二个涨停板高于第一个涨停板
     * @param date
     * @return
     */
    @GetMapping("quantitativeStrategiesV3/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    public Result quantitativeStrategiesV3(@PathVariable String date) {

        List<CommonDto> commonDtoList = quantitativeStrategiesService.quantitativeStrategiesV3(date);

        if (CollectionUtil.isEmpty(commonDtoList)){
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<CommonResp> resp = BeanUtil.copyToList(commonDtoList, CommonResp.class);

        return Result.ok(resp);
    }



    @GetMapping("computeZTBSFHPGP/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    public Result computeZTBSFHPGP(@PathVariable String date) {
        log.info("开始计算第一个涨停板最高价横盘股票");

        log.info("结束计算第一个涨停板最高价横盘股票");
        return null;
    }

}



















