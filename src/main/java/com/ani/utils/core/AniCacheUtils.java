package com.ani.utils.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yeh on 15-9-26.
 */
@Service
public class AniCacheUtils {

    public String getRedisKey(String... parts) {
        return StringUtils.join(parts, ':');
    }

    public Set<byte[]> getByteSetFromString(Set<String> strSet) {
        if (strSet == null || strSet.size() == 0) {
            return new HashSet<byte[]>(1, 1f);
        }
        Set<byte[]> byteSet = new HashSet<byte[]>(strSet.size(), 1f);
        for (String oneStr : strSet) {
            byteSet.add(oneStr.getBytes());
        }
        return byteSet;
    }

    public String getLongStrExp(Long longNum){
        return new String(AniGeneralUtils.longToByte(longNum));
    }
}
