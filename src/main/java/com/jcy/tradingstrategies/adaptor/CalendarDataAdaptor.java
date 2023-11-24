package com.jcy.tradingstrategies.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.domain.entity.CalendarDataEntity;

public class CalendarDataAdaptor {

    public static CalendarDataEntity buildCalendarData(JSONObject jsonDetailInfo) {
        String date = jsonDetailInfo.getString("日期");
        String week = jsonDetailInfo.getString("周");
        String workDay = jsonDetailInfo.getString("是否工作日（0：否，1：是）");

        CalendarDataEntity calendarDataEntity = new CalendarDataEntity();
        calendarDataEntity.setDate(date);
        calendarDataEntity.setWeek(week);
        calendarDataEntity.setWorkDay(workDay);
        return calendarDataEntity;
    }
}
