package tradingstrategies.service;

import com.jcy.tradingstrategies.vo.req.BaseKLineReq;

public interface IBaseService {


    String getBaseKLineResp(BaseKLineReq req);

    String getZTPoolResp(String date);

    String getAllAStock();
}









