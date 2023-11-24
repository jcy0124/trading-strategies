package com.jcy.tradingstrategies.vo.req;

import com.jcy.tradingstrategies.constant.BaseConstant;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class BaseKLineReq {

    @NotBlank(message = "股票代码不能为空")
    private String code;

    @Pattern(regexp = BaseConstant.DAY_REGEX)
    @NotBlank(message = "起始日期不能为空")
    private String startDate;

    @Pattern(regexp = BaseConstant.DAY_REGEX)
    @NotBlank(message = "结束日期不能为空")
    private String endDate;

    @Pattern(regexp = "[1][0][0-2]")
    @NotBlank(message = "周期不能为空")
    private String calculationCycle; //100-日 101-周 102-月
}
