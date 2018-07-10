package com.ani.utils.core.ses.utils;

import com.ani.utils.core.ses.dto.SendEmailInputDto;
import com.ani.utils.core.ses.dto.SendEmailOutputDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by ebrx on 18-7-9.
 */
@Service
public class AmazonSESSampleUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonSESSampleUtil.class);

    static final String FROM = "no-reply@ani.cloud";

    static final String FROMNAME = "AniCloud";

    static final String SMTP_USERNAME = "AKIAJHWJV5SKZKLWQNPQ";

    static final String SMTP_PASSWORD = "Ag8cG+8KMoOVTVkk5bSVjqAKAv4mUTn/9AqADJSUaE3S";

    static final String HOST = "email-smtp.us-west-2.amazonaws.com";

    static final int PORT = 587;

    static final String SUBJECT = "AniCloud account email authentication";


    public SendEmailOutputDto sendMail(SendEmailInputDto sendEmailInputDto) throws UnsupportedEncodingException, MessagingException {
        String BODY = String.join(
                System.getProperty("line.separator"),
                "<p style=\"margin:0px;padding:0px;\"><strong style=\"font-size:14px;line-height:30px;color:#333333;font-family:arial,sans-serif;\">Dear user:</strong></p>",
                "<p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">Hello! Thank you for using the AniCloud service, you are in the process of verifying your mailbox. The verification code for this request is:</p>",
                "<p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\"><b style=\"font-size:18px;color:#ff9900\">" + sendEmailInputDto.getVerCode() + "</b><span style=\"margin:0px;padding:0px;margin-left:10px;line-height:30px;font-size:14px;color:#979797;font-family:'宋体',arial,sans-serif;\">(" +
                        "In order to protect the security of your account, please complete the verification within 5 minutes.)</span></p>",
                System.getProperty("line.separator"),
                System.getProperty("line.separator"),
                "<p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">Cel Platform Team</p>",
                "<p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">" + getDate() + "</p>"
        );

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(sendEmailInputDto.getToAddress()));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY, "text/html");

        Transport transport = session.getTransport();

        SendEmailOutputDto sendEmailOutputDto = new SendEmailOutputDto();
        try {
            LOGGER.info("Sending...");
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            transport.sendMessage(msg, msg.getAllRecipients());
            LOGGER.info("Email sent!");
            sendEmailOutputDto.setStatus("S");
            sendEmailOutputDto.setMsg("success");
            return sendEmailOutputDto;
        } catch (Exception ex) {
            LOGGER.info("The email was not sent.");
            LOGGER.info("Error message: " + ex.getMessage());
            sendEmailOutputDto.setStatus("E");
            sendEmailOutputDto.setMsg(ex.getMessage());
            return sendEmailOutputDto;
        } finally {
            transport.close();
        }
    }
    private static String  getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }
}
