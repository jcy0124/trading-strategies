package com.jcy.tradingstrategies.business.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@TableName("price_warning")
public class PriceWarningEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private String time;

    private BigDecimal current;

    private BigDecimal comparePrice;

    private BigDecimal priceLimitWarning;

    private String reason;

    private String isAlert;

    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
