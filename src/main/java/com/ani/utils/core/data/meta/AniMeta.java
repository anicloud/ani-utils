package com.ani.utils.core.data.meta;

import com.ani.utils.core.AniGeneralUtils;
import com.ani.utils.core.data.value.AniValue;
import com.ani.utils.exception.AniRuleException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class AniMeta implements Serializable {

    private static final long serialVersionUID = 8670038946985933832L;

    Integer groupId;

    Integer id;

    String name;

    List<AniMetaParam> params;

    public AniMeta() {
    }

    public AniMeta(Integer groupId, Integer id, String name, List<AniMetaParam> params) {
        this.groupId = groupId;
        this.id = id;
        this.name = name;
        this.params = params;
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

    public long getLongId() throws AniRuleException {
        return getLongId(this.groupId, this.id);
    }

    public static long getLongId(Integer groupId, Integer id) throws AniRuleException {
        if(groupId == null || id == null)
            throw new AniRuleException("ID_REQUIRED");
        return AniGeneralUtils.combineIntToLong(groupId, id);
    }

    public void checkParamsValueByMeta(List<AniValue> values) throws AniRuleException, AniValue.AniValueException {
        if (this.params == null) return;
        if (values == null || values.size() < this.params.size())
            throw new AniRuleException("PARAMS_LENGTH_NOT_MATCH");
        for (int i = 0; i < this.params.size(); i++) {
            AniMetaParam oneParam = params.get(i);
            values.get(i).checkValue(
                    oneParam.dataType,
                    oneParam.isRequired);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.groupId, this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AniMeta))
            return false;
        AniMeta objModel = (AniMeta) obj;
        return this.id == objModel.id;
    }
}
