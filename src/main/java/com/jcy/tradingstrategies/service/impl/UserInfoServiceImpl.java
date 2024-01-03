package com.jcy.tradingstrategies.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jcy.tradingstrategies.dao.UserInfoDao;
import com.jcy.tradingstrategies.domain.entity.UserInfoEntity;
import com.jcy.tradingstrategies.domain.vo.req.UserInfoInsertReq;
import com.jcy.tradingstrategies.domain.vo.req.UserInfoUpdateReq;
import com.jcy.tradingstrategies.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}



























