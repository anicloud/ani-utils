package com.ani.utils.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: yeh
 * Date: 2/7/13
 */
public class AniListBuilder<T> {
    private List<T> resultList;

    public AniListBuilder() {
        this.resultList = new ArrayList<T>();
    }

    public AniListBuilder(int initCapacity) {
        this.resultList = new ArrayList<T>(initCapacity);
    }

    public AniListBuilder<T> add(T value) {
        if (value != null) {
            this.resultList.add(value);
        }
        return this;
    }

    public List<T> getList(){
        return resultList;
    }
}
