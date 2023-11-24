package com.jcy.tradingstrategies.controller;

import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.common.ResultCode;
import com.jcy.tradingstrategies.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.service.IBaseService;
import com.jcy.tradingstrategies.domain.req.BaseKLineReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("baseKLineInfo")
@Slf4j
public class BaseKLineInfoController {

    @Autowired
    private IBaseKLineInfoService baseKLineInfoService;

    @Autowired
    private IBaseService baseService;

    @PostMapping("getBaseKLineInfo")
    public Result getBaseKLineInfo(@RequestBody @Validated BaseKLineReq req) {

        log.info("开始获取【{}】k线信息", req.getCode());

        String response = baseService.getBaseKLineResp(req);

        List<BaseKLineInfoDto> baseKLineInfoDtoList = baseKLineInfoService.getBaseKLineInfo(response);

        log.info("结束获取【{}】k线信息", req.getCode());
        return Result.ok(ResultCode.SUCCESS, "【" + req.getCode() + "】的K线信息", baseKLineInfoDtoList);
    }
}























