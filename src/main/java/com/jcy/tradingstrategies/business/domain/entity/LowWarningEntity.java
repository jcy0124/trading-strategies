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
@TableName("low_warning")
public class LowWarningEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private String time;

    private BigDecimal lowLimitWarning;

    private String reason;

    private String isAlert;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
