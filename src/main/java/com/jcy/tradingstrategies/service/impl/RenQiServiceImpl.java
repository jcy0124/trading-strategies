package com.jcy.tradingstrategies.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.constant.BaseConstant;
import com.jcy.tradingstrategies.domain.dto.RenQiDto;
import com.jcy.tradingstrategies.service.IRenQiService;
import com.jcy.tradingstrategies.service.adaptor.RenQiAdaptor;
import com.jcy.tradingstrategies.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RenQiServiceImpl implements IRenQiService {


    @Override
    public List<RenQiDto> convert(String response) {
        JSONArray data = JsonUtil.getData(response, BaseConstant.RQGP);

        List<RenQiDto> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            RenQiDto renQiDto = RenQiAdaptor.buildRenQiDto(jsonDetailInfo);
            list.add(renQiDto);
        }
        return list;
    }
}
















