package com.jcy.tradingstrategies.business.domain.vo.req;

import com.jcy.tradingstrategies.common.constant.BaseConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class BaseKLineReq {

    @ApiModelProperty(name = "股票代码",required = true)
    @NotBlank(message = "股票代码不能为空")
    @Pattern(regexp = BaseConstant.CODE_REGEX, message = "请正确输入六位股票代码")
    private String code;

    @ApiModelProperty(name = "起始日期：yyyy-MM-dd",required = true)
    @Pattern(regexp = BaseConstant.DAY_REGEX, message = "请正确输入日期格式：yyyy-MM-dd")
    @NotBlank(message = "起始日期不能为空")
    private String startDate;

    @ApiModelProperty(name = "结束日期：yyyy-MM-dd",required = true)
    @Pattern(regexp = BaseConstant.DAY_REGEX, message = "请正确输入日期格式：yyyy-MM-dd")
    @NotBlank(message = "结束日期不能为空")
    private String endDate;

    @ApiModelProperty(name = "周期：100-日 101-周 102-月",required = true)
    @Pattern(regexp = BaseConstant.CALCULATION_CYCLE_REGEX,message = "请正确输入周期 100-日 101周 102月")
    @NotBlank(message = "周期不能为空")
    private String calculationCycle; //100-日 101-周 102-月


}
