package com.ani.utils.core.data.value;

import com.ani.utils.core.AniGeneralUtils;
import com.ani.utils.core.data.type.AniDataType;
import com.ani.utils.core.data.type.AniDataTypeCategories;

import java.util.List;

import static com.ani.utils.core.data.type.AniDataTypeCategories.COLLECTION;

public class AniCollectionValue<P> extends AniValue<P> {

    private static AniDataTypeCategories category = COLLECTION;

    List<P> value;

    @Override
    protected AniDataTypeCategories getCategory() {
        return category;
    }

    @Override
    public void checkValue(AniDataType dataType, Boolean isRequired) throws AniValueException {
        if(isRequired
                && (AniGeneralUtils.isCollectionEmpty(this.value))
                )
            throw new AniValueException(Errors.DATA_EMPTY);
        super.checkValue(dataType);
    }
}
