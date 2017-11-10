package com.ani.utils.core.datatype;

import com.ani.utils.exception.AniRuleException;

/**
 * Created by hey on 17-2-15.
 */
public enum AniDataPrimitiveTypes {
    INTEGER(Integer.class),
    PERCENTAGE(Short.class),
    FLOAT(Float.class),
    STRING(String.class),
    BOOLEAN(Boolean.class),
    BINARY(Byte[].class),
    CHAR(char.class),
    SHORT(Short.class),
    LONG(Long.class),
    OBJECT(Object.class),
    BYTE(Byte.class);
    private final Class dataClass;

    AniDataPrimitiveTypes(Class dataClass) {
        this.dataClass = dataClass;
    }

    public Class getValue(){
        return this.dataClass;
    }

    public static <T extends Object> void checkValueLegality(AniDataPrimitiveTypes type, T oneValue) throws AniRuleException {
        if(oneValue.getClass() != type.getValue()){
            throw new AniRuleException("DATA_VALUE_NOT_MATCHES_TYPE_" + type.name());
        }
    }
}
