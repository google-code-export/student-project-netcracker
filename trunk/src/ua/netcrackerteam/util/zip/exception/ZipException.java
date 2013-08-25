package ua.netcrackerteam.util.zip.exception;

/**
 * @author unconsionable
 */
public class ZipException extends Exception{

    public ZipException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZipException(Throwable cause) {
        super(cause);
    }

    public ZipException(String message) {
        super(message);
    }

    public ZipException() {
    }
}