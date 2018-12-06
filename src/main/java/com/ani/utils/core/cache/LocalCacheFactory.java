package com.ani.utils.core.cache;

import bitronix.tm.TransactionManagerServices;
import com.ani.utils.exception.AniDataException;
import com.ani.utils.exception.AniRuleException;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URISyntaxException;

public class LocalCacheFactory extends CacheFactory {

    protected static volatile CacheManager cacheManager;

    public LocalCacheFactory() throws AniDataException {
        super();
        this.initCacheMgr();
    }

    public LocalCacheFactory(String cacheConfigPath) throws AniDataException, AniRuleException {
        super(cacheConfigPath);
        this.initCacheMgr();
    }

    protected void initTxMgr() {
        if (txMgr != null)
            return;
        synchronized (this) {
            txMgr = TransactionManagerServices.getTransactionManager();
        }
    }

    private void initCacheMgr() throws AniDataException {
        if (cacheManager != null) {
            return;
        }
         synchronized (this) {
            cacheManager = this.obtainCacheManager();
        }
    }

    public <K, V> Cache<K, V> getCache(String alias, Class<K> keyClass, Class<V> vClass) {
        return this.cacheManager.getCache(alias, keyClass, vClass);
    }

    protected CacheManager obtainCacheManager() throws AniDataException {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager manager = null;
        try {
            manager = cachingProvider.getCacheManager(
                    getClass()
                            .getResource(cacheConfigPath)
                            .toURI(),
                    getClass().getClassLoader());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AniDataException(e.getMessage());
        }
        return manager;
    }

}
