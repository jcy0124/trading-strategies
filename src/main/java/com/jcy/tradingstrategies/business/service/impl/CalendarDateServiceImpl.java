package com.jcy.tradingstrategies.business.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.business.dao.CalendarDateDao;
import com.jcy.tradingstrategies.business.domain.entity.CalendarDateEntity;
import com.jcy.tradingstrategies.business.service.ICalendarDateService;
import com.jcy.tradingstrategies.business.service.adaptor.CalendarDateAdaptor;
import com.jcy.tradingstrategies.business.service.cache.CalendarDateCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarDateServiceImpl implements ICalendarDateService {

    @Autowired
    private CalendarDateDao calendarDateDao;

    @Autowired
    private CalendarDateCache calendarDateCache;

    /**
     * 新增日期，日期在date.txt文件中
     */
    @Override
    public void insert() {

        String calendarJson = "";

        JSONArray jsonArray = JSONObject.parseArray(calendarJson);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonDetailInfo = jsonArray.getJSONObject(i);
            CalendarDateEntity calendarDateEntity = CalendarDateAdaptor.buildCalendarDate(jsonDetailInfo);
            calendarDateDao.insert(calendarDateEntity);
        }
    }

    /**
     * 获取上一个交易日日期
     *
     * @param date
     * @return
     */
    @Override
    public String selectLastWorkDay(String date) {
        LambdaQueryWrapper<CalendarDateEntity> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(CalendarDateEntity::getDate, date);
        CalendarDateEntity calendarDateEntity = calendarDateDao.selectOne(lqw1);

        List<CalendarDateEntity> calendarDateEntityList = calendarDateDao.selectLast14Day(calendarDateEntity.getId());

        String lastWorkDay = "";
        for (CalendarDateEntity dateEntity : calendarDateEntityList) {
            String workDay = dateEntity.getWorkDay();
            String week = dateEntity.getWeek();

            if ("1".equals(workDay) && !(StrUtil.equals(week, "星期六") || (StrUtil.equals(week, "星期日")))) {
                lastWorkDay = dateEntity.getDate();
                break;
            }
        }

        return lastWorkDay;
    }

    /**
     * 获取下一个工作日日期
     *
     * @param startDate
     * @return
     */
    @Override
    public String selectNextWorkDay(String startDate) {
        LambdaQueryWrapper<CalendarDateEntity> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(CalendarDateEntity::getDate, startDate);
        CalendarDateEntity calendarDateEntity = calendarDateDao.selectOne(lqw1);

        List<CalendarDateEntity> calendarDateEntityList = calendarDateDao.selectNext14Day(calendarDateEntity.getId());

        String nextWorkDay = "";
        for (CalendarDateEntity dateEntity : calendarDateEntityList) {
            String workDay = dateEntity.getWorkDay();
            String week = dateEntity.getWeek();

            if ("1".equals(workDay) && !(StrUtil.equals(week, "星期六") || (StrUtil.equals(week, "星期日")))) {
                nextWorkDay = dateEntity.getDate();
                break;
            }
        }

        return nextWorkDay;
    }

    /**
     * 是否为工作日
     *
     * @param date
     * @return
     */
    @Override
    public boolean selectWorkDayByDate(String date) {
        CalendarDateEntity calendarDateEntity = calendarDateCache.getCalendarDateEntityByDate(date);

        String workDay = calendarDateEntity.getWorkDay();
        String week = calendarDateEntity.getWeek();
        return StrUtil.equals(workDay, "1") && !(StrUtil.equals("星期六", week) || StrUtil.equals("星期日", week));
    }

    @Override
    public List<String> selectWorkDateBetween(String startDate, String endDate) {
        return calendarDateDao.selectWorkDateBetween(startDate, endDate);
    }
}