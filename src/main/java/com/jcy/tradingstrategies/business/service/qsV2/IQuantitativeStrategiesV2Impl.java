package com.jcy.tradingstrategies.business.service.qsV2;

import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.business.service.IZTPoolService;
import com.jcy.tradingstrategies.business.service.qsV2.strategy.Strategy;
import com.jcy.tradingstrategies.business.service.qsV2.strategy.StrategyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class IQuantitativeStrategiesV2Impl implements IQuantitativeStrategiesV2Service {


    @Autowired
    private IZTPoolService iztPoolService;

    @Autowired
    private StrategyHolder strategyHolder;


    @Override
    public List<QuantitativeStrategiesV2Dto> quantitativeStrategiesV2(String startDate) {
        //查看数据库表（zt_pool）获取该天的所有涨停板信息
        List<ZTPoolDto> ztStartDate = iztPoolService.selectByDate(startDate);
        if (CollectionUtil.isEmpty(ztStartDate)){
            return null;
        }

        List<QuantitativeStrategiesV2Dto> result = new ArrayList<>();

        for (ZTPoolDto ztPoolDto : ztStartDate) {
            Strategy strategy = strategyHolder.of(ztPoolDto.getLbNum());
            if (Objects.isNull(strategy)){
                continue;
            }
            QuantitativeStrategiesV2Dto dto = strategy.doStrategy(ztPoolDto);
            if (Objects.nonNull(dto)){
                result.add(dto);
            }
        }
        return result;
    }
}










































