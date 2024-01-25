package com.jcy.tradingstrategies.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(4)
public class LastCommandLineRunner implements CommandLineRunner {

    private static final String SWAGGER_URL = "http://127.0.0.1:8080/doc.html#/home";

    private static final String WEB_URL = "http://127.0.0.1:8080/templates/index.html";

    @Override
    public void run(String... args) throws Exception {
        log.info("- - - - - - - - - 项目启动成功！！！- - - - - - - - -");
        log.info("- - - - - - - - - 接口文档地址：{}",SWAGGER_URL);
        log.info("- - - - - - - - - 前端页面地址：{}",WEB_URL);
    }
}
