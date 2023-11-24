package com.jcy.tradingstrategies.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("a_stock")
public class AStockEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String jys;

    private String name;

    private String gl;
}
