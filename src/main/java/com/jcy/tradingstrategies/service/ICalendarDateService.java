package com.jcy.tradingstrategies.service;

public interface ICalendarDateService {

    void insert();

    String selectLastWorkDay(String date);

    boolean selectWorkDayByDate(String date);
}
