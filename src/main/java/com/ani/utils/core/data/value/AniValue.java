package com.ani.utils.core.data.value;

import com.ani.utils.core.data.type.AniDataType;
import com.ani.utils.core.data.type.AniDataTypeCategories;
import com.ani.utils.exception.AniBaseException;

import java.lang.reflect.ParameterizedType;

public abstract class AniValue<P> {

    protected final Class<P> valueType;

    public AniValue() {
        this.valueType = (Class<P>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected abstract AniDataTypeCategories getCategory();

    public abstract void checkValue(AniDataType dataType, Boolean isRequired) throws AniValueException;

    public void checkValue(AniDataType dataType)
            throws AniValueException {
        if (!dataType.getCategory().equals(getCategory()))
            throw new AniValueException(Errors.WRONG_DATA_CATEGORY, this);
        if (dataType.getDataType().getValue() != this.valueType)
            throw new AniValueException(Errors.WRONG_PRIMITIVE_DATA_TYPE, this);
    }

    public enum Errors {
        WRONG_DATA_CATEGORY,
        WRONG_PRIMITIVE_DATA_TYPE,
        DATA_EMPTY
    }

    public static class AniValueException extends AniBaseException {

        public Errors error;
        public AniValue value;

        public AniValueException(Errors dataEmpty) {
            super();
        }

        public AniValueException(Errors error, AniValue value) {
            super(error.name(), "AniValueException");
            this.value = value;
            this.error = error;
        }
    }
}
