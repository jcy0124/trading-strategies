package com.jcy.tradingstrategies.business.service.cache;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcy.tradingstrategies.business.dao.ZTPoolDao;
import com.jcy.tradingstrategies.business.domain.entity.ZTPoolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZTPoolCache {

    @Autowired
    private ZTPoolDao ztPoolDao;

    @Cacheable(cacheNames = "ztPool", key = "'ztPoolByDate:'+#date")
    public List<ZTPoolEntity> getZTPoolInCacheByDate(String date) {
        LambdaQueryWrapper<ZTPoolEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ZTPoolEntity::getTime,date);
        return ztPoolDao.selectList(lqw);
    }

    @CacheEvict(cacheNames = "ztPool", key = "'itemsByType:'+#date")
    public void evictZTPoolInCacheByDate(String date) {
    }
}
































