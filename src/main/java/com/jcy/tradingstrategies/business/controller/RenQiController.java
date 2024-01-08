package com.jcy.tradingstrategies.business.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jcy.tradingstrategies.common.base.Result;
import com.jcy.tradingstrategies.business.domain.dto.RenQiDto;
import com.jcy.tradingstrategies.business.domain.vo.resp.RenQiResp;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.business.service.IRenQiService;
import com.jcy.tradingstrategies.common.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("renqi")
@Slf4j
@Api(tags = "人气股票接口-RenQiController")
public class RenQiController {

    @Autowired
    private IRenQiService renQiService;

    @Autowired
    private IBaseService baseService;

    @GetMapping("/getRenQiPoolByHttp")
    @ApiOperation(value = "http同步当天人气股票-getRenQiPoolByHttp")
    public Result getRenQiPoolByHttp() {

        String today = DateUtil.getToday();

        log.info("开始解析【{}】人气股票", today);

        String response = baseService.getRenQiPoolResp();

        List<RenQiDto> renQiDtoList = renQiService.convert(response);

        List<RenQiResp> resp = BeanUtil.copyToList(renQiDtoList, RenQiResp.class);

        log.info("结束解析【{}】人气股票", today);
        return Result.ok(resp);
    }
}
















