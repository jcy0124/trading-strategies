package com.jcy.tradingstrategies.business.service.qsV2.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class StrategyHolder {

    public static final String FIRST_ZT_STRATEGY = "firstZtStrategy";
    @Autowired
    private Map<String,Strategy> strategyMap;

    public  Strategy of(Integer lbNum) {
        switch (lbNum) {
            case 1:
                return strategyMap.get(FIRST_ZT_STRATEGY);
        }
        return null;
    }
}

