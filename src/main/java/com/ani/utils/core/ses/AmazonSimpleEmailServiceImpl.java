package com.ani.utils.core.ses;

import com.ani.utils.core.ses.dto.SendEmailInputDto;
import com.ani.utils.core.ses.dto.SendEmailOutputDto;
import com.ani.utils.core.ses.utils.AmazonSESSampleUtil;
import com.ani.utils.exception.AniRuleException;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ebrx on 18-7-9.
 */
public class AmazonSimpleEmailServiceImpl implements AmazonSimpleEmailService {
    @Override
    public SendEmailOutputDto sendEmail(SendEmailInputDto sendEmailInputDto) throws AniRuleException, MessagingException, UnsupportedEncodingException {
        if (null == sendEmailInputDto) throw new AniRuleException("SEND_EMAIL_DTO_REQUIRED");
        sendEmailInputDto.checkFields();
        return AmazonSESSampleUtil.sendMail(sendEmailInputDto);
    }
}
