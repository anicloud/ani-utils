package com.ani.utils.core.sms;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ani.utils.exception.AniRuleException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Override
    public String sendMessageByAlis(String phoneNumbers, String msg) throws AniRuleException{

//        //初始化ascClient需要的几个参数
        try {
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            //替换成你的AK
            final String accessKeyId = "LTAIL1AOSN8lXgL2";//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = "BkvnS9DAPMwW3dbceddROeF3QEI4Q2";//你的accessKeySecret，参考本文档步骤2
            //初始化ascClient,暂时不支持多region（请勿修改）
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phoneNumbers);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("Ani云");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode("SMS_136160796");
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"code\":\""+msg+"\"}");

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            return null;
        }catch (ClientException e){
            throw new AniRuleException("SEND_MAIL_FIELD");
        }

    }
    public static void main(String[] args){
        Ani253SMS ani253SMS=new Ani253SMS();
//        try {
//            ani253SMS.sendMessageByAlis("13121386296");
//
//        } catch (AniRuleException e) {
//            e.printStackTrace();
//        }
    }

}
