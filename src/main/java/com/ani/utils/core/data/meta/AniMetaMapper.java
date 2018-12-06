package com.ani.utils.core.data.meta;

import com.ani.utils.exception.AniRuleException;

import java.io.Serializable;
import java.util.Objects;

public abstract class AniMetaMapper implements Serializable {

    private static final long serialVersionUID = 7595524234661219288L;

    private Integer groupId;
    private Integer metaId;

    public AniMetaMapper() {
    }

    public AniMetaMapper(Integer groupId, Integer metaId) {
        this.groupId = groupId;
        this.metaId = metaId;
    }

    public void initFromMeta(AniMeta meta) throws AniRuleException {
        checkMeta(meta);
        this.groupId = meta.groupId;
        this.metaId = meta.id;
    }

    protected void checkMetaMatching(AniMeta meta) throws AniRuleException {
        checkMeta(meta);
        if (this.groupId != meta.groupId || this.metaId != meta.id)
            throw new AniRuleException("META_NOT_MATCH");
    }

    private void checkMeta(AniMeta meta) throws AniRuleException {
        if (meta == null)
            throw new AniRuleException("META_IS_REQUIRED");
        if (meta.groupId == null || meta.id == null)
            throw new AniRuleException("METAID_AND_GROUPID_REQUIRED");
    }

    public long getLongId() throws AniRuleException {
        return AniMeta.getLongId(this.groupId, this.metaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.groupId, this.metaId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AniMetaMapper)) return false;
        AniMetaMapper oneObj = (AniMetaMapper) obj;
        if (oneObj.groupId == this.groupId && oneObj.metaId == this.metaId)
            return true;
        else
            return false;
    }
}