package com.jcy.tradingstrategies.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum ChangeMarkEnum {

    RISE("1", "上涨"),
    FELL("2", "下跌"),
    BID("4","集合竞价");


    private final String code;
    private final String msg;

    private static Map<String, ChangeMarkEnum> cache;

    static {
        cache = Arrays.stream(ChangeMarkEnum.values()).collect(Collectors.toMap(ChangeMarkEnum::getCode, Function.identity()));
    }

    public static ChangeMarkEnum of(String code) {
        return cache.get(code);
    }
}
