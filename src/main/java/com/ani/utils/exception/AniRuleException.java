package com.ani.utils.exception;

public class AniRuleException extends AniBaseException {

    private static final long serialVersionUID = 1534289251958683523L;

    public AniRuleException() {
        super();
    }

    public AniRuleException(Type msg) {
        super(msg.name(), "AniRuleException");
    }

    public AniRuleException(String msg) {
        super(msg, "AniRuleException");
    }

    public enum Type {
        EMAIL_EXITS,
        PHONE_EXITS,
        ACCOUNT_NOT_EXITS,
        PASSWORD_INCORRECT,
        TYPE_ERROR,
        OBJECT_EMPTY,
        CODE_EXPIRED,
        PHONE_ERROR,
        EMAIL_ERROR,
        CODE_ERROR,
    }
}
