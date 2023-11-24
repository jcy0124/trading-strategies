package tradingstrategies.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcy.tradingstrategies.entity.BaseKLineInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseKLineInfoDao extends BaseMapper<BaseKLineInfoEntity> {
}
