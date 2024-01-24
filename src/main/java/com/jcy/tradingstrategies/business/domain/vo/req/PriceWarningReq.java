package com.jcy.tradingstrategies.business.domain.vo.req;

import com.jcy.tradingstrategies.common.constant.BaseConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@ToString
public class PriceWarningReq {


    private String id;

    @ApiModelProperty(name = "股票代码",required = true)
    @NotBlank(message = "股票代码不能为空")
    @Pattern(regexp = BaseConstant.CODE_REGEX, message = "请正确输入六位股票代码")
    private String code;

    private String name;

    @ApiModelProperty(name = "预警价位",required = true)
    @NotNull(message = "预警价位不能为空")
    private BigDecimal priceLimitWarning;

    @ApiModelProperty(name = "原因",required = false)
    private String reason;

    private BigDecimal current;
}
















