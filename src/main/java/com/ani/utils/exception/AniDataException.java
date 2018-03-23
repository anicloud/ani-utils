package com.ani.utils.exception;

public class AniDataException extends AniBaseException {

    private static final long serialVersionUID = -2486231602507288747L;

    public AniDataException() {
        super();
    }

    public AniDataException(String msg) {
        super(msg, "AniFormatException");
    }

    public enum Type {

    }
}
