package tradingstrategies.service;

import com.jcy.tradingstrategies.dto.BaseKLineInfoDto;

import java.util.List;

public interface IBaseKLineInfoService {
    List<BaseKLineInfoDto> getBaseKLineInfo(String response);
}
