package ua.netcrackerteam.configuration;

import org.apache.log4j.*;

/**
 * @author krygin
 */
public class InterviewLogger {
    private static InterviewLogger instance = null;
    protected static Logger log = Logger.getLogger(InterviewLogger.class);
    private static final String infoMsg = "has successfully executed without errors";
    private static final String warningMsg = "has generate warning";
    private static final String errorMsg = "error occurred";

    private InterviewLogger() {}

    public static synchronized InterviewLogger getInstance() {
        try {
            if(instance  == null){
                instance  = new InterviewLogger();
                BasicConfigurator.configure();
                log.setLevel(Level.ALL);
            }
        } catch (Exception e) {
            log.error("Something failed", e);
        }
        return instance;
    }

    public static void info(String usedClass, String usedMethod) {
        log.info("Class: [" + usedClass + "], Method: [" + usedMethod + "] - " + infoMsg);
    }

    public void error(String usedClass, String usedMethod, Exception ce) {
        log.error("Class: [" + usedClass + "], Method: [" + usedMethod + "] - " + warningMsg, ce);
    }

    public void warning(String usedClass, String usedMethod) {
        log.warn("IN Class: [" + usedClass + "], Method: [" + usedMethod + "] - " + errorMsg);
    }
}
