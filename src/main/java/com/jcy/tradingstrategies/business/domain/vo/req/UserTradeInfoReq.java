package com.jcy.tradingstrategies.business.domain.vo.req;

import com.jcy.tradingstrategies.common.constant.BaseConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class UserTradeInfoReq {

    @ApiModelProperty(name = "用户名称",required = true)
    @NotBlank(message = "用户名称不能为空")
    private String userName;

    @ApiModelProperty(name = "股票代码",required = true)
    @NotBlank(message = "股票代码不能为空")
    @Pattern(regexp = BaseConstant.CODE_REGEX, message = "请正确输入六位股票代码")
    private String code;

    @ApiModelProperty(name = "股票名称",required = true)
    @NotBlank(message = "股票名称不能为空")
    private String name;

    @ApiModelProperty(name = "日期：yyyy-MM-dd",required = true)
    @Pattern(regexp = BaseConstant.DAY_REGEX, message = "请正确输入日期格式：yyyy-MM-dd")
    @NotBlank(message = "日期不能为空")
    private String date;

    @ApiModelProperty(name = "买入状态（1买入，2持有，3卖出",required = true)
    @NotBlank(message = "买入状态不能为空")
    @Pattern(regexp = BaseConstant.CODE_STATUS_REGEX, message = "请正确输入六位股票代码")
    private String codeStatus;

    private String buyPrice;

    private String sellPrice;

    private String stockNumber;

    private String lossPoint;
}
