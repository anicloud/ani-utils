package com.ani.utils.core.ses.dto;

import com.ani.utils.dto.AniDto;

/**
 * Created by ebrx on 18-7-9.
 */
public class SendEmailOutputDto extends AniDto {

    private static final long serialVersionUID = 3951499887891605189L;
    private String status;
    private String msg;

    public SendEmailOutputDto() {
    }

    public SendEmailOutputDto(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
