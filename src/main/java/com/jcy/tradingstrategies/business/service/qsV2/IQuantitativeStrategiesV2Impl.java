package com.jcy.tradingstrategies.business.service.qsV2;

import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.business.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.business.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.business.service.ICalendarDateService;
import com.jcy.tradingstrategies.business.service.IZTPoolService;
import com.jcy.tradingstrategies.business.service.qsV2.strategy.Strategy;
import com.jcy.tradingstrategies.business.service.qsV2.strategy.StrategyHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IQuantitativeStrategiesV2Impl implements IQuantitativeStrategiesV2Service {


    @Autowired
    private IZTPoolService iztPoolService;

    @Autowired
    private StrategyHolder strategyHolder;

    @Autowired
    private ICalendarDateService calendarDateService;

    @Autowired
    private IBaseKLineInfoService baseKLineInfoService;

    @Override
    public List<QuantitativeStrategiesV2Dto> quantitativeStrategiesV2(String startDate) {
        //查看数据库表（zt_pool）获取该天的所有涨停板信息
        List<ZTPoolDto> ztStartList = iztPoolService.selectByDate(startDate);
        if (CollectionUtil.isEmpty(ztStartList)) {
            return null;
        }

        String secondDay = calendarDateService.selectNextWorkDay(startDate);
        String thirdDay = calendarDateService.selectNextWorkDay(secondDay);


        List<QuantitativeStrategiesV2Dto> result = new ArrayList<>();

        Map<String, BaseKLineInfoDto> secondKLineMap
                = baseKLineInfoService.getBaseKLineMap(ztStartList.stream().map(ZTPoolDto::getCode).collect(Collectors.toList()), secondDay);

        Map<String, BaseKLineInfoDto> thirdKLineMap
                = baseKLineInfoService.getBaseKLineMap(ztStartList.stream().map(ZTPoolDto::getCode).collect(Collectors.toList()), thirdDay);

        for (ZTPoolDto ztPoolDto : ztStartList) {
            BaseKLineInfoDto secondKLine = secondKLineMap.get(ztPoolDto.getCode());
            BaseKLineInfoDto thirdKLine = thirdKLineMap.get(ztPoolDto.getCode());

            Strategy strategy = strategyHolder.of(ztPoolDto.getLbNum());
            if (Objects.isNull(strategy)) {
                continue;
            }
            QuantitativeStrategiesV2Dto dto = strategy.doStrategy(ztPoolDto,secondKLine,thirdKLine);
            if (Objects.nonNull(dto)) {
                result.add(dto);
            }
        }
        return result;
    }
}










































