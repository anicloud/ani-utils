package com.ani.utils.core;

import java.util.ArrayList;
import java.util.List;

/**
 * User: yeh
 * Date: 2/7/13
 */
public class AniListBuilder<T> {

    private List<T> resultList;

    public AniListBuilder() {
        this.resultList = null;
    }

    public AniListBuilder(int initCapacity) {
        this.resultList = new ArrayList<T>(initCapacity);
    }

    private void initIfNeed(int initCapacityIfEmpty) {
        if(resultList != null) return;
        this.resultList = new ArrayList<>(initCapacityIfEmpty);
    }

    private void initIfNeed(List<T>... lists){
        if(lists == null) return;
        int capacity = 0;
        for(List<T> oneList: lists) {
            if (oneList == null) continue;
            capacity += oneList.size();
        }
        if(capacity == 0) return;
        initIfNeed(capacity);
    }

    public AniListBuilder<T> add(T value) {
        if (value != null) {
            this.resultList.add(value);
        }
        return this;
    }

    public AniListBuilder<T> add(List<T> values) {
        if (values != null) {
            this.resultList.addAll(values);
        }
        return this;
    }

    public AniListBuilder<T> addAll(List<T>... lists){
        if(lists == null || lists.length < 1) return this;
        initIfNeed(lists);
        for(List<T> oneList: lists) {
            this.resultList.addAll(oneList);
        }
        return this;
    }

    public List<T> getList(){
        return resultList;
    }
}
