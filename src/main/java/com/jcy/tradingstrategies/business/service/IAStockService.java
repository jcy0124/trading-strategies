package com.jcy.tradingstrategies.business.service;

import java.util.List;
import java.util.Map;

public interface IAStockService {

    void insert(String response);

    String selectNameByCode(String code);

    Map<String, String> selectNameByCodeList(List<String> codeList);
}
