package com.jcy.tradingstrategies.business.controller;

import com.jcy.tradingstrategies.business.domain.vo.req.LowWarningReq;
import com.jcy.tradingstrategies.business.service.IAStockService;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.business.service.ILowWarningService;
import com.jcy.tradingstrategies.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lowwarning")
@Slf4j
@Api(tags = "最低提醒-LowWarningController")
public class LowWarningController {

    @Autowired
    private ILowWarningService lowWarningService;

    @PostMapping("add")
    @ApiOperation(value = "新增最低价位预警")
    public Result add(@RequestBody List<LowWarningReq> list) {

        log.info("开始新增最低价位预警");

        lowWarningService.insertBatch(list);

        log.info("结束新增最低价位预警");
        return Result.ok();
    }

}




















