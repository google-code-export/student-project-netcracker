package ua.netcrackerteam.controller.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 11.08.13
 * Time: 2:44
 * To change this template use File | Settings | File Templates.
 */
public class HRException extends Exception{
    public static final String TRY_DELETE_NULL_INTERVIEW = "Невозможно удалить резервное интервью.";

    public HRException() { super(); }
    public HRException(String message) { super(message); }
    public HRException(String message, Throwable cause) { super(message, cause); }
    public HRException(Throwable cause) { super(cause); }
}
