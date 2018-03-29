package com.ani.utils.core;

import java.util.HashMap;
import java.util.Map;

/**
 * User: yeh
 * Date: 2/7/13
 */
public class AniMapBuilder<K, V> {

    private static int defaultCapacity = 10;

    private Map<K, V> resultMap;

    private int capacity;

    public AniMapBuilder() {
        this.resultMap = new HashMap<K, V>(defaultCapacity, 1f);
        this.capacity = defaultCapacity;
    }

    public AniMapBuilder(int initCapacity) {
        this.resultMap = new HashMap<K, V>(initCapacity, 1f);
        this.capacity = initCapacity;
    }

    public AniMapBuilder<K, V> extend(int capacity) {
        int newCapacity = this.capacity + capacity;
        Map<K, V> newMap = new HashMap<>(newCapacity, 1f);
        newMap.putAll(this.resultMap);
        this.resultMap = newMap;
        this.capacity = newCapacity;
        return this;
    }

    public AniMapBuilder<K, V> reset(int capacity) {
        init(capacity);
        return this;
    }

    private void init(int capacity) {
        int length = 0;
        if (capacity < 1)
            length = 1;
        this.resultMap = new HashMap<>(capacity, 1f);
        this.capacity = length;
    }

    private void autoExtend() {
        if (this.resultMap.size() > this.capacity)
            this.capacity = this.capacity * 2;
    }

    public AniMapBuilder<K, V> put(K key, V value) {
        if (value != null
                && !value.equals(0L) && !value.equals(0)) {
            this.resultMap.put(key, value);
            autoExtend();
        }
        return this;
    }

    public AniMapBuilder<K, V> putAnyway(K key, V value) {
        this.resultMap.put(key, value);
        autoExtend();
        return this;
    }

    public Map<K, V> getMap() {
        return resultMap;
    }

}
