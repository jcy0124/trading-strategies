package com.jcy.tradingstrategies.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.common.ResultCode;
import com.jcy.tradingstrategies.domain.dto.QSPoolDto;
import com.jcy.tradingstrategies.service.IBaseService;
import com.jcy.tradingstrategies.service.IQSPoolService;
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

import java.util.List;

/**
 * 强势股 没有任何参考价值
 */
@RestController
@RequestMapping("qspool")
@Slf4j
@Api(tags = "强势股票接口-QSPoolController")
public class QSPoolController {

    @Autowired
    private IQSPoolService qsPoolService;

    @Autowired
    private IBaseService baseService;

    @GetMapping("/getQsPoolByHttp/{date}")
    @DateValid
    @ApiOperation(value = "http同步强势股票-getQsPoolByHttp")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "date",value = "日期：yyyy-MM-dd",dataType = "String",required = true),
    })
    public Result getQsPoolByHttp(@PathVariable String date) {
        log.info("开始解析【{}】强势股票", date);

        String response = baseService.getQsPoolResp(date);
        qsPoolService.insert(response, date);

        log.info("结束解析【{}】强势股票", date);
        return Result.ok();
    }

    /**
     * 获取某天的强势股
     *
     * @param date
     * @return
     */
    @GetMapping("/getQsPool/{date}")
    @DateValid
    @ApiOperation(value = "查看强势股票-getQsPool")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "date",value = "日期：yyyy-MM-dd",dataType = "String",required = true),
    })
    public Result getQsPool(@PathVariable String date) {

        log.info("开始查看【{}】强势股票", date);

        List<QSPoolDto> qsPoolDtoList = qsPoolService.selectByDate(date);

        if (CollectionUtil.isEmpty(qsPoolDtoList)) {
            return Result.ok(ResultCode.SUCCESS, "没有查看到该天的强势数据");
        }

        log.info("结束查看【{}】强势股票", date);
        return Result.ok(qsPoolDtoList);
    }

}






















