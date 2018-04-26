package com.ani.utils.core.data.type;

/**
 * Created by yeh on 15-10-16.
 */
public enum PrivilegeType {

    READ(1),
    WRITE(2),
    EXECUTE(4);

    private final Integer typeId;

    PrivilegeType(int typeId) {
        this.typeId = typeId;
    }

    public int getValue() {
        return this.typeId;
    }
}
