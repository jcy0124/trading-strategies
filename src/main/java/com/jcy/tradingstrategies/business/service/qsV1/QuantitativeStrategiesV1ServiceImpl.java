package com.jcy.tradingstrategies.business.service.qsV1;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.jcy.tradingstrategies.business.service.adaptor.CommonDtoAdaptor;
import com.jcy.tradingstrategies.common.constant.TimeConstant;
import com.jcy.tradingstrategies.business.domain.dto.CommonDto;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.business.service.ICalendarDateService;
import com.jcy.tradingstrategies.business.service.IZTPoolService;
import com.jcy.tradingstrategies.common.util.BigDecimalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuantitativeStrategiesV1ServiceImpl implements IQuantitativeStrategiesV1Service {

    @Autowired
    private IZTPoolService ztPoolService;

    @Autowired
    private ICalendarDateService calendarDateService;


    /**
     * 以当天涨停板为基础，计算前3-7个工作日内，是否有涨停板，并且当天涨停板高于之前的涨停板
     *
     * @param date
     * @return
     */
    @Override
    public List<CommonDto> quantitativeStrategiesV1(String date) {
        List<ZTPoolDto> ztPoolDtoList = ztPoolService.selectByDate(date);
        if (CollectionUtil.isEmpty(ztPoolDtoList)) {
            return Collections.EMPTY_LIST;
        }

        ztPoolDtoList = ztPoolDtoList.stream().filter(item -> item.getLbNum() == 1).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(ztPoolDtoList)) {
            return Collections.EMPTY_LIST;
        }

        List<CommonDto> result = new ArrayList<>();

        //遍历每一个股票代码，
        for (ZTPoolDto newZTPoolDto : ztPoolDtoList) {
            //获取最近一个涨停板日期
            ZTPoolDto lastZTPoolDto = ztPoolService.selectLastZTByCode(newZTPoolDto.getCode(), date);
            if (Objects.isNull(lastZTPoolDto)) {
                continue;
            }

            //如果时间超过一个月，也不处理
            String newDate = newZTPoolDto.getTime();
            String newDateSubMonth = DateUtil.format(DateUtil.offsetMonth(DateUtil.parse(newDate, TimeConstant.formatDate), -1), TimeConstant.formatDate);
            String lastDate = lastZTPoolDto.getTime();
            if (newDateSubMonth.compareTo(lastDate) > 0) {
                continue;
            }

            //如果是四板以上，直接返回，不处理
            if (lastZTPoolDto.getLbNum().compareTo(3) > 0) {
                continue;
            }

            //处理三板情况，获取第二个板的数据
            if (Objects.equals(lastZTPoolDto.getLbNum(), 3)) {
                //查询上一个涨停板
                String lastWorkDay = calendarDateService.selectLastWorkDay(lastZTPoolDto.getTime());
                lastZTPoolDto = ztPoolService.selectByCodeAndDate(lastZTPoolDto.getCode(), lastWorkDay);
            }

            //获取上一个涨停板最高价
            BigDecimal lastLastPrice = lastZTPoolDto.getLastPrice();
            //获取当前涨停板最高价
            BigDecimal newLastPrice = newZTPoolDto.getLastPrice();
            //如果当前涨停价没有超过上一个涨停或者二板的涨停
            if (BigDecimalUtils.compare(lastLastPrice, newLastPrice) > 0) {
                continue;
            }

            CommonDto commonDto = CommonDtoAdaptor.buildCommonDto(newZTPoolDto.getCode(), newZTPoolDto.getName(), newZTPoolDto.getTime(), lastZTPoolDto.getTime());
            result.add(commonDto);

        }

        return result;
    }
}




































