package com.jcy.tradingstrategies.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.business.service.IWudangService;
import com.jcy.tradingstrategies.business.service.adaptor.SSGPAdaptor;
import com.jcy.tradingstrategies.common.constant.BaseConstant;
import com.jcy.tradingstrategies.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WudangServiceImpl implements IWudangService {

    @Autowired
    private IBaseService baseService;


    @Override
    public SSGPDto getSSGP(String code) {
        String response = baseService.getSSJGResp(code);
        JSONObject data = JsonUtil.getData(response, BaseConstant.SSGP);
        return SSGPAdaptor.buildSSGPDto(data,code);
    }
}
