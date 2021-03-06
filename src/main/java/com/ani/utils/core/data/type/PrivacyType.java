package com.ani.utils.core.data.type;

import java.util.HashSet;
import java.util.Set;

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

    public int getId() {
        return this.typeId;
    }


}
