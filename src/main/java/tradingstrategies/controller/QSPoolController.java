package tradingstrategies.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.common.ResultCode;
import com.jcy.tradingstrategies.constant.UrlConstant;
import com.jcy.tradingstrategies.dto.QSPoolDto;
import com.jcy.tradingstrategies.service.IQSPoolService;
import com.jcy.tradingstrategies.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 强势股
 */
@RestController
@RequestMapping("qspool")
@Slf4j
public class QSPoolController {

    @Autowired
    private IQSPoolService qsPoolService;

    @GetMapping("/getQsPoolByHttp/{date}")
    public Result getQsPoolByHttp(@PathVariable String date) {
        log.info("开始解析【{}】强势股票",date);

        String url = UrlConstant.QS_POOL_URL + date;
        String response  = HttpClientUtil.doGet(url, "UTF-8");

        qsPoolService.insert(response,date);

        log.info("结束解析【{}】强势股票",date);
        return Result.ok();
    }

    /**
     * 获取某天的强势股
     * @param date
     * @return
     */
    @GetMapping("/getQsPool/{date}")
    public Result getQsPool(@PathVariable String date){

        log.info("开始查看【{}】强势股票",date);

        List<QSPoolDto> qsPoolDtoList = qsPoolService.selectByDate(date);

        if (CollectionUtil.isEmpty(qsPoolDtoList)){
            return Result.ok(ResultCode.SUCCESS,"没有查看到该天的强势数据");
        }

        log.info("结束查看【{}】强势股票",date);
        return Result.ok(qsPoolDtoList);
    }

}






















