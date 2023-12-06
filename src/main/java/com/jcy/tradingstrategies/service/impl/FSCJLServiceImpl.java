package com.jcy.tradingstrategies.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.adaptor.FSCJLAdaptor;
import com.jcy.tradingstrategies.constant.BaseConstant;
import com.jcy.tradingstrategies.domain.dto.FSCJLDto;
import com.jcy.tradingstrategies.service.IFSCJLService;
import com.jcy.tradingstrategies.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@Slf4j
public class FSCJLServiceImpl implements IFSCJLService {


    @Override
    public List<FSCJLDto> getFSCJLResp(String response) {
        JSONArray data = JsonUtil.getData(response, BaseConstant.FSCJL);

        List<FSCJLDto> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            FSCJLDto fscjlDto = FSCJLAdaptor.buildFSCJLDto(jsonDetailInfo);
            list.add(fscjlDto);
        }

        list.sort(Comparator.comparing(FSCJLDto::getTime).reversed());

        return list;
    }
}














