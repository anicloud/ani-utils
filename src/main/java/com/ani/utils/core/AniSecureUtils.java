package com.ani.utils.core;

import com.ani.utils.exception.AniAuthException;
import com.ani.utils.exception.AniRuleException;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class AniSecureUtils {
    public static Long secureTimeoutMills = 10000L;
    private static char hexDigits[] = {
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f'
    };

    public static Long generateTimeMillis() {
        return System.currentTimeMillis();
    }

    private static void checkTimestampLegality(Long timestamp) throws AniAuthException {
        if (System.currentTimeMillis() - timestamp > secureTimeoutMills) {
            throw new AniAuthException("AUTHENTICATION_REQUEST_OUT_OF_TIME");
        }
    }

    public static String generateRequestHash(String requestMessage, Long requestTimestamp, String secretHash) {
        return generateHMACString(secretHash, requestMessage + String.valueOf(requestTimestamp));
    }

    public static String generateSecretHash(String firstHashKey, String message) {
        return generateHMACString(firstHashKey, message);
    }

    public static String generateSecretToken(String specificMessage) {
        return generateHMACString(
                String.valueOf(generateRandomUniqueId()) + "Anicloud",
                specificMessage
        );
    }
//    private static void checkTimeout(Long timestamp){
//        Long timeSpan = System.currentTimeMillis() - timestamp;
//        if(timeSpan > secureTimeoutMills || timeSpan < 0){
//            throw new AniAuthException("REQUEST_TIME_OUT");
//        }
//    }

    private static void checkRequestKeyLegality(String requestHashKey, String requestMessage, Long timestamp, String secretHash) throws AniAuthException {
//        String requestHmacStr = generateHMACString(secretHash, requestMessage + String.valueOf(timestamp));
        String requestHmacStr = generateRequestHash(requestMessage, timestamp, secretHash);
        if (!StringUtils.equals(requestHmacStr, requestHashKey)) {
            throw new AniAuthException("REQUEST_ILLEGAL");
        }
    }

//    private static void checkSecretKeyLegality(String requestHashKey, String requestMessage, String secretHash) throws AniAuthException {
////        String secretHmacStr = generateHMACString(requestHashKey, requestMessage);
//        String secretHmacStr = generateSecretHash(requestHashKey, requestMessage);
//        if(!StringUtils.equals(secretHmacStr, secretHash.trim())){
//            throw new AniAuthException("PASSWORD_OR_TOKEN_ILLEGAL");
//        }
//    }

    public static void checkAuthMessageLegality(String requestHashKey, String requestMessage, Long timestamp, String secretHash) throws AniAuthException {
        checkTimestampLegality(timestamp);
        checkRequestKeyLegality(requestHashKey, requestMessage, timestamp, secretHash);
//        checkSecretKeyLegality(requestHashKey, requestMessage, secretHash);
    }

    public static Long generateRandomUniqueId() {
        Long currentMillis = System.currentTimeMillis();

        Random random = new Random();
        Long currentRandom = random.nextLong();

        Long randomNumber = currentMillis + currentRandom;
        return randomNumber;
    }

    public static byte[] generateHashByte(String algorithm, byte[] srcByte) throws AniRuleException {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new AniRuleException("NO_SUCH_HASH_ALGORITHM:" + algorithm);
        }
        messageDigest.update(srcByte);
        return messageDigest.digest();
    }

    public static String generateHashString(String algorithm, String src) throws AniRuleException {
        byte[] srcByte = src.getBytes();
        StringBuilder finalMD5 = new StringBuilder();
        byte generatedMD5Byte[] = generateHashByte(algorithm, srcByte);
        for (byte curByte : generatedMD5Byte) {
            finalMD5.append(hexDigits[curByte >>> 4 & 0xf]);
            finalMD5.append(hexDigits[curByte & 0xf]);
        }
        return finalMD5.toString();
    }

    public static byte[] generateHmacByte(byte[] key, byte[] message, String hashAlgorithm) throws AniRuleException {
        SecretKey secretKey = new SecretKeySpec(key, hashAlgorithm);
        Mac mac = null;
        try {
            mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return mac.doFinal(message);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new AniRuleException("NO_SUCH_HASH_ALGORITHM:" + hashAlgorithm);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new AniRuleException("KEY_IS_INVALID");
        }

    }

    public static String generateHMACString(String key, String message) {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacMD5");
        try {
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            byte[] hmac = mac.doFinal(message.getBytes());

            int length = hmac.length;
            char[] result = new char[length * 2];
            int k = 0;
            for (int i = 0; i < length; i++) {
                byte byte0 = hmac[i];
                result[k++] = hexDigits[byte0 >>> 4 & 0xf];
                result[k++] = hexDigits[byte0 & 0xf];
            }
            return String.valueOf(result);
        } catch (Exception e) {
//            Logger.error(e.getMessage());
            return null;
        }
    }
}
