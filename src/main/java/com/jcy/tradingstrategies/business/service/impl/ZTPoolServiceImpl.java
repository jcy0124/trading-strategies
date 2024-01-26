package com.jcy.tradingstrategies.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcy.tradingstrategies.business.domain.entity.AStockEntity;
import com.jcy.tradingstrategies.business.domain.vo.resp.ELBResp;
import com.jcy.tradingstrategies.business.service.adaptor.LBAdaptor;
import com.jcy.tradingstrategies.business.service.adaptor.ZTPoolAdaptor;
import com.jcy.tradingstrategies.common.constant.BaseConstant;
import com.jcy.tradingstrategies.business.dao.ZTPoolDao;
import com.jcy.tradingstrategies.business.domain.dto.ELBDto;
import com.jcy.tradingstrategies.business.domain.dto.LBDto;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.business.domain.entity.ZTPoolEntity;
import com.jcy.tradingstrategies.business.service.ICalendarDateService;
import com.jcy.tradingstrategies.business.service.IZTPoolService;
import com.jcy.tradingstrategies.business.service.cache.ZTPoolCache;
import com.jcy.tradingstrategies.common.util.DateUtil;
import com.jcy.tradingstrategies.common.util.JsonUtil;
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
    private ZTPoolCache ztPoolCache;

    @Autowired
    private ICalendarDateService calendarDateService;

    @Override
    public String isExistByDate(String date) {
        return ztPoolDao.isExistByDate(date);
    }


    @Override
    public Integer insert(String response, String date) {

        JSONArray data = JsonUtil.getDataArray(response, BaseConstant.ZTB);

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
        List<ZTPoolEntity> ztPoolEntityList = ztPoolCache.getZTPoolInCacheByDate(date);

        if (CollectionUtil.isEmpty(ztPoolEntityList)) {
            return Collections.EMPTY_LIST;
        }

        List<ZTPoolDto> ztPoolDtoList = BeanUtil.copyToList(ztPoolEntityList, ZTPoolDto.class);

        return ztPoolDtoList.stream().filter(item -> Objects.equals(1, item.getLbNum())).collect(Collectors.toList());
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
        List<ZTPoolEntity> ztPoolEntityList = ztPoolCache.getZTPoolInCacheByDate(date);

        if (CollectionUtil.isEmpty(ztPoolEntityList)) {
            return Collections.EMPTY_LIST;
        }

        //上一个工作日日期
        String lastWorkDay = calendarDateService.selectLastWorkDay(date);
        //前一个工作日日期
        String dayBeforeLastWorkDay = calendarDateService.selectLastWorkDay(lastWorkDay);

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

        List<ZTPoolEntity> ztPoolEntityList = ztPoolCache.getZTPoolInCacheByDate(date);

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

    @Override
    public ZTPoolDto selectLastZTByCode(String code, String date) {
        return ztPoolDao.selectLastZTByCode(code, date);
    }

    @Override
    public ZTPoolDto selectByCodeAndDate(String code, String lastWorkDay) {
        LambdaQueryWrapper<ZTPoolEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ZTPoolEntity::getCode, code)
                .eq(ZTPoolEntity::getTime, lastWorkDay);
        ZTPoolEntity ztPoolEntity = ztPoolDao.selectOne(lqw);
        return BeanUtil.copyProperties(ztPoolEntity, ZTPoolDto.class);
    }

    @Override
    public IPage<ZTPoolEntity> ztpage(Map<String, String> map) {
        Long page = Long.valueOf(map.get("page"));
        String time = map.get("time");
        boolean workDay = calendarDateService.selectWorkDayByDate(time);
        if (!workDay || (time.compareTo(DateUtil.getToday()) == 0 && DateUtil.getTime().compareTo("15:30:00") <= 0)) {
            time = calendarDateService.selectLastWorkDay(time);
        }
        String name = map.get("name");
        String lbNum = map.get("lbNum");
        LambdaQueryWrapper<ZTPoolEntity> lqw = new LambdaQueryWrapper<>();

        lqw.eq(ZTPoolEntity::getTime, time);
        lqw.like(StrUtil.isNotBlank(name), ZTPoolEntity::getName, name);
        lqw.eq(StrUtil.isNotBlank(lbNum),ZTPoolEntity::getLbNum,lbNum);

        Page<ZTPoolEntity> pageInfo = new Page<>(page, 10L);

        return ztPoolDao.selectPage(pageInfo, lqw);
    }

    @Override
    public IPage<ZTPoolEntity> ebpage(Map<String, String> map) {
        Long page = Long.valueOf(map.get("page"));
        String time = map.get("time");
        boolean workDay = calendarDateService.selectWorkDayByDate(time);
        if (!workDay || (time.compareTo(DateUtil.getToday()) == 0 && DateUtil.getTime().compareTo("15:30:00") <= 0)) {
            time = calendarDateService.selectLastWorkDay(time);
        }
        String name = map.get("name");
        LambdaQueryWrapper<ZTPoolEntity> lqw = new LambdaQueryWrapper<>();
        lqw.like(StrUtil.isNotBlank(name), ZTPoolEntity::getName, name);
        lqw.eq(ZTPoolEntity::getTime, time);
        lqw.eq(ZTPoolEntity::getLbNum, 2);

        Page<ZTPoolEntity> pageInfo = new Page<>(page, 10L);

        return ztPoolDao.selectPage(pageInfo, lqw);
    }

    @Override
    public ZTPoolEntity getById(String id) {
        return ztPoolDao.selectById(id);
    }
}





















































