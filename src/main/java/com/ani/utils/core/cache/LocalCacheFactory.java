package com.ani.utils.core.cache;

import bitronix.tm.TransactionManagerServices;
import com.ani.utils.exception.AniDataException;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.transaction.TransactionManager;
import java.net.URISyntaxException;

public class LocalCacheFactory {

    private static volatile TransactionManager txMgr;
    private static volatile CacheManager cacheManager;

    public LocalCacheFactory() throws AniDataException {
        this.initCacheMgr();
    }

    public TransactionManager getTxMgr() {
        initTxMgr();
        return txMgr;
    }

    private void initTxMgr() {
        if (txMgr != null)
            return;
        synchronized (this) {
            txMgr = TransactionManagerServices.getTransactionManager();
        }
    }

    private void initCacheMgr() throws AniDataException {
        if(cacheManager != null)
            return;
        synchronized (this) {
            cacheManager = this.obtainCacheManager();
        }
    }

    private CacheManager obtainCacheManager() throws AniDataException {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager manager = null;
        try {
            manager = cachingProvider.getCacheManager(
                    getClass()
                            .getResource("/cache/ehcache.xml")
                            .toURI(),
                    getClass().getClassLoader());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AniDataException(e.getMessage());
        }
        return manager;
    }

    public <K, V> Cache<K, V> getCache(String alias, Class<K> keyClass, Class<V> vClass) {
        return this.cacheManager.getCache(alias, keyClass, vClass);
    }

}
