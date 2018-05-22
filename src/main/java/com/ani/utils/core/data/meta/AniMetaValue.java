package com.ani.utils.core.data.meta;

import com.ani.utils.core.data.value.AniValue;
import com.ani.utils.exception.AniRuleException;

import java.io.Serializable;
import java.util.List;

public abstract class AniMetaValue implements Serializable {

    private static final long serialVersionUID = 8670038946985933832L;

    protected AniMeta meta;
    protected List<AniValue> paramsValue;

    public AniMetaValue() {
    }

    public AniMetaValue(AniMeta meta, List<AniValue> paramsValue) {
        this.meta = meta;
        this.paramsValue = paramsValue;
    }

    public void check() throws AniRuleException, AniValue.AniValueException {
        if (this.meta == null)
            throw new AniRuleException("META_IS_REQUIRED");
        this.meta.checkParamsValueByMeta(this.paramsValue);
    }

    public AniMeta getMeta() {
        return meta;
    }

    public void setMeta(AniMeta meta) {
        this.meta = meta;
        this.paramsValue = null;
    }

    public List<AniValue> getParamsValue() {
        return paramsValue;
    }

    public void setParamsValue(List<AniValue> paramsValue) throws AniRuleException, AniValue.AniValueException {
        this.meta.checkParamsValueByMeta(paramsValue);
        this.paramsValue = paramsValue;
    }

    public void setParamValue(int idx, AniValue value) throws AniValue.AniValueException, AniRuleException {
        this.meta.checkParamValue(idx, value);
        this.paramsValue.add(idx, value);
    }

    public AniValue getParamValue(int idx) throws AniRuleException {
        if(idx < 0 || idx >= (this.paramsValue.size() - 1))
            throw new AniRuleException("PARAM_VALUE_OUT_OF_RANGE");
        AniValue value = this.paramsValue.get(idx);
        return value;
    }

    @Override
    public int hashCode() {
        return this.meta.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AniMetaValue))
            return false;
        AniMetaValue objModel = (AniMetaValue) obj;
        return this.meta.equals(objModel.meta)
                && this.paramsValue.equals(objModel.paramsValue);
    }
}
