package com.jcy.tradingstrategies.business.domain.vo.req;

import com.jcy.tradingstrategies.common.base.PageQuery;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AStockReq extends PageQuery {

    private String code;

    private String name;
}
