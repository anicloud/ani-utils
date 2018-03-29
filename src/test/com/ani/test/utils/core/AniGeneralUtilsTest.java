package com.ani.test.utils.core;


import com.ani.utils.core.AniGeneralUtils;
import org.junit.Test;

public class AniGeneralUtilsTest {

    @Test
    public void testCombineIntToLong() {
        System.out.println(
                AniGeneralUtils.combineIntToLong(100, 200)
        );
    }

}
