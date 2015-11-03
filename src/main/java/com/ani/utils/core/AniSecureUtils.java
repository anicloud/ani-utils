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

    public static String getHexStringFromByte(byte[] hashByte) {
        int length = hashByte.length;
        char[] result = new char[length * 2];
        int k = 0;
        for (int i = 0; i < length; i++) {
            byte byte0 = hashByte[i];
            result[k++] = hexDigits[byte0 >>> 4 & 0xf];
            result[k++] = hexDigits[byte0 & 0xf];
        }
        return String.valueOf(result);
    }


    public static Long generateTimeMillis() {
        return System.currentTimeMillis();
    }

    public static Long generateRandomUniqueIdLong() {
        Random random = new Random(System.currentTimeMillis() + new Random().nextLong());
        return random.nextLong();
    }

    public static Integer generateRandomUniqueIdInteger() {
        Random random = new Random(System.currentTimeMillis() + new Random().nextInt());
        return random.nextInt();
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
            return getHexStringFromByte(hmac);
        } catch (Exception e) {
//            Logger.error(e.getMessage());
            return null;
        }
    }
}
