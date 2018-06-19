package com.ani.test.utils.core;


import com.ani.utils.core.AniGeneralUtils;
import com.ani.utils.dto.AniPattern;
import org.junit.Test;

public class AniGeneralUtilsTest {

    @Test
    public void testCombineIntToLong() {

        System.out.println(
                AniGeneralUtils.combineIntToLong(100, 200)
        );
    }

    @Test
    public void testUrlRegEx() {
        String url = "1";
        boolean matches = url.matches(AniPattern.URL_PATTERN.getPattern());
        System.out.println(matches);
    }

}
