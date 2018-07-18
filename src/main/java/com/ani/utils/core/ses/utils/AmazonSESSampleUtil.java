package com.ani.utils.core.ses.utils;

import com.ani.utils.core.ses.dto.SendEmailInputDto;
import com.ani.utils.exception.AniRuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by ebrx on 18-7-9.
 * Last Modified by xuben on 18-7-18.
 */

public class AmazonSESSampleUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonSESSampleUtil.class);

    static final String FROM = "no-reply@ani.cloud";

    static final String FROMNAME = "AniCloud";

    static final String SMTP_USERNAME = "AKIAJVNZ2LY34H3M7AUQ";

    static final String SMTP_PASSWORD = "AtH8a42LWgpO28VukFh21jZyd5LzFCQoab8QYIE4n3u4";

    static final String HOST = "email-smtp.us-west-2.amazonaws.com";

    static final int PORT = 587;

    public static void sendHtmlMail(String toAddress, String subject, String content) throws AniRuleException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        Transport transport = null;
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            msg.setSubject(subject);
            msg.setContent(content, "text/html");

            transport = session.getTransport();

            LOGGER.info("Sending...");
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            LOGGER.info("Email sent!");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            throw new AniRuleException("The email was not sent.");
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            throw new AniRuleException("from address error.");
        } finally {
            try {
                transport.close();
            } catch (MessagingException e) {
                throw new AniRuleException("Transport Not closed."); //todo 为啥这么写, 全用AniRuleException？
            }
        }
    }

    public static void sendPasswordMail(String toAddress, String employeeid, byte[] password) throws AniRuleException{
        String BODY = String.join(
                System.getProperty("line.separator"),
                "<p style=\"margin:0px;padding:0px;\"><strong style=\"font-size:14px;line-height:30px;color:#333333;font-family:arial,sans-serif;\">Dear user:</strong></p>",
                "<p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">Hello! Thank you for using the AniCloud service. Your account info:</p>",
                "<p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\"><b style=\"font-size:18px;color:#ff9900\">id: " + employeeid + "<br/>password: " + new String(password) + "</b></p>",
                System.getProperty("line.separator"),
                System.getProperty("line.separator"),
                "<p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">Cel Platform Team</p>",
                "<p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">" + getDate() + "</p>"
        );
        sendHtmlMail(toAddress, "Ani.cloud account info", BODY);
    }


    public static void sendVerifyMail(SendEmailInputDto sendEmailInputDto) throws AniRuleException{
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
        final String SUBJECT = "AniCloud account email authentication";

        sendHtmlMail(sendEmailInputDto.getToAddress(), SUBJECT, BODY);
    }

    private static String  getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }
}
