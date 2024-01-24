package com.jcy.tradingstrategies.business.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcy.tradingstrategies.business.dao.PriceWarningDao;
import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;
import com.jcy.tradingstrategies.business.domain.entity.PriceWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.PriceWarningReq;
import com.jcy.tradingstrategies.business.service.IAStockService;
import com.jcy.tradingstrategies.business.service.IPriceWarningService;
import com.jcy.tradingstrategies.business.service.IWudangService;
import com.jcy.tradingstrategies.common.exception.BusinessException;
import com.jcy.tradingstrategies.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Autowired
    private IWudangService wudangService;



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

    @Override
    public void updateCurrent(Integer id, BigDecimal current) {
        priceWarningDao.updateCurrent(id, current);
    }

    @Override
    public IPage<PriceWarningEntity> page(Map<String, String> map) {
        Long page = Long.valueOf(map.get("page"));

        String name = map.get("name");
        String isAlert = map.get("isAlert");

        LambdaQueryWrapper<PriceWarningEntity> lqw = new LambdaQueryWrapper<>();
        lqw.orderByAsc(PriceWarningEntity::getIsAlert).orderByDesc(PriceWarningEntity::getTime);
        lqw.like(StrUtil.isNotBlank(name),PriceWarningEntity::getName,name);
        lqw.eq(StrUtil.isNotBlank(isAlert),PriceWarningEntity::getIsAlert,isAlert);

        Page<PriceWarningEntity> pageInfo = new Page<>(page, 10L);

        return priceWarningDao.selectPage(pageInfo,lqw);
    }

    @Override
    public void insert(PriceWarningReq req) {
        String name = iaStockService.selectNameByCode(req.getCode());
        String time = DateUtil.getToday();
        req.setName(name);
        priceWarningDao.insertOne(req, time);
    }

    @Override
    public void delete(String id) {
        priceWarningDao.deleteById(id);
    }

    @Override
    public SSGPDto findKline(String code) {
        SSGPDto ssgp = wudangService.getSSGP(code);
        return ssgp;
    }

    @Override
    public PriceWarningEntity getById(String id) {
        return priceWarningDao.selectById(id);
    }

    @Override
    public void edit(PriceWarningReq req) {
        priceWarningDao.update(req);
    }
}












