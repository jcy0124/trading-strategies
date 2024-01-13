package com.jcy.tradingstrategies.business.service.qsV2.strategy;

import com.jcy.tradingstrategies.business.dao.QuantitativeStrategiesV2Dao;
import com.jcy.tradingstrategies.business.domain.dto.BaseKLineInfoDto;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.business.domain.vo.req.BaseKLineReq;
import com.jcy.tradingstrategies.business.service.IBaseKLineInfoService;
import com.jcy.tradingstrategies.business.service.ICalendarDateService;
import com.jcy.tradingstrategies.business.service.IZTPoolService;
import com.jcy.tradingstrategies.business.service.adaptor.BaseKLineInfoAdaptor;
import com.jcy.tradingstrategies.business.service.qsV2.QuantitativeStrategiesV2Adaptor;
import com.jcy.tradingstrategies.business.service.qsV2.QuantitativeStrategiesV2Dto;
import com.jcy.tradingstrategies.business.service.qsV2.QuantitativeStrategiesV2Enum;
import com.jcy.tradingstrategies.common.util.BigDecimalUtils;
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
    public QuantitativeStrategiesV2Dto doStrategy(ZTPoolDto firstKLine, BaseKLineInfoDto secondKLine, BaseKLineInfoDto thirdKLine) {

        //获取第一天涨停板价格
        BigDecimal ztFirstLastPrice = firstKLine.getLastPrice();

        String code = firstKLine.getCode();
        String name = firstKLine.getName();

        //获取第二个工作日涨停板
        ZTPoolDto ztSecondDay = iztPoolService.selectByCodeAndDate(code, secondKLine.getTime());

        //第二个工作日不是涨停
        if (Objects.isNull(ztSecondDay)) {

            //判断第二个工作日收盘价是否超过第一个工作日涨停价
            Boolean isOver2_1 = compareLastPrice(secondKLine.getClose(), ztFirstLastPrice);

            //第二天收盘价没有超过第一天涨停板价格
            if (!isOver2_1) {
                return null;
            }

            //判断第二个工作日是否高开
            Boolean isHigh2_1 = compare2OpenHighLast(secondKLine.getOpen(), ztFirstLastPrice);

            //如果是高开
            if (isHigh2_1) {
                //第二天收盘价超过第一天涨停板价

                //获取第三个工作日涨停板
                ZTPoolDto ztThirdDay = iztPoolService.selectByCodeAndDate(code, thirdKLine.getCode());

                //第三个工作日是涨停，第一个工作日涨停，第二个工作日高位横盘，第三个工作日涨停，强势，不继续跟进，不进行入库操作
                if (Objects.nonNull(ztThirdDay)) {
                    return QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(code, name, QuantitativeStrategiesV2Enum.REASON_1);
                }

                //第三个工作日不是涨停

                //判断第三个工作日收盘价是否超过第一个工作日涨停价
                Boolean isOver3_1 = compareLastPrice(thirdKLine.getClose(), ztFirstLastPrice);
                if (!isOver3_1) {
                    return null;
                }

                QuantitativeStrategiesV2Dto quantitativeStrategiesV2Dto = QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(code, name, QuantitativeStrategiesV2Enum.REASON_2);

                //对该数据进行入库操作进行持续跟进
//            quantitativeStrategiesV2Dao.insert();

                return quantitativeStrategiesV2Dto;
            } else { //如果是低开

                //获取第三个工作日涨停板
                ZTPoolDto ztThirdDay = iztPoolService.selectByCodeAndDate(code, thirdKLine.getTime());

                //第三个工作日是涨停，第一个工作日涨停，第二个工作日高位横盘，第三个工作日涨停，强势，不继续跟进，不进行入库操作
                if (Objects.nonNull(ztThirdDay)) {
                    return QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(code, name, QuantitativeStrategiesV2Enum.REASON_1);
                }

                //第三个工作日不是涨停

                //判断第三日的实k线的最低价是否高于第二日的开盘价
                BigDecimal thirdLow = thirdKLine.getChange().compareTo(new BigDecimal("0")) >= 0 ? thirdKLine.getOpen() : thirdKLine.getClose();

                //判断第三个工作日收盘价是否超过第一个工作日涨停价
                Boolean isOver3_2 = compareLastPrice(thirdLow, secondKLine.getOpen());
                if (!isOver3_2) {
                    return null;
                }

                QuantitativeStrategiesV2Dto quantitativeStrategiesV2Dto = QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(code, name, QuantitativeStrategiesV2Enum.REASON_3);

                return quantitativeStrategiesV2Dto;
            }

            //==========================================================================================================
        } else { //如果第二个工作日是涨停，判断第三个工作日的收盘价是否在第一个工作日涨停上方


            BigDecimal secondDayOpen = secondKLine.getOpen();
            BigDecimal multiply = BigDecimalUtils.multiply(ztFirstLastPrice, new BigDecimal(1.03));
            if (BigDecimalUtils.compare(secondDayOpen, multiply) >= 0) {
                return null;
            }

            if (BigDecimalUtils.compare(thirdKLine.getHigh(), ztSecondDay.getLastPrice()) < 0) {
                return null;
            }

            //判断第三个工作日的收盘价在第一个工作日的上方和第二个工作日中间
            Boolean isOK = compare3CloseIn1HighAnd2Mid(ztFirstLastPrice, ztSecondDay.getLastPrice(), thirdKLine);
            if (!isOK) {
                return null;
            }
            QuantitativeStrategiesV2Dto quantitativeStrategiesV2Dto = QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(code, name, QuantitativeStrategiesV2Enum.REASON_4);

            return quantitativeStrategiesV2Dto;
        }
    }

    //原循环调用 http接口
