package com.ani.utils.dto;

import com.ani.utils.exception.AniAuthException;
import com.ani.utils.exception.AniDataException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AniFieldUtils {

    public static void verifyRequiredElements(Map<String, String> fieldsValue, String[] requiredFieldKeys) throws AniAuthException {
        StringBuilder errorMsg = new StringBuilder();
        for (String oneKey : requiredFieldKeys) {
            if (!isFormFieldIgnored(fieldsValue.get(oneKey))) {
                errorMsg.append(" [" + oneKey + "] ");
            }
        }
        if (errorMsg.length() > 0) {
            throw new AniAuthException(
                    "REQUIRED_FIELDS_EMPTY: " +
                            errorMsg.toString()
            );
        }
    }

    public static void verifyRequiredElementsInObject(Object oneObj, String[] requiredFieldsName) throws AniDataException, AniAuthException {
        StringBuilder errorMsg = new StringBuilder();
        for (String oneKey : requiredFieldsName) {
            Object oneFieldValue = null;
            try {
                oneFieldValue = oneObj.getClass().getField(oneKey).get(oneObj);
            } catch (Exception e) {
                throw new AniDataException("FIELD_NOT_EXISTS");
            }
            if (!isFormFieldIgnored(oneFieldValue)) {
                errorMsg.append(" [" + oneKey + "] ");
            }
        }
        if (errorMsg.length() > 0) {
            throw new AniAuthException(
                    "REQUIRED_FIELDS_EMPTY: " +
                            errorMsg.toString()
            );
        }
    }

    private static Boolean isFormFieldIgnored(Object oneArg) {
        if (isFieldEmpty(oneArg) || oneArg.equals("0")) {
            return false;
        }
        return true;
    }

    public static Boolean isFieldEmpty(Object oneField) {
        if (oneField == null) {
            return true;
        }
        if (oneField instanceof String) {
            return StringUtils.isEmpty(String.valueOf(oneField));
        } else if (oneField instanceof List) {
            return ((List) oneField).size() < 1;
        } else {
            return false;
        }
    }

    public static List<Long> generateIdListFromStrArray(String[] fromArray) {
        List<Long> resultIdList = new ArrayList<Long>();
        for (String oneArrElem : fromArray) {
            resultIdList.add(Long.parseLong(oneArrElem));
        }
        return resultIdList;
    }
}
