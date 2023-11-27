package com.jcy.tradingstrategies.service.cache;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.dao.CalendarDateDao;
import com.jcy.tradingstrategies.dao.ZTPoolDao;
import com.jcy.tradingstrategies.domain.entity.CalendarDateEntity;
import com.jcy.tradingstrategies.domain.entity.ZTPoolEntity;
import com.jcy.tradingstrategies.service.ICalendarDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalendarDateCache {

    @Autowired
    private CalendarDateDao calendarDateDao;

    @Cacheable(cacheNames = "calendarDate", key = "'calendarDate:'+#date")
    public CalendarDateEntity getCalendarDateEntityByDate(String date) {
        LambdaQueryWrapper<CalendarDateEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CalendarDateEntity::getDate,date);
        return calendarDateDao.selectOne(lqw);
    }

    @CacheEvict(cacheNames = "calendarDate", key = "'calendarDate:'+#date")
    public void evictCalendarDateEntityByDate(String date) {
    }
}
































