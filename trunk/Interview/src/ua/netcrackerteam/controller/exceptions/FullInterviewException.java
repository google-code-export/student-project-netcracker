package ua.netcrackerteam.controller.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Miralissa
 * Date: 06.08.13
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class FullInterviewException extends Exception{
    public static final String FULL_INTERVIEW_EXCEPRION = "К сожалению все места на это собеседование уже заняты! Выберите, пожалуйста, другое время.";

    public FullInterviewException() { super(); }
    public FullInterviewException(String message) { super(message); }
    public FullInterviewException(String message, Throwable cause) { super(message, cause); }
    public FullInterviewException(Throwable cause) { super(cause); }
}
