package com.jcy.tradingstrategies.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.domain.dto.FSCJLDto;
import com.jcy.tradingstrategies.domain.vo.resp.FSCJLResp;
import com.jcy.tradingstrategies.service.IBaseService;
import com.jcy.tradingstrategies.service.IFSCJLService;
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
 * 分时成交量
 */
@RestController
@RequestMapping("fscjl")
@Slf4j
@Api(tags = "分时成交量接口-FSCJLController")
public class FSCJLController {

    @Autowired
    private IBaseService baseService;

    @Autowired
    private IFSCJLService fscjlService;

    /**
     * 获取分时成交量
     *
     * @param code
     * @return
     */
    @GetMapping("/getFSCJL/{code}")
    @ApiOperation(value = "获取股票分时成交量-getFSCJL")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = true),
    })
    public Result getFSCJL(@PathVariable String code) {

        log.info("开始解析【{}】，分时成交量信息", code);

        String response = baseService.getFSCJLResp(code);

        List<FSCJLDto> fscjlResp = fscjlService.getFSCJLResp(response);
        List<FSCJLResp> fscjlResps = BeanUtil.copyToList(fscjlResp, FSCJLResp.class);

        log.info("结束解析【{}】，分时成交量信息", code);
        return Result.ok(fscjlResps);
    }

}

































