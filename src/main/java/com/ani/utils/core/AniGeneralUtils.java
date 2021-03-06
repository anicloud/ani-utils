package com.ani.utils.core;

import com.ani.utils.exception.AniAuthException;
import com.ani.utils.exception.AniRuleException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.*;

public class AniGeneralUtils {

    public static Boolean isListEmpty(List oneList) {
        if (oneList == null || oneList.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void normalizeBoolean(Boolean boo) {
        boo = (boo == null ? false : boo);
    }

    public static Boolean isCollectionEmpty(Collection oneCol) {
        return (oneCol == null || oneCol.isEmpty() || oneCol.size() < 1);
    }

    public static Map deleteEmptyMapElements(Map srcMap) {
        Map oneMap = srcMap;
        for (Object oneKey : oneMap.keySet()) {
            Object oneElement = oneMap.get(oneKey);
            if (
                    (oneElement == null)
                            ||
                            (oneElement instanceof String
                                    && StringUtils.isEmpty(String.valueOf(oneElement))
                            )
                    ) {
                oneMap.remove(oneKey);
                continue;
            }
        }
        return oneMap;
    }

    public static Long translateStrId(String srcId) {
        try {
            return Long.parseLong(srcId);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Long> obtainSingleIdList(Long oneId) {
        List<Long> singleIdList = new ArrayList<Long>();
        singleIdList.add(oneId);
        return singleIdList;
    }

    public static <T> List<T> obtainSingleObjList(T oneObj) {
        List<T> oneList = new ArrayList<T>();
        oneList.add(oneObj);
        return oneList;
    }

    public static List<String> searchClassFields(Class oneClass) {
        List<String> searchFields = new ArrayList<String>();
        for (Field oneField : oneClass.getFields()) {
            searchFields.add(oneField.getName());
        }
        return searchFields;
    }

    public static Boolean isListContainsDuplicateElement(List oneList) {
        Boolean containsDuplicateElement = false;
        for (Integer i = 0; i < oneList.size() - 1; i++) {
            for (Integer j = i + 1; j < oneList.size(); j++) {
                if (oneList.get(i).equals(oneList.get(j))) {
                    return true;
                }
            }
        }
        return containsDuplicateElement;
    }

    public static int countCollectionsSize(Collection... cols) {
        int totalSize = 0;
        if (cols == null || cols.length < 1)
            return totalSize;
        for (Collection oneList : cols) {
            if (oneList == null)
                continue;
            totalSize += oneList.size();
        }
        return totalSize;
    }

    public static <T> List<T> mergeCollectionsToList(Collection<T>... cols) throws AniRuleException {
        if (cols == null || cols.length < 1) {
            throw new AniRuleException("COLLECTIONS_REQUIRED");
        }
        List<T> mergedList = new ArrayList<T>(countCollectionsSize(cols));
        for (Collection<T> oneList : cols) {
            if (oneList == null)
                continue;
            mergedList.addAll(oneList);
        }
        return mergedList;
    }

    public static int getLongestCollectionLength(Collection... cols) {
        int longestColLength = 0;
        if (cols == null || cols.length == 0)
            return 0;
        for (Collection oneCol : cols) {
            if (oneCol == null || oneCol.size() < longestColLength)
                continue;
            longestColLength += oneCol.size();
        }
        return longestColLength;
    }

    public static void stringsEqual(String string1, String string2, String errorNotification) throws AniAuthException {
        if (!StringUtils.equals(string1.trim(), string2.trim())) throw new AniAuthException(errorNotification);
    }

    public static byte[] longToByte(Long longObj) {
        return ByteBuffer.allocate(Long.SIZE).putLong(longObj.longValue()).array();
    }

    public static long combineIntToLong(int int0, int int1) {
        long result = int0;
        result = result << 32;
        result += int1;
        return result;
    }

    public static int longToFrontInt(long data) {
        data = data >> 32;
        int result = (int) data;
        return result;
    }

    public static <T> Map<ByteBuffer, T> fromByteToByteBufferMap(Map<byte[], T> byteMap) {
        if (byteMap == null || byteMap.size() < 1) {
            return new HashMap<ByteBuffer, T>(1);
        }
        Map<ByteBuffer, T> resultByteBufferMap = new HashMap<ByteBuffer, T>(1);
        for (byte[] oneByteKey : byteMap.keySet()) {
            resultByteBufferMap.put(
                    ByteBuffer.wrap(oneByteKey),
                    byteMap.get(oneByteKey));
        }

        return resultByteBufferMap;
    }
}
