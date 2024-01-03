//package com.jcy.tradingstrategies.init;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Map;
//
//@Component
//@Order(2)
//@Slf4j
//public class InitCacheCommandLineRunner implements CommandLineRunner {
//
//    @Resource
//    ApplicationContext applicationContext;
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            log.info("- - - - - - - - - 开始加载redis缓存 - - - - - - - - -");
//            Map<String, AbstractCache> beanMap = applicationContext.getBeansOfType(AbstractCache.class);
//            if (beanMap.isEmpty()) {
//                return;
//            }
//            for (Map.Entry<String, AbstractCache> entry : beanMap.entrySet()) {
//                AbstractCache abstractCache = applicationContext.getBean(entry.getValue().getClass());
//                abstractCache.reloadCache();
//            }
//        } finally {
//            log.info("- - - - - - - - - 结束加载redis缓存 - - - - - - - - -");
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
