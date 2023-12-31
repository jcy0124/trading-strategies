package com.jcy.tradingstrategies.business.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.business.service.adaptor.AStockAdaptor;
import com.jcy.tradingstrategies.common.constant.BaseConstant;
import com.jcy.tradingstrategies.business.dao.AStockDao;
import com.jcy.tradingstrategies.business.domain.entity.AStockEntity;
import com.jcy.tradingstrategies.business.service.IAStockService;
import com.jcy.tradingstrategies.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AStockServiceImpl implements IAStockService {

    public static final int MAX_INSERT_SIZE = 1000;

    @Autowired
    private AStockDao aStockDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
//    @Async(ThreadPoolConfig.TRADING_STRATEGIES)
    public void insert(String response) {

        aStockDao.deleteAll();

        JSONArray data = JsonUtil.getData(response, BaseConstant.QBGP);
        List<AStockEntity> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            AStockEntity aStockEntity = AStockAdaptor.buildAStockEntity(jsonDetailInfo);
            if (Objects.isNull(aStockEntity)) {
                continue;
            }

            list.add(aStockEntity);
            if (list.size() >= MAX_INSERT_SIZE) {
                aStockDao.insertBatch(list);
                list.clear();
            }
        }
        aStockDao.insertBatch(list);
    }
}





















