package com.jcy.tradingstrategies.config.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程池异常处理类
 */
@Slf4j
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    /**
     * Method invoked when the given thread terminates due to the
     * given uncaught exception.
     * <p>Any exception thrown by this method will be ignored by the
     * Java Virtual Machine.
     *
     * @param t the thread
     * @param e the exception
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("Exception in thread" + t.getName(), e);
    }
}





































