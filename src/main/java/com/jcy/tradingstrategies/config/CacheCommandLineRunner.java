package com.jcy.tradingstrategies.config;


import com.jcy.tradingstrategies.service.ICalendarDateService;
import com.jcy.tradingstrategies.service.cache.CalendarDateCache;
import com.jcy.tradingstrategies.service.cache.ZTPoolCache;
import com.jcy.tradingstrategies.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(3)
public class CacheCommandLineRunner implements CommandLineRunner {


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
        try {
            log.info("- - - - - - - - - 开始加载本地缓存 - - - - - - - - -");
            String date = DateUtil.getToday();
            String lastWorkDay = calendarDateService.selectLastWorkDay(date);
            log.info("- - - - - - - - - 加载今日涨停板 - - - - - - - - -");
            ztPoolCache.getZTPoolInCacheByDate(date);
            log.info("- - - - - - - - - 加载上一个工作日涨停板 - - - - - - - - -");
            ztPoolCache.getZTPoolInCacheByDate(lastWorkDay);
            log.info("- - - - - - - - - 加载当日日期 - - - - - - - - -");
            calendarDateCache.getCalendarDateEntityByDate(date);
            log.info("- - - - - - - - - 加载上一个工作日日期 - - - - - - - - -");
            calendarDateCache.getCalendarDateEntityByDate(lastWorkDay);
        } finally {
            log.info("- - - - - - - - - 结束加载本地缓存 - - - - - - - - -");
        }
    }
}























