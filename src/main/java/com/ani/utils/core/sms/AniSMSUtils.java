package com.ani.utils.core.sms;

import java.util.Random;

/**
 * Created by hey on 16-8-22.
 */
public class AniSMSUtils {

    public static String sms253url = "http://222.73.117.169/msg/HttpBatchSendSM?";
    public static String sms253username = "N6212140";
    public static String sms253password = "Ps4dcfed";


    //generate six rand num
    public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
