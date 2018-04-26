package com.ani.utils.core.data.type;

/**
 * Created by yeh on 15-10-16.
 */
public enum PrivacyType {

    PUBLIC(1),
    PROTECTED(2),
    PRIVATE(4);

    private final Integer typeId;

    PrivacyType(int typeId) {
        this.typeId = typeId;
    }

    public int getValue() {
        return this.typeId;
    }
}
