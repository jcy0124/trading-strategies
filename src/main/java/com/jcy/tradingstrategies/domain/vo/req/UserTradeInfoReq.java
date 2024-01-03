package com.jcy.tradingstrategies.domain.vo.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserTradeInfoReq {

    private String userName;

    private String code;

    private String name;

    private String date;

    private String codeStatus;

    private String buyPrice;

    private String sellPrice;

    private String stockNumber;

    private String todayProfit;

    private String totalProfit;
}
