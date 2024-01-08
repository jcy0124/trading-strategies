package com.jcy.tradingstrategies.common.exception;


import com.jcy.tradingstrategies.common.base.Result;
import com.jcy.tradingstrategies.common.base.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionAdaptController {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("统一异常处理：MethodArgumentNotValidException, 异常原因：", ex);

        String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        // 防止抛出空指针异常
        assert defaultMessage != null;

        return Result.fail(ResultCode.ERROR, ex.getBindingResult().getAllErrors().stream().map((error) -> {
            String errorMessage = error.getDefaultMessage();
            return errorMessage;
        }).collect(Collectors.joining(",")));

    }

    @ExceptionHandler({RuntimeException.class})
    public Result runTimeException(RuntimeException exception) {
        log.error("统一异常处理：runtimeException, 异常原因：", exception);
        return Result.fail(ResultCode.ERROR, exception.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public Result exception(Exception exception) {
        log.error("统一异常处理：Exception, 异常原因：", exception);
        return Result.fail(ResultCode.ERROR, exception.getMessage());
    }
}































