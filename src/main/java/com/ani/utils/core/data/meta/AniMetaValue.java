package com.ani.utils.core.data.meta;

import com.ani.utils.core.data.value.AniValue;
import com.ani.utils.exception.AniRuleException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class AniMetaValue implements Serializable {

    private static final long serialVersionUID = 8670038946985933832L;

    protected Long metaLongId;
    protected List<AniValue> paramsValue;

    public AniMetaValue() {
    }

    public AniMetaValue(Long metaLongId, List<AniValue> paramsValue) {
        this.metaLongId = metaLongId;
        this.paramsValue = paramsValue;
    }

    public Long getMetaLongId() {
        return metaLongId;
    }

    public void setMetaLongId(Long metaLongId) {
        this.metaLongId = metaLongId;
    }

    public List<AniValue> getParamsValue() {
        return paramsValue;
    }

    public void setParamsValue(List<AniValue> paramsValue) {
        this.paramsValue = paramsValue;
    }

    public AniValue getParamValue(int idx) throws AniRuleException {
        if(idx < 0 || idx >= (this.paramsValue.size() - 1))
            throw new AniRuleException("PARAM_VALUE_OUT_OF_RANGE");
        AniValue value = this.paramsValue.get(idx);
        return value;
    }

    @Override
    public int hashCode() {
        if(metaLongId == null)
            return 0;
        return Objects.hashCode(metaLongId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AniMetaValue))
            return false;
        AniMetaValue metaValue = (AniMetaValue) obj;
        if(metaValue.getMetaLongId() != this.metaLongId)
            return false;
        if(paramsValueEquality(metaValue.getParamsValue()))
            return false;
        return true;
    }

    private boolean paramsValueEquality(List<AniValue> paramsValue) {
        if(paramsValue == null
            && this.paramsValue == null) {
                return true;
        }
        if(paramsValue != null && this.paramsValue != null) {
            if(paramsValue.size() != this.paramsValue.size())
                return false;
            for(int idx = 0; idx < paramsValue.size(); idx++) {
                if(this.paramsValue.get(idx) == null
                    || paramsValue.get(idx) == null)
                    return false;
                if(!this.paramsValue.get(idx)
                        .equals(paramsValue.get(idx)))
                    return false;
            }
            return true;
        }
        return false;
    }
}
