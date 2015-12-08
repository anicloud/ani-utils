package com.ani.utils.exception;

import org.springframework.jms.JmsException;

import javax.management.remote.JMXServerErrorException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * User: yeqingzhou
 * Date: 12-9-28
 */
public class AniBaseException extends JmsException {
    /**
     *
     */
    private static final long serialVersionUID = -5854217260226084617L;
    protected Logger logger = Logger.getLogger("AniException");

    public AniBaseException() {
        super("ANI_BASE_EXCEPTION");
        this.logger = Logger.getLogger("AniException");
        traceException();
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
