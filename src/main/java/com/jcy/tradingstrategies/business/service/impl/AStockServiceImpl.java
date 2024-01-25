package com.jcy.tradingstrategies.business.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcy.tradingstrategies.business.dao.AStockDao;
import com.jcy.tradingstrategies.business.domain.entity.AStockEntity;
import com.jcy.tradingstrategies.business.service.IAStockService;
import com.jcy.tradingstrategies.business.service.adaptor.AStockAdaptor;
import com.jcy.tradingstrategies.common.constant.BaseConstant;
import com.jcy.tradingstrategies.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

        JSONArray data = JsonUtil.getDataArray(response, BaseConstant.QBGP);
        List<AStockEntity> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            AStockEntity aStockEntity = AStockAdaptor.buildAStockEntity(jsonDetailInfo);
            if (Objects.isNull(aStockEntity)) {
                continue;
            }

            list.add(aStockEntity);
//            if (list.size() >= MAX_INSERT_SIZE) {
//                aStockDao.insertBatch(list);
//                list.clear();
//            }
        }

        list = list.stream().peek(item -> item.setName(item.getName().replaceAll(" ", ""))).sorted(Comparator.comparing(AStockEntity::getCode)).collect(Collectors.toList());

        aStockDao.deleteAll();
        aStockDao.insertBatch(list);
    }

    @Override
    public String selectNameByCode(String code) {
        LambdaQueryWrapper<AStockEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AStockEntity::getCode, code);
        lqw.select(AStockEntity::getName);
        return aStockDao.selectOne(lqw).getName();
    }

    @Override
    public Map<String, String> selectNameByCodeList(List<String> codeList) {
        LambdaQueryWrapper<AStockEntity> lqw = new LambdaQueryWrapper<>();
        lqw.in(AStockEntity::getCode, codeList);
        List<AStockEntity> aStockEntities = aStockDao.selectList(lqw);
        return aStockEntities.stream().collect(Collectors.toMap(AStockEntity::getCode, AStockEntity::getName));
    }

    @Override
    public IPage<AStockEntity> page(Map<String, String> map) {
        Long page = Long.valueOf(map.get("page"));
        String name = map.get("name");

        Page<AStockEntity> pageInfo = new Page<>(page, 10L);

        LambdaQueryWrapper<AStockEntity> lqw = new LambdaQueryWrapper<>();
        lqw.like(StrUtil.isNotBlank(name), AStockEntity::getName, name);

        return aStockDao.selectPage(pageInfo, lqw);
    }

    @Override
    public AStockEntity getByCode(String code) {
        LambdaQueryWrapper<AStockEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AStockEntity::getCode,code);
        return aStockDao.selectOne(lqw);
    }
}





















