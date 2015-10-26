package com.ani.utils.exception;

public class AniNeedApprovalException extends AniBaseException {

    private static final long serialVersionUID = -1669027718400384479L;

    public AniNeedApprovalException() {
        super("NEED_APPROVAL", "AniNeedApprovalException");
    }

    public AniNeedApprovalException(String msg) {
        super(msg, "AniNeedApprovalException");
    }
}
