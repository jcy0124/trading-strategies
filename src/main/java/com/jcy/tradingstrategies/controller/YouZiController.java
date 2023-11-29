package com.jcy.tradingstrategies.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jcy.tradingstrategies.annotation.DateValid;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.constant.TimeConstant;
import com.jcy.tradingstrategies.domain.dto.RenQiDto;
import com.jcy.tradingstrategies.domain.dto.YouZiDto;
import com.jcy.tradingstrategies.domain.vo.resp.RenQiResp;
import com.jcy.tradingstrategies.domain.vo.resp.YouZiResp;
import com.jcy.tradingstrategies.service.IBaseService;
import com.jcy.tradingstrategies.service.IRenQiService;
import com.jcy.tradingstrategies.service.IYouZiService;
import com.jcy.tradingstrategies.util.DateUtil;
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

@RestController
@RequestMapping("youzi")
@Slf4j
@Api(tags = "柚子信息接口-YouZiController")
public class YouZiController {

    @Autowired
    private IYouZiService youZiService;

    @Autowired
    private IBaseService baseService;

    @GetMapping("/getYouZiByHttp/{date}")
    @DateValid(afterTime = TimeConstant.FIVE_FORTY)
    @ApiOperation(value = "http同步柚子信息-getYouZiByHttp")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "date", value = "日期：yyyy-MM-dd", dataType = "String", required = true),
    })
    public Result getYouZiByHttp(@PathVariable String date) {

        log.info("开始解析【{}】柚子信息", date);

        String response = baseService.getYouZiResp(date);

        List<YouZiDto> youZiDtoList = youZiService.convert(response);

        List<YouZiResp> youZiResps = BeanUtil.copyToList(youZiDtoList, YouZiResp.class);

        log.info("结束解析【{}】柚子信息", date);
        return Result.ok(youZiResps);
    }
}
















