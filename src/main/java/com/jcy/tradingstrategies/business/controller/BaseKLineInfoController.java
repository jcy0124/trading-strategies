package com.jcy.tradingstrategies.business.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jcy.tradingstrategies.common.base.Result;
import com.jcy.tradingstrategies.common.base.ResultCode;
import com.jcy.tradingstrategies.business.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.business.domain.vo.req.BaseKLineReq;
import com.jcy.tradingstrategies.business.domain.vo.resp.BaseKLineInfoResp;
import com.jcy.tradingstrategies.business.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.business.service.IBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础k线
 */
@RestController
@RequestMapping("baseKLineInfo")
@Slf4j
@Api(tags = "股票基础k线接口-BaseKLineInfoController")
public class BaseKLineInfoController {

    @Autowired
    private IBaseKLineInfoService baseKLineInfoService;

    @Autowired
    private IBaseService baseService;

    @PostMapping("getBaseKLineInfo")
    @ApiOperation(value = "http同步股票基础k线-getBaseKLineInfo")
    public Result getBaseKLineInfo(@RequestBody @Validated BaseKLineReq req) {

        log.info("开始获取【{}】k线信息", req.getCode());

        String response = baseService.getBaseKLineResp(req);

        List<BaseKLineInfoDto> baseKLineInfoDtoList = baseKLineInfoService.getBaseKLineInfo(response);

        List<BaseKLineInfoResp> resp = new ArrayList<>();
        for (BaseKLineInfoDto baseKLineInfoDto : baseKLineInfoDtoList) {
            BaseKLineInfoResp baseKLineInfoResp = BeanUtil.copyProperties(baseKLineInfoDto, BaseKLineInfoResp.class);
            resp.add(baseKLineInfoResp);
        }

        log.info("结束获取【{}】k线信息", req.getCode());
        return Result.ok(ResultCode.SUCCESS, "【" + req.getCode() + "】的K线信息", resp);
    }
}























