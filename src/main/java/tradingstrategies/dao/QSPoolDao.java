package tradingstrategies.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.entity.QSPoolEntity;
import com.jcy.tradingstrategies.entity.ZTPoolEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QSPoolDao extends BaseMapper<QSPoolEntity> {
}
