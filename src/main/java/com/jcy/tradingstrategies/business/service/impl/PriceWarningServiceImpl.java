package com.jcy.tradingstrategies.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.business.dao.PriceWarningDao;
import com.jcy.tradingstrategies.business.domain.entity.PriceWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.PriceWarningReq;
import com.jcy.tradingstrategies.business.service.IAStockService;
import com.jcy.tradingstrategies.business.service.IPriceWarningService;
import com.jcy.tradingstrategies.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PriceWarningServiceImpl implements IPriceWarningService {

    @Autowired
    private PriceWarningDao priceWarningDao;

    @Autowired
    private IAStockService iaStockService;


    @Override
    public List<PriceWarningEntity> selectNeedWarningToday(String date) {
        LambdaQueryWrapper<PriceWarningEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PriceWarningEntity::getTime, date);
        lqw.eq(PriceWarningEntity::getIsAlert, "0");
        return priceWarningDao.selectList(lqw);
    }

    @Override
    public void updateIsAlert(PriceWarningEntity priceWarningEntity) {
        priceWarningEntity.setIsAlert("1");
        priceWarningDao.updateAlert(priceWarningEntity.getId());
    }

    @Override
    public void insertBatch(List<PriceWarningReq> list) {

        Map<String, String> codeNameMap = iaStockService.selectNameByCodeList(list.stream().map(PriceWarningReq::getCode).collect(Collectors.toList()));

        String time = DateUtil.getToday();

        for (PriceWarningReq req : list) {
            String name = codeNameMap.get(req.getCode());
            req.setName(name);
        }

        priceWarningDao.insertBatch(list, time);
    }

    @Override
    public List<PriceWarningEntity> selectNoAlertList() {
        LambdaQueryWrapper<PriceWarningEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PriceWarningEntity::getIsAlert, "0");
        return priceWarningDao.selectList(lqw);
    }
}












