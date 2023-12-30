package com.jcy.tradingstrategies.service.qsV2.strategy;

import com.jcy.tradingstrategies.adaptor.BaseKLineInfoAdaptor;
import com.jcy.tradingstrategies.dao.QuantitativeStrategiesV2Dao;
import com.jcy.tradingstrategies.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.domain.vo.req.BaseKLineReq;
import com.jcy.tradingstrategies.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.service.ICalendarDateService;
import com.jcy.tradingstrategies.service.IZTPoolService;
import com.jcy.tradingstrategies.service.qsV2.QuantitativeStrategiesV2Adaptor;
import com.jcy.tradingstrategies.service.qsV2.QuantitativeStrategiesV2Dto;
import com.jcy.tradingstrategies.service.qsV2.QuantitativeStrategiesV2Enum;
import com.jcy.tradingstrategies.util.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class FirstZtStrategy implements Strategy {

    @Autowired
    private IZTPoolService iztPoolService;

    @Autowired
    private ICalendarDateService calendarDateService;

    @Autowired
    private IBaseKLineInfoService baseKLineInfoService;

    @Autowired
    private QuantitativeStrategiesV2Dao quantitativeStrategiesV2Dao;

    @Override
    public QuantitativeStrategiesV2Dto doStrategy(ZTPoolDto ztPoolDtoFirstDay) {

        //==============================================================================================================
        //获取第二个工作日日期
        String secondDay = calendarDateService.selectNextWorkDay(ztPoolDtoFirstDay.getTime());

        //获取第二个工作日涨停板
        ZTPoolDto ztSecondDay = iztPoolService.selectByCodeAndDate(ztPoolDtoFirstDay.getCode(), secondDay);

        //第二个工作日不是涨停
        if (Objects.isNull(ztSecondDay)) {

            //获取第二个工作日基本信息
            BaseKLineInfoDto kLineSecondDay = getKLine(ztPoolDtoFirstDay.getCode(), secondDay);

            //判断第二个工作日收盘价是否超过第一个工作日涨停价
            Boolean isOver2_1 = compareLastPrice(kLineSecondDay.getClose(), ztPoolDtoFirstDay.getLastPrice());

            //第二天收盘价没有超过第一天涨停板价格
            if (!isOver2_1) {
                return null;
            }

            //==========================================================================================================

            //==========================================================================================================
            //第二天收盘价超过第一天涨停板价
            //获取第三个工作日日期
            String thirdDay = calendarDateService.selectNextWorkDay(secondDay);

            //获取第三个工作日涨停板
            ZTPoolDto ztThirdDay = iztPoolService.selectByCodeAndDate(ztPoolDtoFirstDay.getCode(), thirdDay);

            //第三个工作日是涨停，第一个工作日涨停，第二个工作日高位横盘，第三个工作日涨停，强势，不继续跟进，不进行入库操作
            if (Objects.nonNull(ztThirdDay)) {
                return QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(ztPoolDtoFirstDay.getCode(), ztPoolDtoFirstDay.getName(), QuantitativeStrategiesV2Enum.REASON_1);
            }

            //第三个工作日不是涨停
            //获取第三个工作日基本信息
            BaseKLineInfoDto kLineThirdDay = getKLine(ztPoolDtoFirstDay.getCode(), thirdDay);

            //判断第三个工作日收盘价是否超过第一个工作日涨停价
            Boolean isOver3_1 = compareLastPrice(kLineThirdDay.getClose(), ztPoolDtoFirstDay.getLastPrice());
            if (!isOver3_1) {
                return null;
            }

            QuantitativeStrategiesV2Dto quantitativeStrategiesV2Dto = QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(ztPoolDtoFirstDay.getCode(), ztPoolDtoFirstDay.getName(), QuantitativeStrategiesV2Enum.REASON_2);

            //对该数据进行入库操作进行持续跟进
//            quantitativeStrategiesV2Dao.insert();

            return quantitativeStrategiesV2Dto;
            //==========================================================================================================
        } else { //如果第二个工作日是涨停
            return null;
        }
    }

    private Boolean compareLastPrice(BigDecimal nextClosePrice, BigDecimal firstClosePrice) {
        return BigDecimalUtils.compare(nextClosePrice, firstClosePrice) >= 0;
    }

    private BaseKLineInfoDto getKLine(String code, String date) {
        BaseKLineReq reqNext = BaseKLineInfoAdaptor.buildBaseKLineReq(code, date);
        return baseKLineInfoService.getBaseKLineInfoByDay(reqNext);
    }
}
















































