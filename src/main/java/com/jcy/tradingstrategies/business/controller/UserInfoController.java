package com.jcy.tradingstrategies.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jcy.tradingstrategies.business.domain.dto.UserTradeInfoDto;
import com.jcy.tradingstrategies.business.domain.entity.UserTradeInfoEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.UserInfoInsertReq;
import com.jcy.tradingstrategies.business.domain.vo.req.UserInfoUpdateReq;
import com.jcy.tradingstrategies.business.domain.vo.req.UserTradeInfoReq;
import com.jcy.tradingstrategies.business.service.IUserInfoService;
import com.jcy.tradingstrategies.business.service.IUserTradeInfoService;
import com.jcy.tradingstrategies.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
        UserTradeInfoEntity result = userTradeInfoService.add(req);
        log.info("结束新增用户信息");
        return Result.ok(result);
    }


    @PostMapping("web/add")
    @ApiOperation(value = "用户交易信息")
    public Result add(@RequestBody UserTradeInfoReq req) {

        log.info("开始新增用户信息");
        UserTradeInfoEntity result = userTradeInfoService.add(req);
        log.info("结束新增用户信息");
        return Result.ok(result);
    }

    @PostMapping("web/getbyid")
    @ApiOperation(value = "用户交易信息")
    public Result getById(@RequestParam String id) {

        log.info("开始查询用户信息");
        UserTradeInfoDto result = userTradeInfoService.getById(id);
        log.info("结束查询用户信息");
        return Result.ok(result);
    }


    @PostMapping("web/delete")
    @ApiOperation(value = "用户交易信息")
    public Result delete(@RequestParam String id) {

        log.info("开始删除用户信息");
         userTradeInfoService.delete(id);
        log.info("结束删除用户信息");
        return Result.ok();
    }


    @PostMapping("web/page")
    @ApiOperation(value = "查看用户交易信息")
    public Result page(@RequestParam Map<String,String> map) {

        log.info("开始新增用户信息");
        IPage<UserTradeInfoEntity> result = userTradeInfoService.page(map);
        log.info("结束新增用户信息");
        return Result.ok(result);
    }


}














