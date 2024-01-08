package com.jcy.tradingstrategies.business.service.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.business.domain.entity.CalendarDateEntity;

public class CalendarDateAdaptor {

    public static CalendarDateEntity buildCalendarDate(JSONObject jsonDetailInfo) {
        String date = jsonDetailInfo.getString("date");
        String week = jsonDetailInfo.getString("周");
        String workDay = jsonDetailInfo.getString("is");

        CalendarDateEntity calendarDateEntity = new CalendarDateEntity();
        calendarDateEntity.setDate(date);
        calendarDateEntity.setWeek(week);
        calendarDateEntity.setWorkDay(workDay);
        return calendarDateEntity;
    }
}
