package com.jcy.tradingstrategies.business.controller;

import com.jcy.tradingstrategies.business.domain.entity.UserSummaryEntity;
import com.jcy.tradingstrategies.business.service.IUserSummaryService;
import com.jcy.tradingstrategies.common.base.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("usersummary")
@Slf4j
@Api(tags = "用户总结表-UserSummaryController")
public class UserSummaryController {

    @Autowired
    private IUserSummaryService userSummaryService;

    @PostMapping("web/getbyusertardeid")
    public Result getByUserTradeId(@RequestParam String userTradeId) {

        log.info("开始查询用户总结信息");
        UserSummaryEntity result = userSummaryService.getByUserTradeId(userTradeId);
        log.info("结束查询用户总结信息");
        return Result.ok(result);
    }

    @PostMapping("web/update")
    public Result update(@RequestParam Map<String, String> map) {

        log.info("开始修改用户总结信息");
        userSummaryService.update(map);
        log.info("结束修改用户总结信息");
        return Result.ok();
    }
}




















