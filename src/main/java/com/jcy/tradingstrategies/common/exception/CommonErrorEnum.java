package com.jcy.tradingstrategies.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommonErrorEnum implements ErrorEnum{

    BUSINESS_ERROR(0,"{0}"),
    SYSTEM_ERROR(-1,"系统异常"),
    PARAM_INVALID(-2,"参数校验失败"),
    LOCK_LIMIT(-3,"请求太频繁了，请稍后再试"),
    ;

    private final Integer code;
    private final String msg;

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }

    public static void main(String[] args) {
    }

}
