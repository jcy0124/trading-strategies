package tradingstrategies.controller;

import com.jcy.tradingstrategies.common.Result;
import com.jcy.tradingstrategies.service.IAStockService;
import com.jcy.tradingstrategies.service.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("astock")
@Slf4j
public class AStockController {

    @Autowired
    private IAStockService aStockService;

    @Autowired
    private IBaseService baseService;

    @GetMapping("getAllAStock")
    public Result getAllAStock() {

        log.info("开始获取全部a股市场股票");
        String response = baseService.getAllAStock();
        aStockService.insert(response);

        log.info("结束获取全部a股市场股票");
        return Result.ok();
    }

}

























