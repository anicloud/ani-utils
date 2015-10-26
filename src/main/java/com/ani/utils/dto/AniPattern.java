package com.ani.utils.dto;

/**
 * Created by yeh on 14-8-25.
 */
public enum AniPattern {
    //PATTERN STRINGS
    // -USER INFO
    DEFAULT_PATTERN(""),
    EMAIL_PATTERN("[a-z0-9_-]+@[a-z0-9_-]+\\.[a-z\\.]+"),
    SCREENNAME_PATTERN("[a-zA-Z0-9-_\\.]+"),
    MD5_PATTERN("[0-9a-f]{32}"),
    // -DEVICE
    MAC_ADDRESS("([0-9a-f]{2}:){5}[0-9a-f]{2}"),
    // -APP & API INFO
    APP_NAME_PATTERN("[^\\p{Punct}]{1,}"),
    API_N_PARAM_NAME_PATTERN("[a-zA-Z0-9]+"),
    API_PARAM_VALUE_PATTERN("[^\\p{Punct}]{1,}"),

    URL_PATTERN("(http:|https:)//[^[A-Za-z0-9\\\\._\\\\?%&+\\\\\\-=/#:]]*");

    private final String pattern;

    AniPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}
