package com.jcy.tradingstrategies.business.controller;

import com.jcy.tradingstrategies.business.service.IAStockService;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("astock")
@Slf4j
@Api(tags = "a股市场股票接口-AStockController")
public class AStockController {

    @Autowired
    private IAStockService aStockService;

    @Autowired
    private IBaseService baseService;

    @GetMapping("getall")
    @ApiOperation(value = "http同步全量a股市场股票")
    public Result getAllAStock() {

        log.info("开始获取全部a股市场股票");
        String response = baseService.getAllAStock();
        aStockService.insert(response);

        log.info("结束获取全部a股市场股票");
        return Result.ok();
    }

}




















