package com.jcy.tradingstrategies.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("user_info")
public class UserInfoEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String startAmount;

    private String nowAmount;

    private String changeAmount;

}
