package com.ani.utils.core;

import java.util.HashMap;
import java.util.Map;

/**
 * User: yeh
 * Date: 2/7/13
 */
public class AniMapBuilder<K, V> {
    private Map<K, V> resultMap;

    public AniMapBuilder() {
        this.resultMap = new HashMap<K, V>();
    }

    public AniMapBuilder(int initCapacity) {
        this.resultMap = new HashMap<K, V>(initCapacity);
    }

    public AniMapBuilder(int initCapacity, float loadFactor) {
        this.resultMap = new HashMap<K, V>(initCapacity, loadFactor);
    }

    public AniMapBuilder<K, V> put(K key, V value) {
        if (value != null
                && !value.equals(0L) && !value.equals(0)) {
            this.resultMap.put(key, value);
        }
        return this;
    }

    public AniMapBuilder<K, V> putAnyway(K key, V value) {
        this.resultMap.put(key, value);
        return this;
    }

    public Map<K, V> getMap() {
        return resultMap;
    }
}
