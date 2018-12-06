package com.ani.utils.dto.response;

import com.ani.utils.exception.AniBaseException;
import com.ani.utils.exception.AniRuleException;

import java.io.Serializable;

/**
 * Created by zhanglina on 18-3-15.
 */
public class AniErrResDto extends AniResponseDto implements Serializable {

    private static final long serialVersionUID = 3839521096876519055L;
    String message;

    public AniErrResDto() {
        this.success = false;
        this.message = "error";
    }

    public AniErrResDto(String errMsg) {
        this.success = false;
        this.message = errMsg;
    }

    public AniErrResDto(AniBaseException e) {
        this.success = false;
        this.message = e.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
