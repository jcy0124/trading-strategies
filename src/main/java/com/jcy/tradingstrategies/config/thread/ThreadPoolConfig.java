package com.jcy.tradingstrategies.config.thread;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {
    /**
     * 项目共用线程池
     */
    public static final String TRADING_STRATEGIES = "trading-strategies-executor";


    @Override
    public Executor getAsyncExecutor() {
        return tradingExecutor();
    }

    /**
     * spring的线程池，自带优雅停机功能，在项目关闭时，会等待线程池中的任务执行完成之后在进行停机
     *
     * @return
     */
    @Bean(TRADING_STRATEGIES) //线程池名字
    @Primary
    public ThreadPoolTaskExecutor tradingExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //线程池优雅停机 spring的线程池继承了DisposableBean，将线程池交由spring管理，在destroy方法中调用了showdown方法，实现优雅停机
        executor.setWaitForTasksToCompleteOnShutdown(true);

        executor.setCorePoolSize(8);   //核心线程
        executor.setMaxPoolSize(8);    //最大线程
        executor.setQueueCapacity(200); //等待队列
        executor.setThreadNamePrefix("trading-strategies-executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//拒绝策略：满了调用线程执行，认为重要任务

        //线程池的异常捕获
        executor.setThreadFactory(new MyThreadFactory(executor));

        executor.initialize();
        return executor;

    }
}





































