package com.jcy.tradingstrategies.config.app;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class GracefulShutdownTomcat implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private volatile Connector connector;

    private final int shutdownAwaitSeconds = 30;


    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (connector == null) {
            return;
        }
        log.info("----- Tomcat 接收到停机指令，暂停接收新请求 -----");
        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                log.info("----- Tomcat 开始优雅停机 -----");
                long t0 = System.currentTimeMillis();
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(shutdownAwaitSeconds, TimeUnit.SECONDS)) {
                    log.error("Tomcat did not shutdown gracefully within {} seconds,now going to shutdown forcely", shutdownAwaitSeconds);
                    threadPoolExecutor.shutdown();
                } else {
                    long t1 = System.currentTimeMillis();
                    log.info("----- Tomcat 优雅停机成功，耗时 {} 毫秒 -----", (t1 - t0));
                }
            } catch (InterruptedException e) {
                log.error("----- tomcat 优雅停机时发生异常 -----", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}