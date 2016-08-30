package com.ani.utils.core.sms;


import java.io.Serializable;
import java.util.List;

/**
 * Created by hey on 16-8-22.
 */
public interface AniSMS{
    //public String registerCheckPhone(String phoneNumber,String codeF,String msg);

    public Boolean validateCode(String savedCode,String code);

    public String getIdentifyingCode(String phoneNumber,String msg);

    public String sendMessages(String phoneNumbers, String msg);

}
