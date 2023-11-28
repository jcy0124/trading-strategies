package com.jcy.tradingstrategies.domain.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RenQiEntity {

    private String code;

    private String name;

    private String changeRatio;

    private String price;
}
