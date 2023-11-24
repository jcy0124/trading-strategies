package tradingstrategies.constant;


public class UrlConstant {

    /**
     * 获取涨停板
     */
    public static final String ZT_POOL_URL = "https://stockapi.com.cn/v1/base/ZTPool?token=" + TokenConstant.TOKEN + "&date=";
    public static final String ZT_POOL_URL_WITHOUT_TOKEN = "https://stockapi.com.cn/v1/base/ZTPool?date=";
    /**
     * 获取强势股
     */
    public static final String QS_POOL_URL = "https://stockapi.com.cn/v1/base/QSPool?token=" + TokenConstant.TOKEN + "&date=";
    public static final String QS_POOL_URL_WITHOUT_TOKEN = "https://stockapi.com.cn/v1/base/QSPool?date=";
    /**
     * 查看是否是交易日
     */
    public static final String TRADE_DATE_URL = "https://stockapi.com.cn/v1/base/tradeDate?token=" + TokenConstant.TOKEN + "&tradeDate=";
    public static final String TRADE_DATE_URL_WITHOUT_TOKEN = "https://stockapi.com.cn/v1/base/tradeDate?tradeDate=";

    /**
     * 查看股票日线，周线，月线
     */
    public static final String BASE_K_LINE_URL = "https://stockapi.com.cn/v1/base/day?token=" + TokenConstant.TOKEN + "&code=%s&endDate=%s&startDate=%s&calculationCycle=%s";
    public static final String BASE_K_LINE_URL_WITHOUT_TOKEN = "https://stockapi.com.cn/v1/base/day?code=%s&endDate=%s&startDate=%s&calculationCycle=%s";

    /**
     * 获取全部a股票列表
     */
    public static final String ALL_CODE_URL = "https://stockapi.com.cn/v1/base/all?token=" + TokenConstant.TOKEN;
    public static final String ALL_CODE_URL_WITHOUT_TOKEN = "https://stockapi.com.cn/v1/base/all";


}


































