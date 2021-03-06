package com.ani.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * User: yeqingzhou
 * Date: 12-9-28
 */
public class AniBaseException extends Exception {

    private static final long serialVersionUID = -5854217260226084617L;

    protected transient Logger logger = Logger.getLogger("AniException");

    public AniBaseException() {
        super();
    }

    public AniBaseException(String msg, String logInfo) {
        super(msg);
        this.logger = Logger.getLogger(logInfo);
        traceException();
    }

    private void traceException() {
        printStackTrace();
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

}
