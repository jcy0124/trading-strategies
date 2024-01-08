package com.jcy.tradingstrategies.common.aop;


import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.jcy.tradingstrategies.common.annotation.DateValid;
import com.jcy.tradingstrategies.business.service.ICalendarDateService;
import com.jcy.tradingstrategies.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
@Aspect
@Slf4j
public class DateValidAop {

    public static final String DAY_REGEX = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    @Autowired
    private ICalendarDateService calendarDateService;

    @Pointcut("@annotation(com.jcy.tradingstrategies.common.annotation.DateValid)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = jp.getArgs();
        if (!method.isAnnotationPresent(DateValid.class)) {
            return;
        }

        DateValid dateValid = method.getAnnotation(DateValid.class);
        String afterTime = dateValid.afterTime();
        for (Object arg : args) {
            validDate(arg, afterTime);
        }

    }

    private void validDate(Object arg, String afterTime) {
        String date = arg.toString();

        if (!ReUtil.isMatch(DAY_REGEX, date)) {
            throw new RuntimeException("日期格式不正确哦~~，请正确输入，如【2023-11-23】");
        }

        String today = DateUtil.getToday();
        if (date.compareTo(today) == 1) {
            throw new RuntimeException("亲亲我不能预知未来哦~~");
        }

        String now = DateUtil.getTime();
        if (StrUtil.equals(date, today) && afterTime.compareTo(now) > 0) {
            throw new RuntimeException("当天数据需要【" + afterTime + "】之后才能获取哦~~");
        }

        boolean isWorkDay = calendarDateService.selectWorkDayByDate(date);
        if (!isWorkDay) {
            throw new RuntimeException("【" + date + "】不是股市交易日哦~~~");
        }
    }


}
























