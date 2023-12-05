package com.jcy.tradingstrategies.domain.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommonDto {

    private String code;

    private String name;

    private String lastZTDate;

    private String nowZTDate;

}
