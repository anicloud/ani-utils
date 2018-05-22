package com.ani.utils.core.data.meta;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class AniMetaGroup implements Serializable {

    private static final long serialVersionUID = 8670038946985933832L;

    Integer groupId;
    String name;

    public AniMetaGroup() {
    }

    public AniMetaGroup(Integer groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.groupId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AniMetaGroup))
            return false;
        AniMetaGroup metaGroup = (AniMetaGroup) obj;
        return this.groupId == metaGroup.groupId;
    }
}
