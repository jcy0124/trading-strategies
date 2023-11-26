package com.jcy.tradingstrategies.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {


    // 加法
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    // 减法
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    // 乘法
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    // 除法
    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        // 设置除法精度和舍入模式
        int scale = 10; // 设置小数位数
        return a.divide(b, scale, RoundingMode.HALF_UP); // 使用四舍五入
    }

    // 比较大小
    //a > b 1
    //a = b 0
    //a < b -1
    public static int compare(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }

    // 舍入
    public static BigDecimal round(BigDecimal value, int scale, RoundingMode roundingMode) {
        return value.setScale(scale, roundingMode);
    }

}