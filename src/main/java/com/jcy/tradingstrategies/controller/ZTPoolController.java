package com.jcy.tradingstrategies.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.common.ResultCode;
import com.jcy.tradingstrategies.common.ResultEnum;
import com.jcy.tradingstrategies.domain.dto.ELBDto;
import com.jcy.tradingstrategies.domain.dto.LBDto;
import com.jcy.tradingstrategies.domain.dto.ZTPoolDto;
import com.jcy.tradingstrategies.domain.vo.resp.ZTPoolResp;
import com.jcy.tradingstrategies.service.IBaseService;
import com.jcy.tradingstrategies.service.IZTPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 涨停股
 */
@RestController
@RequestMapping("ztpool")
@Slf4j
public class ZTPoolController {

    @Autowired
    private IZTPoolService ztPoolService;

    @Autowired
    private IBaseService baseService;


    /**
     * 获取当天涨停板股票
     *
     * @param date
     * @return
     */
    @GetMapping("/getZtPoolByHttp/{date}")
    @DateValid
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
    @DateValid
    public Result getZtPool(@PathVariable String date) {

        log.info("开始查看【{}】涨停股票", date);

        List<ZTPoolDto> ztPoolDtoList = ztPoolService.selectByDate(date);

        if (CollectionUtil.isEmpty(ztPoolDtoList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        List<ZTPoolResp> ztPoolRespList = new ArrayList<>();
        for (ZTPoolDto ztPoolDto : ztPoolDtoList) {
            ZTPoolResp ztPoolResp = BeanUtil.copyProperties(ztPoolDto, ZTPoolResp.class);
            ztPoolRespList.add(ztPoolResp);
        }

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
    @DateValid
    public Result getErBan(@PathVariable String date) {
        log.info("开始获取二连板信息");

        List<ELBDto> elbDtoList = ztPoolService.gerErBan(date);

        if (CollectionUtil.isEmpty(elbDtoList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        log.info("结束获取二连板信息");
        return Result.ok(ResultCode.SUCCESS, "【" + date + "】二连板信息", elbDtoList);
    }


    /**
     * 查看某日的前10个交易日的连扳股
     *
     * @param date
     * @return
     */
    @GetMapping("/getLBan/{date}")
    @DateValid
    public Result getLBan(@PathVariable String date) {
        log.info("开始获取连板信息");

        List<LBDto> lBanList = ztPoolService.getLBan(date);

        if (CollectionUtil.isEmpty(lBanList)) {
            return Result.ok(ResultEnum.NO_TODAY_DATA);
        }

        log.info("结束获取连板信息");
        return Result.ok(ResultCode.SUCCESS, "【" + date + "】连板信息", lBanList);
    }

}

























































































