package com.ani.utils.exception;

public class AniRuleException extends AniBaseException {

    private static final long serialVersionUID = 1534289251958683523L;

    public AniRuleException(){
        super("ANI_INTERNAL_ERROR", "AniRuntimeException");
    }

    public AniRuleException(String msg){
        super(msg, "AniRuntimeException");
    }
}
