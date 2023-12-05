package com.jcy.tradingstrategies.service;

import java.util.List;

public interface ICalendarDateService {

    void insert();

    String selectLastWorkDay(String date);

    boolean selectWorkDayByDate(String date);

    List<String> selectWorkDateBetween(String startDate, String endDate);

    String selectNextWorkDay(String startDate);
}
