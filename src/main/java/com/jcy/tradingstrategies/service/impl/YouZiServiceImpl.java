package com.jcy.tradingstrategies.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.adaptor.RenQiAdaptor;
import com.jcy.tradingstrategies.adaptor.YouZiAdaptor;
import com.jcy.tradingstrategies.constant.BaseConstant;
import com.jcy.tradingstrategies.domain.dto.RenQiDto;
import com.jcy.tradingstrategies.domain.dto.YouZiDto;
import com.jcy.tradingstrategies.service.IYouZiService;
import com.jcy.tradingstrategies.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class YouZiServiceImpl implements IYouZiService {

    @Override
    public List<YouZiDto> convert(String response) {

        JSONArray data = JsonUtil.getData(response, BaseConstant.YOUZI);

        List<YouZiDto> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            YouZiDto youZiDto = YouZiAdaptor.buildYouZiDto(jsonDetailInfo);
            list.add(youZiDto);
        }
        return list;
    }

}









