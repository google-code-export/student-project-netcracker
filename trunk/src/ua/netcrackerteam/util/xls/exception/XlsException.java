package ua.netcrackerteam.util.xls.exception;

/**
 * @author unconsionable
 */
public class XlsException extends Exception{

    public XlsException(String message, Throwable cause) {
        super(message, cause);
    }

    public XlsException(Throwable cause) {
        super(cause);
    }

    public XlsException(String message) {
        super(message);
    }

    public XlsException() {
    }
}