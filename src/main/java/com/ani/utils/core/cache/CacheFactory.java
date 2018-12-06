package com.ani.utils.core.cache;

import com.ani.utils.core.AniGeneralUtils;
import com.ani.utils.exception.AniDataException;
import com.ani.utils.exception.AniRuleException;
import org.apache.commons.lang3.StringUtils;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.transaction.TransactionManager;

public abstract class CacheFactory {

    protected static volatile TransactionManager txMgr;

    protected volatile String cacheConfigPath = "/cache/ehcache.xml";

    public CacheFactory() throws AniDataException {
    }

    public CacheFactory(String cacheConfigPath) throws AniDataException, AniRuleException {
        if(StringUtils.isEmpty(cacheConfigPath)) {
//            throw new AniRuleException("CACHE_CONFIG_PATH_REQUIRED");
            return;
        }
        this.cacheConfigPath = cacheConfigPath;
    }

    public TransactionManager getTxMgr() {
        initTxMgr();
        return txMgr;
    }

    protected abstract void initTxMgr();

}