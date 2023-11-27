package com.jcy.tradingstrategies.controller;

import com.jcy.tradingstrategies.service.ICalendarDateService;
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
public class CalendarDateController {

    @Autowired
    private ICalendarDateService calendarDateService;

    @GetMapping
    public void insertCalendarData() {
        calendarDateService.insert();
    }
}
