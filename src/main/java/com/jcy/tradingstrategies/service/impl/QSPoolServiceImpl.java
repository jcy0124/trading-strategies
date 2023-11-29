package com.jcy.tradingstrategies.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.adaptor.QSPoolAdaptor;
import com.jcy.tradingstrategies.constant.BaseConstant;
import com.jcy.tradingstrategies.dao.QSPoolDao;
import com.jcy.tradingstrategies.domain.dto.QSPoolDto;
import com.jcy.tradingstrategies.domain.entity.QSPoolEntity;
import com.jcy.tradingstrategies.service.IQSPoolService;
import com.jcy.tradingstrategies.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class QSPoolServiceImpl implements IQSPoolService {

    @Autowired
    private QSPoolDao qsPoolDao;

    @Override
    public void insert(String response, String date) {

        JSONArray data = JsonUtil.getData(response, BaseConstant.QSG);

        List<QSPoolEntity> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            QSPoolEntity qsPoolEntity = QSPoolAdaptor.buildQSPoolEntity(jsonDetailInfo);
            list.add(qsPoolEntity);
        }

        log.info("【{}】一共有：{}只股票强势", date, list.size());
        for (QSPoolEntity qsPoolEntity : list) {
            qsPoolDao.insert(qsPoolEntity);
        }
    }

    @Override
    public List<QSPoolDto> selectByDate(String date) {
        LambdaQueryWrapper<QSPoolEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(QSPoolEntity::getTime, date)
                .select(QSPoolEntity::getId,    //自增id
                        QSPoolEntity::getCode,  //代码
                        QSPoolEntity::getName,  //名称
                        QSPoolEntity::getChangeRatio,   //涨跌幅
                        QSPoolEntity::getTurnoverRatio, //换手率
                        QSPoolEntity::getLastPrice, //最新价格
                        QSPoolEntity::getAmount,    //成交额
                        QSPoolEntity::getZttj,  //涨停统计
                        QSPoolEntity::getZs,    //涨速
                        QSPoolEntity::getNh,    //是否新高
                        QSPoolEntity::getLb,    //量比
                        QSPoolEntity::getTime   //时间
                );
        List<QSPoolEntity> qsPoolEntityList = qsPoolDao.selectList(lqw);
        if (CollectionUtil.isEmpty(qsPoolEntityList)) {
            return Collections.EMPTY_LIST;
        }

        List<QSPoolDto> qsPoolDtoList = BeanUtil.copyToList(qsPoolEntityList,QSPoolDto.class);

        return qsPoolDtoList;
    }
}






























