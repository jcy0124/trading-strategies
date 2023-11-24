package tradingstrategies.service;

public interface ICalendarDataService {

    void insert();

    String selectLastWorkDay(String date);

    boolean selectWorkDayByDate(String date);
}
