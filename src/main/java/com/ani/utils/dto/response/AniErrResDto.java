package com.ani.utils.dto.response;

/**
 * Created by zhanglina on 18-3-15.
 */
public class AniErrResDto extends AniResponseDto {

    String message;

    public AniErrResDto() {
        this.success = false;
        this.message = "error";
    }

    public AniErrResDto(String errMsg) {
        this.success = false;
        this.message = errMsg;
    }

}
