package com.jcy.tradingstrategies.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.domain.entity.CalendarDateEntity;

public class CalendarDateAdaptor {

    public static CalendarDateEntity buildCalendarDate(JSONObject jsonDetailInfo) {
        String date = jsonDetailInfo.getString("日期");
        String week = jsonDetailInfo.getString("周");
        String workDay = jsonDetailInfo.getString("是否工作日（0：否，1：是）");

        CalendarDateEntity calendarDateEntity = new CalendarDateEntity();
        calendarDateEntity.setDate(date);
        calendarDateEntity.setWeek(week);
        calendarDateEntity.setWorkDay(workDay);
        return calendarDateEntity;
    }
}
