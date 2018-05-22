package com.ani.utils.core.data.meta;

import com.ani.utils.core.AniGeneralUtils;
import com.ani.utils.core.data.value.AniValue;
import com.ani.utils.exception.AniRuleException;

import java.io.Serializable;
import java.util.List;

public abstract class AniMeta implements Serializable {

    private static final long serialVersionUID = 8670038946985933832L;
    Integer groupId;
    Integer id;
    String name;
    List<AniMetaParam> params;
    private Long longId = null;

    public AniMeta() {
    }

    public AniMeta(Integer groupId, Integer id, String name, List<AniMetaParam> params) {
        this.groupId = groupId;
        this.id = id;
        this.name = name;
        this.params = params;
    }

    public static long getLongId(Integer groupId, Integer id) throws AniRuleException {
        if (groupId == null || id == null)
            throw new AniRuleException("ID_REQUIRED");
        return AniGeneralUtils.combineIntToLong(groupId, id);
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AniMetaParam> getParams() {
        return params;
    }

    public void setParams(List<AniMetaParam> params) {
        this.params = params;
    }

    public Long getLongId() throws AniRuleException {
        if (this.longId == null)
            this.longId = AniMeta.getLongId(this.groupId, this.id);
        return this.longId;
    }

    public void checkParamValue(int idx, AniValue value) throws AniValue.AniValueException, AniRuleException {
        AniMetaParam oneParam = params.get(idx);
        if (value == null && oneParam.isRequired)
            throw new AniRuleException(String.format(
                    "REQUIRED_PARAM_NOT_EXIST_IDX:[%d]", idx));
        if (oneParam == null)
            throw new AniRuleException("PARAM_NOT_INITIALIZED");
        value.checkValue(
                oneParam.dataType,
                oneParam.isRequired);
    }

    public void checkMetaParamsValue(AniMetaValue metaValue) throws AniRuleException, AniValue.AniValueException {
        if (metaValue.meta == null || metaValue.meta.getLongId() != this.getLongId()) {
            throw new AniRuleException("META_VALUE_ID_NOT_MATCH");
        }
        if (metaValue == null || metaValue.paramsValue == null) {
            throw new AniRuleException("META_VALUE_REQUIRED");
        }
        if (metaValue.paramsValue.size() > this.params.size()) {
            throw new AniRuleException("META_VALUE_PARAMS_LENGTH_OVERFLOW");
        }
        this.checkParamsValueByMeta(metaValue.paramsValue);
    }

    public void checkParamsValueByMeta(List<AniValue> values) throws AniRuleException, AniValue.AniValueException {
        if (AniGeneralUtils.isCollectionEmpty(values)
                && AniGeneralUtils.isCollectionEmpty(this.params))
            return;
        if (values.size() < this.params.size())
            throw new AniRuleException("PARAMS_LENGTH_NOT_MATCH");
        for (int i = 0; i < this.params.size(); i++) {
            checkParamValue(i, values.get(i));
        }
    }

    @Override
    public int hashCode() {
        try {
            return this.getLongId().hashCode();
        } catch (AniRuleException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AniMeta))
            return false;
        AniMeta objModel = (AniMeta) obj;
        return this.id == objModel.id;
    }
}
