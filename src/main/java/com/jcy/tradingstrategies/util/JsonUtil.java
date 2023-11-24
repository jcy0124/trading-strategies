package com.jcy.tradingstrategies.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class JsonUtil {

    public static JSONArray getData(String response, String type) {
        JSONObject jsonObj = JSON.parseObject(response);
        Integer code = jsonObj.getInteger("code");
        String msg = jsonObj.getString("msg");
        if (Objects.isNull(code) || 20000 != code) {
            throw new RuntimeException("获取" + type + "异常:" + msg);
        }
        if (Objects.isNull(msg) || !StrUtil.equals("success", msg)) {
            throw new RuntimeException("获取" + type + "异常:" + msg);
        }

        JSONArray data = JSONObject.parseArray(jsonObj.getString("data"));
        if (Objects.isNull(data)) {
            throw new RuntimeException("今日无" + type + "股票");
        }
        return data;
    }
}
