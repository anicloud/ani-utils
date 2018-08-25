package com.ani.utils.core.cache;

import com.ani.utils.exception.AniDataException;
import com.ani.utils.exception.AniRuleException;

import javax.cache.Cache;
import javax.transaction.TransactionManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AniCache<K, V> {

    private static volatile LocalCacheFactory cacheFactory;

    private Class<K> kClass;
    private Class<V> vClass;
    private Cache<K, V> cache;

    protected AniCache() throws AniDataException {
        init("AniCache");
    }

    protected AniCache(String alias) throws AniDataException {
        init(alias);
    }

    private void setCacheFactory() throws AniDataException {
        cacheFactory = new LocalCacheFactory();
    }

    protected TransactionManager getTx() throws AniDataException {
        return this.getCacheFactory().getTxMgr();
    }

    private void setKVClasses() {
        Type[] genTypes = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        this.kClass = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.vClass = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    private LocalCacheFactory getCacheFactory() throws AniDataException {
        if (this.cacheFactory == null)
            this.setCacheFactory();
        return cacheFactory;
    }

    protected Cache<K, V> getCache() throws AniRuleException {
        if (this.cache == null)
            throw new AniRuleException("CACHE_NOT_INITIALIZED");
        return this.cache;
    }

    private void setCache(String alias) throws AniDataException {
        this.cache = this.getCacheFactory().getCache(alias, this.kClass, this.vClass);
    }

    private void initCacheFactory() throws AniDataException {
        if (cacheFactory == null) {
            this.setCacheFactory();
        }
    }

    protected void init(String alias) throws AniDataException {
        initCacheFactory();
        this.setKVClasses();
        this.setCacheFactory();
        this.setCache(alias);
    }

}
