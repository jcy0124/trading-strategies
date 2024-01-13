package com.jcy.tradingstrategies.business.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class SSGPDto {

    private BigDecimal buy5;

    private BigDecimal sell5;
}
