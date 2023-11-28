package com.jcy.tradingstrategies.controller;

import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.domain.dto.RenQiDto;
import com.jcy.tradingstrategies.service.IBaseService;
import com.jcy.tradingstrategies.service.IRenQiService;
import com.jcy.tradingstrategies.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("renqi")
@Slf4j
public class RenQiController {

    @Autowired
    private IRenQiService renQiService;

    @Autowired
    private IBaseService baseService;

    @GetMapping("/getRenQiPoolByHttp")
    @DateValid
    public Result getRenQiPoolByHttp() {

        String today = DateUtil.getToday();

        log.info("开始解析【{}】人气股票", today);

        String response = baseService.getRenQiPoolResp();

        List<RenQiDto> renQiDtoList = renQiService.convert(response);

        log.info("结束解析【{}】人气股票", today);
        return Result.ok(renQiDtoList);
    }
}
















