package com.ani.utils.core.sms;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by hey on 16-8-22.
 */
@Service(value = "ani253SMS")
public class Ani253SMS implements AniSMS {

    @Override
    public Boolean validateCode(String savedCode, String code) {
        if(savedCode == null)
        {
            return false;
        }else if(savedCode.equals(code)) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public String getIdentifyingCode(String phoneNumber,String msg) {
        String state =null;
        try {
            //send short message
            String url = AniSMSUtils.sms253url + "account=" + AniSMSUtils.sms253username + "&pswd=" + AniSMSUtils.sms253password + "&mobile="
                    + phoneNumber + "&msg=" + msg + "&needstatus=true";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            state = result.split("\n")[0].split(",")[1];
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return state;
    }
    @Override
    public String sendMessages(String phoneNumbers, String msg) {
        String url = AniSMSUtils.sms253url +"account=" + AniSMSUtils.sms253username +"&pswd="+ AniSMSUtils.sms253password +"&mobile="
                +phoneNumbers +"&msg=" +msg + "&needstatus=true";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        String state = result.split("\n")[0].split(",")[1];
        return state;
    }
}
