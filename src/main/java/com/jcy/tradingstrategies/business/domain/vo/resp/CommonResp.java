package com.jcy.tradingstrategies.business.domain.vo.resp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommonResp {

    private String code;

    private String name;

    private String reason;

    private String lastZTDate;

    private String nowZTDate;

    private String current;
}
