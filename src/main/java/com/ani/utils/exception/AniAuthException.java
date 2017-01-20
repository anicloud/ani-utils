package com.ani.utils.exception;

/**
 * User: yeqingzhou
 * Date: 12-9-28
 */
public class AniAuthException extends AniBaseException {
    /**
     *
     */
    private static final long serialVersionUID = -6835080361834780463L;

    public AniAuthException() {
        super();
    }

    public AniAuthException(String msg) {
        super(msg, "AniAuthException");
    }
}
