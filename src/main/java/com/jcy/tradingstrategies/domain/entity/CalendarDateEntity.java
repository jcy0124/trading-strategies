package com.jcy.tradingstrategies.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("calendar_data")
public class CalendarDateEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String date;

    private String week;

    private String workDay;
}
