package com.jcy.tradingstrategies.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateValid {

    String afterTime();
}
