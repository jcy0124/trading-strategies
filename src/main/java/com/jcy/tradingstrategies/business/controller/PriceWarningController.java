package com.jcy.tradingstrategies.business.controller;

import com.jcy.tradingstrategies.business.domain.vo.req.PriceWarningReq;
import com.jcy.tradingstrategies.business.service.IPriceWarningService;
import com.jcy.tradingstrategies.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pricewarning")
@Slf4j
@Api(tags = "价格提醒-PriceWarningController")
public class PriceWarningController {

    @Autowired
    private IPriceWarningService priceWarningService;

    @PostMapping("add")
    @ApiOperation(value = "新增最低价位预警")
    public Result add(@RequestBody List<PriceWarningReq> list) {

        log.info("开始新增最低价位预警");

        priceWarningService.insertBatch(list);

        log.info("结束新增最低价位预警");
        return Result.ok();
    }

}




















