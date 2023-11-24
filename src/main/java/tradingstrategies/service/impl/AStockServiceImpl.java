package tradingstrategies.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.adaptor.AStockAdaptor;
import com.jcy.tradingstrategies.config.ThreadPoolConfig;
import com.jcy.tradingstrategies.dao.AStockDao;
import com.jcy.tradingstrategies.entity.AStockEntity;
import com.jcy.tradingstrategies.service.IAStockService;
import com.jcy.tradingstrategies.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AStockServiceImpl implements IAStockService {

    public static final int MAX_INSERT_SIZE = 1000;

    @Autowired
    private AStockDao aStockDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
//    @Async(ThreadPoolConfig.TRADING_STRATEGIES)
    public void insert(String response) {

        aStockDao.deleteAll();

        JSONArray data = JsonUtil.getData(response, "全部股票");
        List<AStockEntity> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonDetailInfo = data.getJSONObject(i);
            AStockEntity aStockEntity = AStockAdaptor.buildAStockEntity(jsonDetailInfo);
            if (Objects.isNull(aStockEntity)) {
                continue;
            }

            list.add(aStockEntity);
            if (list.size() >= MAX_INSERT_SIZE) {
                aStockDao.insertBatch(list);
                list.clear();
            }
        }
        aStockDao.insertBatch(list);
    }
}





















