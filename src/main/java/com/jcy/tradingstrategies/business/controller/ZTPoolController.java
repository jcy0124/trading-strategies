package com.jcy.tradingstrategies.business.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.jcy.tradingstrategies.business.domain.dto.ELBDto;
import com.jcy.tradingstrategies.business.domain.dto.LBDto;
import com.jcy.tradingstrategies.business.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.business.domain.vo.resp.ELBResp;
import com.jcy.tradingstrategies.business.domain.vo.resp.LBResp;
import com.jcy.tradingstrategies.business.domain.vo.resp.ZTPoolResp;
import com.jcy.tradingstrategies.business.service.IBaseService;
import com.jcy.tradingstrategies.business.service.ICalendarDateService;
import com.jcy.tradingstrategies.business.service.IZTPoolService;
import com.jcy.tradingstrategies.common.annotation.DateValid;
import com.jcy.tradingstrategies.common.base.Result;
import com.jcy.tradingstrategies.common.base.ResultCode;
import com.jcy.tradingstrategies.common.base.ResultEnum;
import com.jcy.tradingstrategies.common.constant.TimeConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 涨停股
 */
@RestController
@RequestMapping("ztpool")
@Slf4j
@Api(tags = "涨停股票接口-ZTPoolController")
public class ZTPoolController {

    @Autowired
    private IZTPoolService ztPoolService;

    @Autowired
    private IBaseService baseService;

    @Autowired
    private ICalendarDateService calendarDateService;

    /**
     * Http获取当天涨停板股票
     *
     * @param date
     * @return
     */
    @GetMapping("/getZtPoolByHttp/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    @ApiOperation(value = "http同步涨停股-getZtPoolByHttp")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "date", value = "日期：yyyy-MM-dd", dataType = "String", required = true),
    })
    public Result getZtPoolByHttp(@PathVariable String date) {

        String isExists = ztPoolService.isExistByDate(date);
        if (StrUtil.isNotBlank(isExists)) {
            return Result.ok(ResultEnum.ALREADY_DOWNLOAD);
        }

        log.info("开始解析【{}】涨停股票", date);

        String response = baseService.getZTPoolResp(date);

        Integer count = ztPoolService.insert(response, date);

        log.info("结束解析【{}】涨停股票", date);
        return Result.ok(ResultCode.SUCCESS, "当日一共有【" + count + "】只涨停股");
    }


    /**
     * 获取某天的涨停股
     *
     * @param date
     * @return
     */
    @GetMapping("/getZtPool/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    @ApiOperation(value = "查看涨停股-getZtPool")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "date", value = "日期：yyyy-MM-dd", dataType = "String", required = true),
    })
    public Result getZtPool(@PathVariable String date) {

        log.info("开始查看【{}】涨停股票", date);

        List<ZTPoolDto> ztPoolDtoList = ztPoolService.selectByDate(date);

        if (CollectionUtil.isEmpty(ztPoolDtoList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<ZTPoolResp> ztPoolRespList = BeanUtil.copyToList(ztPoolDtoList, ZTPoolResp.class);

        log.info("开始查看【{}】涨停股票", date);
        return Result.ok(ztPoolRespList);
    }


    /**
     * 获取某天和前一天连扳的股票，二连板股票
     *
     * @param date
     * @return
     */
    @GetMapping("/getErBan/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    @ApiOperation(value = "查看二连板股票-getErBan")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "date", value = "日期：yyyy-MM-dd", dataType = "String", required = true),
    })
    public Result getErBan(@PathVariable String date) {
        log.info("开始获取二连板信息");

        List<ELBDto> elbDtoList = ztPoolService.gerErBan(date);

        if (CollectionUtil.isEmpty(elbDtoList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<ELBResp> elbRespList = BeanUtil.copyToList(elbDtoList, ELBResp.class);

        log.info("结束获取二连板信息");
        return Result.ok(ResultCode.SUCCESS, "【" + date + "】二连板信息", elbRespList);
    }


    /**
     * 查看某日的前10个交易日的连扳股
     *
     * @param date
     * @return
     */
    @GetMapping("/getLBan/{date}")
    @DateValid(afterTime = TimeConstant.HALF_PAST_THREE)
    @ApiOperation(value = "查看十个交易日连扳股票-getLBan")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "date", value = "日期：yyyy-MM-dd", dataType = "String", required = true),
    })
    public Result getLBan(@PathVariable String date) {
        log.info("开始获取连板信息");

        List<LBDto> lBanList = ztPoolService.getLBan(date);

        if (CollectionUtil.isEmpty(lBanList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<LBResp> lbRespList = BeanUtil.copyToList(lBanList, LBResp.class);

        log.info("结束获取连板信息");
        return Result.ok(ResultCode.SUCCESS, "【" + date + "】连板信息", lbRespList);
    }

    @GetMapping("/getZtPoolListByHttp")
    public Result getZtPoolListByHttp() {
        String startDate = "2023-11-29";
        String endDate = "2023-06-01";
        List<String> dateList = calendarDateService.selectWorkDateBetween(startDate, endDate);

        for (String date : dateList) {
            log.info("开始【{}】解析", date);
            String response = baseService.getZTPoolResp(date);
            try {
                ztPoolService.insert(response, date);
            } catch (Exception e) {
                log.error("调用远程解析出问题：", e);
            }
        }

        return Result.ok();
    }
}
