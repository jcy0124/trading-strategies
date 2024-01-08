package com.jcy.tradingstrategies.business.domain.vo.resp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RenQiResp {

    private String code;

    private String name;

    private String changeRatio;

    private String price;
}
