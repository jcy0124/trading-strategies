package com.jcy.tradingstrategies.config;

import cn.hutool.core.util.StrUtil;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.business.service.ICalendarDateService;
import com.jcy.tradingstrategies.business.service.IZTPoolService;
import com.jcy.tradingstrategies.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(1)
public class ZTPoolCommandLineRunner implements CommandLineRunner {

    @Autowired
    private IZTPoolService ztPoolService;

    @Autowired
    private ICalendarDateService calendarDateService;

    @Autowired
    private IBaseService baseService;

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("- - - - - - - - - 开始新增当日涨停板 - - - - - - - - -");
            String today = DateUtil.getToday();
            boolean isWorkDay = calendarDateService.selectWorkDayByDate(today);
            if (!isWorkDay) {
                log.info("- - - - - - - - - 非工作日 - - - - - - - - -");
                return;
            }

            String now = DateUtil.getTime();
            if ("15:30:00".compareTo(now) >= 0) {
                log.info("- - - - - - - - - 非三点半之后 - - - - - - - - -");
                return;
            }

            String isExists = ztPoolService.isExistByDate(today);
            if (StrUtil.isNotBlank(isExists)) {
                log.info("- - - - - - - - - 数据已同步 - - - - - - - - -");
                return;
            }

            String resp = baseService.getZTPoolResp(today);
            ztPoolService.insert(resp, today);
        } catch (Exception e) {
            log.error("- - - - - - - - - http同步涨停板报错 - - - - - - - - -", e);
        } finally {
            log.info("- - - - - - - - - 结束新增当日涨停板 - - - - - - - - -");
        }
    }
}




