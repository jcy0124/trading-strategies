package com.jcy.tradingstrategies.business.service.cache;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.business.dao.CalendarDateDao;
import com.jcy.tradingstrategies.business.domain.entity.CalendarDateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

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
































