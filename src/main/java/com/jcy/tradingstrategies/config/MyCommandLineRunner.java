package com.jcy.tradingstrategies.config;


import com.jcy.tradingstrategies.service.ICalendarDateService;
import com.jcy.tradingstrategies.service.cache.CalendarDateCache;
import com.jcy.tradingstrategies.service.cache.ZTPoolCache;
import com.jcy.tradingstrategies.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {

    @Resource
    ApplicationContext applicationContext;

    @Autowired
    private ZTPoolCache ztPoolCache;

    @Autowired
    private ICalendarDateService calendarDateService;

    @Autowired
    private CalendarDateCache calendarDateCache;

    /**
     * 在项目启动时，加载涨停板数据
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("- - - - - - - - - 开始加载本地缓存 - - - - - - - - -");
        String date = DateUtil.getToday();
        ztPoolCache.getZTPoolInCacheByDate(date);
        String lastWorkDay = calendarDateService.selectLastWorkDay(date);
        ztPoolCache.getZTPoolInCacheByDate(lastWorkDay);
        calendarDateCache.getCalendarDateEntityByDate(date);
        log.info("- - - - - - - - - 结束加载本地缓存 - - - - - - - - -");
    }
}























