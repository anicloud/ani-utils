package com.ani.utils.dto;

import com.ani.utils.exception.AniRuleException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by yeh on 14-8-24.
 */
public class AniDto implements Serializable {

    public static void batchCheckFields(List<? extends AniDto> aniDtoList) throws AniRuleException {
//        if(AniGeneralUtils.isCollectionEmpty(aniDtoList)) throw new AniRuleException("DTOS_ARE_REQUIRED");
        StringBuilder finalMsg = new StringBuilder("");
        Boolean isError = false;
        for (AniDto oneDto : aniDtoList) {
            try {
                oneDto.checkFields();
            } catch (AniRuleException e) {
                if (isError) {
                    finalMsg.append("\n");
                } else {
                    isError = true;
                }
                finalMsg.append(e.getMessage());
            }
        }
        if (isError) {
            throw new AniRuleException(finalMsg.toString());
        }
    }

    public static void checkFields(AniDto dto) throws AniRuleException {
        if (dto == null) throw new AniRuleException(AniRuleException.Type.OBJECT_EMPTY);
        dto.checkFields();
    }

    public void checkFields() throws AniRuleException {
        Field[] fields = this.getClass().getFields();
        checkFields(fields);
    }

    public void checkFieldsByName(String[] fieldsName) {
        Class curClass = this.getClass();
        Field[] fields = new Field[fieldsName.length];
        for (int oneFieldNameIdx = 0; oneFieldNameIdx < fieldsName.length; oneFieldNameIdx++) {
            try {
                fields[oneFieldNameIdx] = curClass.getField(fieldsName[oneFieldNameIdx]);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkFields(Field[] fields) throws AniRuleException {
        checkFieldsExistence(fields);
        checkFieldsFormatLegality(fields);
    }

    private void checkFieldsExistence(Field[] fields) throws AniRuleException {
        boolean isError = false;
        StringBuilder notification = new StringBuilder("REQUIRED_FIELDS_EMPTY:");
        try {
            for (Field oneField : fields) {
                boolean isRequired = (oneField.getAnnotation(AniRequiredField.class) != null);
                boolean isFieldNull = (oneField.get(this) == null);
                if (isRequired && isFieldNull) {
                    isError = true;
                    notification.append(
                            String.format(" [%s]", oneField.getName())
                    );
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (isError) {
            throw new AniRuleException(notification.toString());
        }
    }

    private void checkFieldsFormatLegality(Field[] fields) throws AniRuleException {
        boolean isError = false;
        StringBuilder notification = new StringBuilder("FIELDS_FORMAT_ERROR:");
        try {
            for (Field oneField : fields) {
                AniFieldFormat fieldFormat = oneField.getAnnotation(AniFieldFormat.class);
                boolean isFormatted = (fieldFormat != null);
                String fieldValue = String.valueOf(oneField.get(this));
                boolean isMatched = (isFormatted && fieldValue != null && fieldValue.matches(fieldFormat.pattern().getPattern()));
                if (isFormatted && !isMatched) {
                    isError = true;
                    notification.append(
                            String.format(" [%s]", oneField.getName())
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isError) {
            throw new AniRuleException(notification.toString());
        }
    }

    public static void checkDto(AniDto dto, String elem) throws AniRuleException {
        if(dto == null)
            throw new AniRuleException(elem + "_IS_REQUIRED");
        dto.checkFields();
    }



}
