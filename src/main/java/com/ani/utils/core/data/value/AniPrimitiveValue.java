package com.ani.utils.core.data.value;

import com.ani.utils.core.data.type.AniDataType;
import com.ani.utils.core.data.type.AniDataTypeCategories;

import static com.ani.utils.core.data.type.AniDataTypeCategories.PRIMITIVE;

public class AniPrimitiveValue<P> extends AniValue<P> {

    private static AniDataTypeCategories category = PRIMITIVE;

    private P value;

    @Override
    protected AniDataTypeCategories getCategory() {
        return category;
    }

    @Override
    public void checkValue(AniDataType dataType, Boolean isRequired) throws AniValueException {
        if(isRequired && this.value == null)
            throw new AniValueException(Errors.DATA_EMPTY);
        super.checkValue(dataType);
    }
}