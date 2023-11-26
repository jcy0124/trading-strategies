package com.jcy.tradingstrategies.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {


    protected Integer errorCode;

    protected String errorMsg;

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorCode = CommonErrorEnum.BUSINESS_ERROR.getErrorCode();
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMsg());
        this.errorMsg = errorEnum.getErrorMsg();
        this.errorCode = errorEnum.getErrorCode();
    }

}





















