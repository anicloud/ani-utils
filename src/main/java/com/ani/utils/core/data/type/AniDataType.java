package com.ani.utils.core.data.type;

import java.io.Serializable;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AniDataType that = (AniDataType) o;
        if (category != that.category) return false;
        return dataType == that.dataType;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.ordinal() : 0;
        result = (result << 16) + (dataType != null ? dataType.ordinal() : 0);
        return result;
    }
}
