package com.ani.utils.network;

import com.ani.utils.exception.AniRuleException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yeh on 15-11-21.
 */
public class AniNetworkUtils {

    public static String getIpv4FromByte(byte[] ipv4Address) throws AniRuleException {
        Integer[] ipv4AddIntArr = checkByteIpV4Legality(ipv4Address);
        return StringUtils.join(ipv4AddIntArr, ".");
    }

    public static Integer[] checkByteIpV4Legality(byte[] ipv4Address) throws AniRuleException {
        // length legality
        if(
                ipv4Address == null         // is null
                || ipv4Address.length != 4  // length
                ){
            throw new AniRuleException("BYTE_IPV4_LENGTH_ILLEGAL");
        }

        Integer[] ipv4AddIntArr = new Integer[4];

        // value legality
        short verifiedSegNum = 0;
        for(int oneSegIdx = 0; oneSegIdx < ipv4Address.length; oneSegIdx++){
            ipv4AddIntArr[oneSegIdx] = ipv4Address[oneSegIdx] & 0xff;
            if(
                    (ipv4AddIntArr[oneSegIdx] == 0)
                    && verifiedSegNum == 0
                    ) {
            }
            if(ipv4AddIntArr[oneSegIdx] != 0) verifiedSegNum++;
        }
        return ipv4AddIntArr;
    }

}
