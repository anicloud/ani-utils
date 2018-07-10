package com.ani.utils.core.ses;

import com.ani.utils.core.ses.dto.SendEmailInputDto;
import com.ani.utils.core.ses.dto.SendEmailOutputDto;
import com.ani.utils.exception.AniRuleException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * Created by ebrx on 18-7-9.
 */
public interface AmazonSimpleEmailService {

    /**
     * <h2>Amazon Simple Email Service</h2>
     * @param sendEmailInputDto
     * @return SendEmailOutputDto status:[S：success]/[E：error]
     * @throws AniRuleException
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public SendEmailOutputDto sendEmail(SendEmailInputDto sendEmailInputDto) throws AniRuleException, UnsupportedEncodingException, MessagingException;
}
