package com.jcy.tradingstrategies.business.service;

import java.util.List;

public interface ICalendarDateService {

    void insert();

    String selectLastWorkDay(String date);

    boolean selectWorkDayByDate(String date);

    String selectNextWorkDay(String startDate);

    List<String> selectWorkDateBetween(String startDate, String endDate);
}
