package com.jcy.tradingstrategies.domain.vo.resp;

import io.swagger.annotations.ApiModelProperty;
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
