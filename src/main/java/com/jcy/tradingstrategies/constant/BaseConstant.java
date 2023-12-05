package com.jcy.tradingstrategies.constant;

import java.math.BigDecimal;

public class BaseConstant {

    public static final String DAY_REGEX = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    public static final String CODE_REGEX= "^\\d{6}$";

    public static final String CALCULATION_CYCLE_REGEX ="[1][0][0-2]";

    public static final String ZTB = "涨停板";

    public static final String QSG = "强势股";

    public static final String QBGP = "全部股票";

    public static final String KXXX ="k线信息";

    public static final String RQGP ="人气股票";

    public static final String YOUZI = "柚子信息";

}