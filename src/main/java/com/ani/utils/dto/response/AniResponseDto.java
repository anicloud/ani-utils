package com.ani.utils.dto.response;

import java.io.Serializable;

/**
 * Created by zhanglina on 18-3-15.
 */
public abstract class AniResponseDto implements Serializable {

    private static final long serialVersionUID = -8202218827778429104L;

    Boolean success;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AniResponseDto() {
    }

    public AniResponseDto(Boolean success) {
        this.success = success;
    }
}
