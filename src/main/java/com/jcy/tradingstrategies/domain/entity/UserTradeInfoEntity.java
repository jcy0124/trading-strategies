package com.jcy.tradingstrategies.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("user_trade_info")
public class UserTradeInfoEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String code;

    private String name;

    private String date;

    private String codeStatus;

    private String buyPrice;

    private String todayClosePrice;

    private String sellPrice;

    private String stockNumber;

    private String todayProfit;

    private String totalProfit;

    private String lossPoint;

    private String lossRatio;

    private String lossPrice;

    private String profitPoint;

    private String profitRatio;

    private String profitPrice;
}
