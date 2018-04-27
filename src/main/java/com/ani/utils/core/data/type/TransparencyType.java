package com.ani.utils.core.data.type;

/**
 * Created by yeh on 15-10-16.
 */
public enum TransparencyType {

    CURRENT(1),
    NEIGHBOURS(2),
    CHILDREN(4);

    private final Integer typeId;

    TransparencyType(int typeId) {
        this.typeId = typeId;
    }

    public int getValue() {
        return this.typeId;
    }
}
