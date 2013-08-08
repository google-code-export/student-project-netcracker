package ua.netcrackerteam.controller.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Miralissa
 * Date: 08.08.13
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public class NoFormException extends Exception {
    public static final String NO_FORM_EXCEPRION = "Перед тем, как записаться на интервью, необходимо заполнить анкету!";
    public NoFormException() { super(); }
    public NoFormException(String message) { super(message); }
    public NoFormException(String message, Throwable cause) { super(message, cause); }
    public NoFormException(Throwable cause) { super(cause); }
}
