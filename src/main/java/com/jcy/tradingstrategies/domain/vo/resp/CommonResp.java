package com.jcy.tradingstrategies.domain.vo.resp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommonResp {

    private String code;

    private String name;

    private String lastZTDate;

    private String nowZTDate;
}
