package com.ani.utils.core;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yeh on 15-9-26.
 */
public class AniCacheUtils {

    public static String getRedisKey(String... parts) {
        return StringUtils.join(parts, ':');
    }

    public static Set<byte[]> getByteSetFromString(Set<String> strSet) {
        if (strSet == null || strSet.size() == 0) {
            return new HashSet<byte[]>(1, 1f);
        }
        Set<byte[]> byteSet = new HashSet<byte[]>(strSet.size(), 1f);
        for (String oneStr : strSet) {
            byteSet.add(oneStr.getBytes());
        }
        return byteSet;
    }

    public static String getLongStrExp(Long longNum){
        return new String(AniGeneralUtils.longToByte(longNum));
    }
}
