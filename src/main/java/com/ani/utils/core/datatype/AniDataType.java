package com.ani.utils.core.datatype;

import java.io.Serializable;

/**
 * Created by hey on 17-2-15.
 */
public class AniDataType implements Serializable {

    private static final long serialVersionUID = 2930021334774335010L;

    AniDataTypeCategories category;

    AniDataPrimitiveTypes dataType;

    public AniDataType() {
    }

    public AniDataType(AniDataTypeCategories category, AniDataPrimitiveTypes dataType) {
        this.category = category;
        this.dataType = dataType;
    }

    public AniDataTypeCategories getCategory() {
        return category;
    }

    public void setCategory(AniDataTypeCategories category) {
        this.category = category;
    }

    public AniDataPrimitiveTypes getDataType() {
        return dataType;
    }

    public void setDataType(AniDataPrimitiveTypes dataType) {
        this.dataType = dataType;
    }
}
