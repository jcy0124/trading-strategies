package com.jcy.tradingstrategies.domain.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RenQiDto {

    private String code;

    private String name;

    private String changeRatio;

    private String price;
}
