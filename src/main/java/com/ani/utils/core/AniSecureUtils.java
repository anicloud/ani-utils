package com.ani.utils.core;

import com.ani.utils.exception.*;

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
        return Math.abs(random.nextLong());
    }

    public static Integer generateRandomUniqueIdInteger() {
        Random random = new Random(System.currentTimeMillis() + new Random().nextInt());
        return Math.abs(random.nextInt());
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
        StringBuilder finalHash = new StringBuilder();
        byte generatedHashByte[] = generateHashByte(algorithm, srcByte);
        for (byte curByte : generatedHashByte) {
            finalHash.append(hexDigits[curByte >>> 4 & 0xf]);
            finalHash.append(hexDigits[curByte & 0xf]);
        }
        return finalHash.toString();
    }

    public static byte[] generateMd5HashByte(byte[] srcByte) throws AniRuleException {
        return generateHashByte("md5", srcByte);
    }

    public static byte[] generateHMACByte(byte[] key, byte[] message, String hashAlgorithm) throws AniRuleException {
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

    public static byte[] generateHMACByteMd5(byte[] key, byte[] message) {
        byte[] hmacByteMd5 = null;
        try {
            hmacByteMd5 = generateHMACByte(key, message, "HmacMD5");
        } catch (AniRuleException e) {
            e.printStackTrace();
        }
        return hmacByteMd5;
    }

    public static byte[] generateHMACByteSha2(byte[] key, byte[] message) {
        byte[] hmacByteMd5 = null;
        try {
            hmacByteMd5 = generateHMACByte(key, message, "HmacSHA256");
        } catch (AniRuleException e) {
            e.printStackTrace();
        }
        return hmacByteMd5;
    }
//
//    public static String generateHMACString(String key, String message) {
//        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacMD5");
//        try {
//            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
//            mac.init(secretKey);
//            byte[] hmac = mac.doFinal(message.getBytes());
//            return getHexStringFromByte(hmac);
//        } catch (Exception e) {
////            Logger.error(e.getMessage());
//            return null;
//        }
//    }
    public static String generateHMACString(String key,String message){
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),"HmacSHA256");
        try {
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            byte[] hmac=mac.doFinal(message.getBytes());
            return getHexStringFromByte(hmac);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] generateMd5Token(byte[] srcToken) {
        return generateHMACByteMd5(srcToken, AniGeneralUtils.longToByte(generateRandomUniqueIdLong()));
    }

    public static byte[] generateSha256Token(byte[] srcToken) {
        return generateHMACByteSha2(srcToken, AniGeneralUtils.longToByte(generateRandomUniqueIdLong()));
    }

    public static byte[] generateRequestCode(Long timestamp, byte[] token) {
        return generateHMACByteMd5(
                AniGeneralUtils.longToByte(timestamp),
                token
        );
    }
}
