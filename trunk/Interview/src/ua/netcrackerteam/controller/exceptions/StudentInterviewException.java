package ua.netcrackerteam.controller.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Miralissa
 * Date: 10.08.13
 * Time: 22:14
 * To change this template use File | Settings | File Templates.
 */
public class StudentInterviewException extends Exception{
    public static final String NO_INTERVIEW_EXCEPTION = "Это собеседование было удалено.";
    public static final String NO_FORM_EXCEPTION = "Перед тем, как записаться на интервью, необходимо заполнить анкету!";
    public static final String FULL_INTERVIEW_EXCEPTION = "К сожалению все места на это собеседование уже заняты! Выберите, пожалуйста, другое время.";

    public StudentInterviewException() { super(); }
    public StudentInterviewException(String message) { super(message); }
    public StudentInterviewException(String message, Throwable cause) { super(message, cause); }
    public StudentInterviewException(Throwable cause) { super(cause); }
}
