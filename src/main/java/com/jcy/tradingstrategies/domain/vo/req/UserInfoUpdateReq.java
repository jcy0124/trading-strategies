package com.jcy.tradingstrategies.domain.vo.req;

import com.jcy.tradingstrategies.constant.BaseConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class UserInfoUpdateReq {

    @ApiModelProperty(name = "用户姓名",required = true)
    @NotBlank(message = "用户姓名不能为空")
    private String userName;

    @ApiModelProperty(name = "变更资金",required = true)
    @Pattern(regexp = BaseConstant.POSITIVE_NUMBER_REGEX, message = "请正确输入数字")
    @NotBlank(message = "变更资金不能为空")
    private String changeAmount;

}