/*    @Override
    public QuantitativeStrategiesV2Dto doStrategy(ZTPoolDto ztPoolDtoFirstDay,BaseKLineInfoDto secondKLine,BaseKLineInfoDto thirdKLine) {

        //获取第一天涨停板价格
        BigDecimal ztFirstLastPrice = ztPoolDtoFirstDay.getLastPrice();

        //获取第二个工作日日期
        String secondDay = calendarDateService.selectNextWorkDay(ztPoolDtoFirstDay.getTime());

        //获取第二个工作日涨停板
        ZTPoolDto ztSecondDay = iztPoolService.selectByCodeAndDate(ztPoolDtoFirstDay.getCode(), secondDay);

        //第二个工作日不是涨停
        if (Objects.isNull(ztSecondDay)) {

            //获取第二个工作日基本信息
            BaseKLineInfoDto kLineSecondDay = getKLine(ztPoolDtoFirstDay.getCode(), secondDay);

            //判断第二个工作日收盘价是否超过第一个工作日涨停价
            Boolean isOver2_1 = compareLastPrice(kLineSecondDay.getClose(), ztFirstLastPrice);

            //第二天收盘价没有超过第一天涨停板价格
            if (!isOver2_1) {
                return null;
            }

            //判断第二个工作日是否高开
            Boolean isHigh2_1 = compare2OpenHighLast(kLineSecondDay.getOpen(), ztFirstLastPrice);

            //如果是高开
            if (isHigh2_1) {
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
                Boolean isOver3_1 = compareLastPrice(kLineThirdDay.getClose(), ztFirstLastPrice);
                if (!isOver3_1) {
                    return null;
                }

                QuantitativeStrategiesV2Dto quantitativeStrategiesV2Dto = QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(ztPoolDtoFirstDay.getCode(), ztPoolDtoFirstDay.getName(), QuantitativeStrategiesV2Enum.REASON_2);

                //对该数据进行入库操作进行持续跟进
//            quantitativeStrategiesV2Dao.insert();

                return quantitativeStrategiesV2Dto;
            } else { //如果是低开
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

                //判断第三日的实k线的最低价是否高于第二日的开盘价
                BigDecimal thirdLow = kLineThirdDay.getChange().compareTo(new BigDecimal("0")) >= 0 ? kLineThirdDay.getOpen() : kLineThirdDay.getClose();

                //判断第三个工作日收盘价是否超过第一个工作日涨停价
                Boolean isOver3_2 = compareLastPrice(thirdLow, kLineSecondDay.getOpen());
                if (!isOver3_2) {
                    return null;
                }

                QuantitativeStrategiesV2Dto quantitativeStrategiesV2Dto = QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(ztPoolDtoFirstDay.getCode(), ztPoolDtoFirstDay.getName(), QuantitativeStrategiesV2Enum.REASON_3);

                return quantitativeStrategiesV2Dto;
            }

            //==========================================================================================================
        } else { //如果第二个工作日是涨停，判断第三个工作日的收盘价是否在第一个工作日涨停上方

            //获取第二个工作日的k线
            BaseKLineInfoDto kLineSecondDay = getKLine(ztPoolDtoFirstDay.getCode(), secondDay);

            BigDecimal secondDayOpen = kLineSecondDay.getOpen();
            BigDecimal multiply = BigDecimalUtils.multiply(ztFirstLastPrice, new BigDecimal(1.03));
            if (BigDecimalUtils.compare(secondDayOpen, multiply) >= 0) {
                return null;
            }

            //获取第三个工作日日期
            String thirdDay = calendarDateService.selectNextWorkDay(secondDay);
            BaseKLineInfoDto kLineThirdDay = getKLine(ztPoolDtoFirstDay.getCode(), thirdDay);

            if (BigDecimalUtils.compare(kLineThirdDay.getHigh(), ztSecondDay.getLastPrice()) < 0){
                return null;
            }

                //判断第三个工作日的收盘价在第一个工作日的上方和第二个工作日中间
                Boolean isOK = compare3CloseIn1HighAnd2Mid(ztFirstLastPrice, ztSecondDay.getLastPrice(), kLineThirdDay);
            if (!isOK) {
                return null;
            }
            QuantitativeStrategiesV2Dto quantitativeStrategiesV2Dto = QuantitativeStrategiesV2Adaptor.buildQuantitativeStrategiesV2Dto(ztPoolDtoFirstDay.getCode(), ztPoolDtoFirstDay.getName(), QuantitativeStrategiesV2Enum.REASON_4);

            return quantitativeStrategiesV2Dto;
        }
    }*/

    private Boolean compare3CloseIn1HighAnd2Mid(BigDecimal ztFirstLastPrice, BigDecimal ztSecondLastPrice, BaseKLineInfoDto kLineThirdDay) {
        ztSecondLastPrice = BigDecimalUtils.subtract(ztSecondLastPrice, BigDecimalUtils.multiply(BigDecimalUtils.subtract(ztSecondLastPrice, ztFirstLastPrice), new BigDecimal(0.5)));
        BigDecimal thirdDayClose = kLineThirdDay.getClose();
        if (BigDecimalUtils.compare(thirdDayClose, ztFirstLastPrice) >= 0 && BigDecimalUtils.compare(ztSecondLastPrice, thirdDayClose) >= 0) {
            return true;
        }
        return false;
    }


    private Boolean compare2OpenHighLast(BigDecimal open, BigDecimal lastPrice) {
        return BigDecimalUtils.compare(open, lastPrice) > 0;
    }

    private Boolean compareLastPrice(BigDecimal nextClosePrice, BigDecimal firstClosePrice) {
        return BigDecimalUtils.compare(nextClosePrice, firstClosePrice) >= 0;
    }

    private BaseKLineInfoDto getKLine(String code, String date) {
        BaseKLineReq reqNext = BaseKLineInfoAdaptor.buildBaseKLineReq(code, date);
        return baseKLineInfoService.getBaseKLineInfoByDay(reqNext);
    }
}
















































