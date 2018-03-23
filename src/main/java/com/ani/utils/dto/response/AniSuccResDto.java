package com.ani.utils.dto.response;

/**
 * Created by zhanglina on 18-3-15.
 */
public class AniSuccResDto extends AniResponseDto {

    Object data;

    public AniSuccResDto() {
        this.success = true;
        this.data = null;
    }

    public AniSuccResDto(Object data) {
        this.success = true;
        this.data = data;
    }

}
