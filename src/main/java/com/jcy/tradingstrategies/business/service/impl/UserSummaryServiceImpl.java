package com.jcy.tradingstrategies.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.business.dao.UserSummaryDao;
import com.jcy.tradingstrategies.business.domain.entity.UserSummaryEntity;
import com.jcy.tradingstrategies.business.service.IUserSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class UserSummaryServiceImpl implements IUserSummaryService {

    @Autowired
    private UserSummaryDao userSummaryDao;


    @Override
    public UserSummaryEntity insert(Integer id) {
        UserSummaryEntity userSummaryEntity = new UserSummaryEntity();
        userSummaryEntity.setUserTradeInfoId(id);
        userSummaryDao.add(userSummaryEntity);
        return userSummaryEntity;
    }

    @Override
    public UserSummaryEntity getByUserTradeId(String userTradeId) {
        LambdaQueryWrapper<UserSummaryEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserSummaryEntity::getUserTradeInfoId, userTradeId);
        return userSummaryDao.selectOne(lqw);
    }

    @Override
    public void update(Map<String, String> map) {
        userSummaryDao.updateSummary(map);
    }
}










