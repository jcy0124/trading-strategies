package com.jcy.tradingstrategies.controller;

import com.jcy.tradingstrategies.service.ICalendarDateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日期
 */
@RestController
@RequestMapping("calendarDate")
@Slf4j
@Api(tags = "日期相关接口-CalendarDateController")
public class CalendarDateController {

    @Autowired
    private ICalendarDateService calendarDateService;

    @GetMapping
    @ApiOperation(value = "同步本年全量日期")
    public void insertCalendarData() {
        calendarDateService.insert();
    }
}
