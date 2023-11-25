package com.jcy.tradingstrategies.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.adaptor.BaseKLineInfoAdaptor;
import com.jcy.tradingstrategies.constant.BaseConstant;
import com.jcy.tradingstrategies.dao.BaseKLineInfoDao;
import com.jcy.tradingstrategies.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.domain.entity.BaseKLineInfoEntity;
import com.jcy.tradingstrategies.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BaseKLineInfoImpl implements IBaseKLineInfoService {

    @Autowired
    private BaseKLineInfoDao baseKLineInfoDao;

    @Override
    public List<BaseKLineInfoDto> getBaseKLineInfo(String response) {

        System.out.println(response);
        JSONArray data = JsonUtil.getData(response, BaseConstant.KXXX);
        List<BaseKLineInfoDto> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            BaseKLineInfoDto baseKLineInfoDto = BaseKLineInfoAdaptor.buildBaseKLineInfo(jsonDetailInfo);
            list.add(baseKLineInfoDto);
        }

        list.forEach(System.out::println);
        return null;
    }
}




































