package com.ani.utils.core.data.meta;

import com.ani.utils.core.data.type.AniDataType;

import java.io.Serializable;
import java.util.Objects;

public class AniMetaParam implements Serializable {

    private static final long serialVersionUID = 5078547280261126326L;

    String name;
    AniDataType dataType;
    Boolean isRequired;

    public AniMetaParam() {
    }

    public AniMetaParam(String name, AniDataType dataType, Boolean isRequired) {
        this.name = name;
        this.dataType = dataType;
        this.isRequired = isRequired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AniDataType getDataType() {
        return dataType;
    }

    public void setDataType(AniDataType dataType) {
        this.dataType = dataType;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof AniMetaParam)) return false;
        AniMetaParam paramObj = (AniMetaParam)obj;
        return (
                paramObj.name.equals(this.name)
                && paramObj.dataType.equals(this.dataType)
        );
    }

    public int hashCode(){
        return Objects.hash(this.name);
    }
}
