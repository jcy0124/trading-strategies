package tradingstrategies.service;

import com.jcy.tradingstrategies.dto.ELBDto;
import com.jcy.tradingstrategies.dto.LBDto;
import com.jcy.tradingstrategies.dto.ZTPoolDto;

import java.util.List;

public interface IZTPoolService{

    Integer insert(String response, String date);

    List<ZTPoolDto> selectByDate(String date);

    List<ELBDto> gerErBan(String date);

    String isExistByDate(String date);

    List<LBDto> getLBan(String date);
}
