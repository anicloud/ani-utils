package com.ani.utils.core.data.type;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yeh on 15-10-16.
 */
public enum PrivilegeType {

    READ(1),
    WRITE(2),
    EXECUTE(4),
    ADMIN(8);

    private final Integer typeId;

    PrivilegeType(int typeId) {
        this.typeId = typeId;
    }

    public static Set<PrivilegeType> getByTypes(int typesSum) {
        PrivilegeType[] types = PrivilegeType.values();
        Set<PrivilegeType> curTypes = null;
        for (
                int oneTypeIdx = types.length - 1;
                oneTypeIdx >= 0; oneTypeIdx--) {
            PrivilegeType oneType = types[oneTypeIdx];
            if (typesSum < oneType.getId())
                continue;
            if (curTypes == null)
                curTypes = new HashSet<>(oneTypeIdx + 1, 1f);
            typesSum = typesSum - oneType.getId();
            curTypes.add(oneType);
            if (typesSum == 0) break;
        }
        return curTypes;
    }

    public static short getByTypes(Set<PrivilegeType> privilegeTypes) {
        int types = 0;
        if (privilegeTypes != null && privilegeTypes.size() != 0) {
            for (PrivilegeType privilegeType : privilegeTypes) {
                types = types + privilegeType.getId();
            }
        }
        return (short) types;
    }

    public int getId() {
        return this.typeId;
    }

}
