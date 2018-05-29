package com.ani.utils.dto.response;

import java.io.Serializable;

/**
 * Created by zhanglina on 18-3-15.
 */
public class AniSuccResDto extends AniResponseDto implements Serializable {

    private static final long serialVersionUID = 1497362412500062742L;
    Object data;

    public AniSuccResDto() {
        this.success = true;
        this.data = null;
    }

    public AniSuccResDto(Object data) {
        this.success = true;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
