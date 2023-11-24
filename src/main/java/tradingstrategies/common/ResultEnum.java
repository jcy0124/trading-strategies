package tradingstrategies.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum {
    ALREADY_DOWNLOAD(ResultCode.SUCCESS,"已经同步过该天数据，请勿重复同步哦~~"),
    NO_TODAY_DATA(ResultCode.SUCCESS,"没有获取到当天的数据，可能是市场较弱哦~~");
    ;


    private final Integer code;
    private final String msg;
}
