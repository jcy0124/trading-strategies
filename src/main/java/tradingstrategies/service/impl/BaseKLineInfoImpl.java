package tradingstrategies.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.adaptor.BaseKLineInfoAdaptor;
import com.jcy.tradingstrategies.dao.BaseKLineInfoDao;
import com.jcy.tradingstrategies.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.entity.BaseKLineInfoEntity;
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

        JSONArray data = JsonUtil.getData(response, "k线信息");
        List<BaseKLineInfoEntity> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            BaseKLineInfoEntity baseKLineInfoEntity = BaseKLineInfoAdaptor.buildBaseKLineInfo(jsonDetailInfo);
            list.add(baseKLineInfoEntity);
        }

        list.forEach(System.out::println);
        return null;
    }
}




































