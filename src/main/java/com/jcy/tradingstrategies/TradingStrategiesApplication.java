package com.jcy.tradingstrategies;

import com.jcy.tradingstrategies.config.app.GracefulShutdownTomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class TradingStrategiesApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TradingStrategiesApplication.class, args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(TradingStrategiesApplication.class);
        builder.headless(false).run(args);
    }

    @Bean
    public ServletWebServerFactory servletContainer(@Autowired GracefulShutdownTomcat gracefulShutdownTomcat){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(gracefulShutdownTomcat);
        return tomcat;
    }
}
