package com.jcy.tradingstrategies.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.dao.UserInfoDao;
import com.jcy.tradingstrategies.domain.entity.UserInfoEntity;
import com.jcy.tradingstrategies.domain.vo.req.UserInfoInsertReq;
import com.jcy.tradingstrategies.domain.vo.req.UserInfoUpdateReq;
import com.jcy.tradingstrategies.service.IUserInfoService;
import com.jcy.tradingstrategies.util.BigDecimalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public void add(UserInfoInsertReq req) {
        UserInfoEntity userInfoEntity = BeanUtil.copyProperties(req, UserInfoEntity.class);
        userInfoEntity.setNowAmount(userInfoEntity.getStartAmount());
        userInfoDao.insert(userInfoEntity);
    }

    @Override
    public void update(UserInfoUpdateReq req) {
        UserInfoEntity userInfoEntity = BeanUtil.copyProperties(req, UserInfoEntity.class);

    }

    @Override
    public void updateAmount(String userName, String todayProfit) {
        LambdaQueryWrapper<UserInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserInfoEntity::getUserName, userName);
        lqw.orderByDesc(UserInfoEntity::getId);
        lqw.last("limit 1");
        UserInfoEntity userInfoEntity = userInfoDao.selectOne(lqw);
        BigDecimal nowAmount = new BigDecimal(userInfoEntity.getNowAmount());
        nowAmount = BigDecimalUtils.add(nowAmount, new BigDecimal(todayProfit));
        userInfoEntity.setNowAmount(nowAmount.toString());
        userInfoEntity.setChangeAmount(todayProfit);
        userInfoEntity.setId(null);
        userInfoDao.insert(userInfoEntity);
    }
}



























