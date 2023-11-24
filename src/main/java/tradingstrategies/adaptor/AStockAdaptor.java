package tradingstrategies.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.jcy.tradingstrategies.entity.AStockEntity;
import com.jcy.tradingstrategies.entity.BaseKLineInfoEntity;

import java.math.BigDecimal;

public class AStockAdaptor {


    public static AStockEntity buildAStockEntity(JSONObject jsonDetailInfo) {
        String code = jsonDetailInfo.getString("api_code");
        String jys = jsonDetailInfo.getString("jys");
        String gl = jsonDetailInfo.getString("gl");
        String name = jsonDetailInfo.getString("name");

        if (code.startsWith("688")){
            return null;
        }

        AStockEntity aStockEntity = new AStockEntity();
        aStockEntity.setCode(code);
        aStockEntity.setJys(jys);
        aStockEntity.setGl(gl);
        aStockEntity.setName(name);
        return aStockEntity;
    }
}
