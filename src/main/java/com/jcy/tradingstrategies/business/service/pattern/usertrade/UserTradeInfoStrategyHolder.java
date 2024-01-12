package com.jcy.tradingstrategies.business.service.pattern.usertrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserTradeInfoStrategyHolder {

    public static final String BUY = "buyStrategy";
    public static final String HOLD = "holdStrategy";
    public static final String SELL = "sellStrategy";


    @Autowired
    private Map<String, UserTradeInfoStrategy> strategyMap;

    public UserTradeInfoStrategy of(String codeStatus) {
        switch (codeStatus) {
            case "1":
                return strategyMap.get(BUY);
            case "2":
                return strategyMap.get(HOLD);
            case "3":
                return strategyMap.get(SELL);
        }
        return null;
    }
}