package com.ani.utils.dto.response;

/**
 * Created by zhanglina on 18-3-15.
 */
public abstract class AniResponseDto {

    Boolean success;

    public AniResponseDto() {
    }

    public AniResponseDto(Boolean success) {
        this.success = success;
    }
}
