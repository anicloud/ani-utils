package com.ani.utils.core.ses.dto;

import com.ani.utils.dto.AniDto;

/**
 * Created by ebrx on 18-7-9.
 */
public class SendEmailInputDto extends AniDto {
    private static final long serialVersionUID = -8281862157694807284L;

    private String toAddress;
    private String verCode;

    public SendEmailInputDto() {
    }

    public SendEmailInputDto(String toAddress, String subject, String body) {
        this.toAddress = toAddress;
        this.verCode = body;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String body) {
        this.verCode = body;
    }
}
