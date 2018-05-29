package com.ani.utils.core.sms;


import com.ani.utils.exception.AniRuleException;

/**
 * Created by hey on 16-8-22.
 */
public interface AniSMS{
    //public String registerCheckPhone(String phoneNumber,String codeF,String msg);

    public Boolean validateCode(String savedCode,String code);

    public String getIdentifyingCode(String phoneNumber,String msg);

    public String sendMessages(String phoneNumbers, String msg);
    public String sendMessageByAlis(String phoneNumbers, String msg) throws AniRuleException;
}
