package com.jcy.tradingstrategies.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jcy.tradingstrategies.business.domain.entity.AStockEntity;

import java.util.List;
import java.util.Map;

public interface IAStockService {

    void insert(String response);

    String selectNameByCode(String code);

    Map<String, String> selectNameByCodeList(List<String> codeList);

    IPage<AStockEntity> page(Map<String, String> map);
}
