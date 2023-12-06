package com.jcy.tradingstrategies.domain.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FSCJLDto {

    private String code;

    private String time;

    private String price;

    private String shoushu;

    private String bsbz; //涨跌标志，1-跌，2-涨，4-竞价阶段
}
