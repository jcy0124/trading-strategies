package com.jcy.tradingstrategies.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.business.dao.LowWarningDao;
import com.jcy.tradingstrategies.business.domain.entity.LowWarningEntity;
import com.jcy.tradingstrategies.business.domain.vo.req.LowWarningReq;
import com.jcy.tradingstrategies.business.service.ILowWarningService;
import com.jcy.tradingstrategies.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LowWarningServiceImpl implements ILowWarningService {

    @Autowired
    private LowWarningDao lowWarningDao;

    @Override
    public List<LowWarningEntity> selectNeedWarningToday(String date) {
        LambdaQueryWrapper<LowWarningEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(LowWarningEntity::getTime,date);
        lqw.eq(LowWarningEntity::getIsAlert,"0");
        return lowWarningDao.selectList(lqw);
    }

    @Override
    public void updateIsAlert(LowWarningEntity lowWarningEntity) {
        lowWarningEntity.setIsAlert("1");
        lowWarningDao.updateById(lowWarningEntity);
    }

    @Override
    public void insertBatch(List<LowWarningReq> list) {
        String time = DateUtil.getToday();
        lowWarningDao.insertBatch(list,time);
    }

    @Override
    public List<LowWarningEntity> selectNoAlertList() {
        LambdaQueryWrapper<LowWarningEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(LowWarningEntity::getIsAlert,"0");
        return lowWarningDao.selectList(lqw);
    }
}












