package com.jcy.tradingstrategies.business.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@TableName("low_warning")
public class LowWarningEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String time;

    private BigDecimal lowLimitWarning;

    private String isAlert;
}
