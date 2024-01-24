package com.jcy.tradingstrategies.business.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;
import com.jcy.tradingstrategies.business.domain.entity.PriceWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.PriceWarningReq;
import com.jcy.tradingstrategies.business.domain.vo.resp.CommonResp;
import com.jcy.tradingstrategies.business.service.IPriceWarningService;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pricewarning")
@Slf4j
@Api(tags = "价格提醒-PriceWarningController")
public class PriceWarningController {

    @Autowired
    private IPriceWarningService priceWarningService;

    @PostMapping("add")
    @ApiOperation(value = "新增价位预警")
    public Result add(@RequestBody List<PriceWarningReq> list) {

        log.info("开始新增价位预警");

        priceWarningService.insertBatch(list);

        log.info("结束新增价位预警");
        return Result.ok();
    }

    @PostMapping("web/page")
    public Result page(@RequestParam Map<String, String> map) {

        log.info("开始查询价位预警");

        IPage<PriceWarningEntity> result = priceWarningService.page(map);

        log.info("结束查询价位预警");
        return Result.ok(result);
    }

    @PostMapping("web/add")
    public Result add(@RequestBody PriceWarningReq req) {

        log.info("开始新增价位预警");

        priceWarningService.insert(req);

        log.info("结束新增价位预警");
        return Result.ok();
    }

    @PostMapping("web/edit")
    public Result edit(@RequestBody PriceWarningReq req) {

        if (StrUtil.isBlank(req.getId())){
            return Result.ok();
        }

        log.info("开始修改价位预警");

        priceWarningService.edit(req);

        log.info("开始修改价位预警");
        return Result.ok();
    }

    @PostMapping("web/delete")
    public Result delete(@RequestParam String id) {

        log.info("开始删除价位预警");

        priceWarningService.delete(id);

        log.info("结束删除价位预警");
        return Result.ok();
    }

    @PostMapping("web/getById")
    public Result getById(@RequestParam String id) {

        log.info("开始查询【{}】信息", id);

        PriceWarningEntity result = priceWarningService.getById(id);

        log.info("结束查询【{}】信息", id);
        return Result.ok(result);
    }

    @PostMapping("web/findkline")
    public Result findkline(@RequestParam String code) {

        log.info("开始查询【{}】信息", code);

        SSGPDto ssgpDto = priceWarningService.findKline(code);

        log.info("结束查询【{}】信息", code);
        return Result.ok(ssgpDto);
    }
}




















