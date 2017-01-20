package com.ani.utils.exception;

/**
 * User: yeqingzhou
 * Date: 12-9-28
 */
public class AniUserAuthException extends AniBaseException {

    private static final long serialVersionUID = -6835080361834780463L;

    public AniUserAuthException() {
        super();
    }

    public AniUserAuthException(String msg) {
        super(msg, "AniUserAuthException");
    }
}
