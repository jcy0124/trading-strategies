package com.jcy.tradingstrategies.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.adaptor.LBAdaptor;
import com.jcy.tradingstrategies.adaptor.ZTPoolAdaptor;
import com.jcy.tradingstrategies.constant.BaseConstant;
import com.jcy.tradingstrategies.dao.ZTPoolDao;
import com.jcy.tradingstrategies.dto.ELBDto;
import com.jcy.tradingstrategies.dto.LBDto;
import com.jcy.tradingstrategies.dto.ZTPoolDto;
import com.jcy.tradingstrategies.entity.ZTPoolEntity;
import com.jcy.tradingstrategies.service.ICalendarDataService;
import com.jcy.tradingstrategies.service.IZTPoolService;
import com.jcy.tradingstrategies.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ZTPoolServiceImpl implements IZTPoolService {

    @Autowired
    private ZTPoolDao ztPoolDao;

    @Autowired
    private ICalendarDataService calendarDataService;

    @Override
    public String isExistByDate(String date) {
        return ztPoolDao.isExistByDate(date);
    }


    @Override
    public Integer insert(String response, String date) {

        JSONArray data = JsonUtil.getData(response, BaseConstant.ZTB);

        List<ZTPoolEntity> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            ZTPoolEntity ztPoolEntity = ZTPoolAdaptor.buildZTPoolEntity(jsonDetailInfo);
            list.add(ztPoolEntity);
        }

        log.info("【{}】一共有：{}只股票涨停", date, list.size());
        for (ZTPoolEntity ztPoolEntity : list) {
            String code = ztPoolEntity.getCode();
            if (code.startsWith("688")) {
                continue;
            }
            ztPoolDao.insert(ztPoolEntity);
        }
        return list.size();
    }

    @Override
    public List<ZTPoolDto> selectByDate(String date) {
        LambdaQueryWrapper<ZTPoolEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ZTPoolEntity::getTime, date)
                .select(ZTPoolEntity::getId,    //自增id
                        ZTPoolEntity::getCode,  //代码
                        ZTPoolEntity::getName,  //名称
                        ZTPoolEntity::getTurnoverRatio, //换手率
                        ZTPoolEntity::getFirstCeilingTime,  //涨停时间
                        ZTPoolEntity::getLbNum, //连板次数
                        ZTPoolEntity::getTime   //时间
                );
        List<ZTPoolEntity> ztPoolEntityList = ztPoolDao.selectList(lqw);
        if (CollectionUtil.isEmpty(ztPoolEntityList)) {
            return Collections.EMPTY_LIST;
        }
        List<ZTPoolDto> ztPoolDtoList = new ArrayList<>();
        for (ZTPoolEntity ztPoolEntity : ztPoolEntityList) {
            ZTPoolDto ztPoolDto = BeanUtil.copyProperties(ztPoolEntity, ZTPoolDto.class);
            ztPoolDtoList.add(ztPoolDto);
        }
        return ztPoolDtoList;
    }

    /**
     * 获取二连板股票
     *
     * @param date
     * @return
     */
    @Override
    public List<ELBDto> gerErBan(String date) {

        //获取当天涨停股票
        LambdaQueryWrapper<ZTPoolEntity> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(ZTPoolEntity::getTime, date);
        List<ZTPoolEntity> ztPoolEntityList = ztPoolDao.selectList(lqw1);
        if (CollectionUtil.isEmpty(ztPoolEntityList)) {
            return Collections.EMPTY_LIST;
        }

        //上一个工作日日期
        String lastWorkDay = calendarDataService.selectLastWorkDay(date);
        //前一个工作日日期
        String dayBeforeLastWorkDay = calendarDataService.selectLastWorkDay(lastWorkDay);

        List<ELBDto> result = new ArrayList<>();

        //遍历当天的涨停板股票
        for (ZTPoolEntity ztPoolEntity : ztPoolEntityList) {
            //根据lastWorkDay和code作为条件查询zt_pool
            LambdaQueryWrapper<ZTPoolEntity> lqw2 = new LambdaQueryWrapper<>();
            lqw2.eq(ZTPoolEntity::getTime, lastWorkDay)
                    .eq(ZTPoolEntity::getCode, ztPoolEntity.getCode());
            ZTPoolEntity ztPoolEntityLastWorkDay = ztPoolDao.selectOne(lqw2);

            if (Objects.isNull(ztPoolEntityLastWorkDay)) {
                continue;
            }

            //查看前一天是否涨停
            LambdaQueryWrapper<ZTPoolEntity> lwq3 = new LambdaQueryWrapper<>();
            lwq3.eq(ZTPoolEntity::getTime, dayBeforeLastWorkDay)
                    .eq(ZTPoolEntity::getCode, ztPoolEntity.getCode());
            ZTPoolEntity ztPoolEntityDayBeforeLastWorkDay = ztPoolDao.selectOne(lwq3);

            if (Objects.isNull(ztPoolEntityDayBeforeLastWorkDay)) {
                ELBDto elbDto = BeanUtil.copyProperties(ztPoolEntity, ELBDto.class);
                result.add(elbDto);
            }
        }

        return result;
    }

    @Override
    public List<LBDto> getLBan(String date) {

        LambdaQueryWrapper<ZTPoolEntity> lqw1 = new LambdaQueryWrapper();
        lqw1.eq(ZTPoolEntity::getTime, date);
        //【date】当天的涨停板股票
        List<ZTPoolEntity> ztPoolEntityList = ztPoolDao.selectList(lqw1);

        List<LBDto> list = new ArrayList<>();
        for (ZTPoolEntity ztPoolEntity : ztPoolEntityList) {
            String code = ztPoolEntity.getCode();
            List<ZTPoolEntity> LBList = ztPoolDao.selectLBList(code, date);
            LBDto lbDto = LBAdaptor.buildLBDto(LBList);
            list.add(lbDto);
        }
        list = list.stream().sorted(Comparator.comparing(LBDto::getLbcs)).collect(Collectors.toList());
        return list;
    }
}





















































