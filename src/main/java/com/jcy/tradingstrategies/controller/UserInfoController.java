package com.jcy.tradingstrategies.controller;


import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.domain.vo.req.UserInfoInsertReq;
import com.jcy.tradingstrategies.domain.vo.req.UserInfoUpdateReq;
import com.jcy.tradingstrategies.domain.vo.req.UserTradeInfoReq;
import com.jcy.tradingstrategies.service.IUserInfoService;
import com.jcy.tradingstrategies.service.IUserTradeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userinfo")
@Slf4j
@Api(tags = "用户相关接口-UserInfoController")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IUserTradeInfoService userTradeInfoService;

    @PostMapping("add")
    @ApiOperation(value = "新增用户信息")
    public Result addUserInfo(@RequestBody UserInfoInsertReq req) {

        log.info("开始新增用户信息");
        userInfoService.add(req);
        log.info("结束新增用户信息");
        return Result.ok();
    }

    @PostMapping("update")
    @ApiOperation(value = "变更用户信息")
    public Result updateUserInfo(@RequestBody UserInfoUpdateReq req) {

        log.info("开始变更用户信息");
        userInfoService.update(req);
        log.info("结束变更用户信息");
        return Result.ok();
    }

    @PostMapping("addUserTradeInfo")
    @ApiOperation(value = "用户交易信息")
    public Result addUserTradeInfo(@RequestBody UserTradeInfoReq req) {

        log.info("开始新增用户信息");
        userTradeInfoService.add(req);
        log.info("结束新增用户信息");
        return Result.ok();
    }

}














