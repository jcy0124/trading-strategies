package com.jcy.tradingstrategies.controller;

import com.jcy.tradingstrategies.service.ICalendarDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日期
 */
@RestController
@RequestMapping("calendarData")
@Slf4j
public class CalendarDataController {

    @Autowired
    private ICalendarDataService calendarDataService;

    @GetMapping
    public void insertCalendarData() {
        calendarDataService.insert();
    }
}
