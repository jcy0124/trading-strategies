package com.jcy.tradingstrategies.common.util;

import org.joda.time.LocalTime;

import java.util.Date;

public class DateUtil {

    public static String convertToTimeFormat(String originalTime) {
        if (originalTime.length() != 6) {
            return "Invalid time format!";
        }

        String hour = originalTime.substring(0, 2);
        String minute = originalTime.substring(2, 4);
        String second = originalTime.substring(4);

        return hour + ":" + minute + ":" + second;
    }

    public static String getToday(){
        return cn.hutool.core.date.DateUtil.formatDate(new Date());
    }

    public static String getTime(){
        return new LocalTime().toString().substring(0, 8);
    }
}
