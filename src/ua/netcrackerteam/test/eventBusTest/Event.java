package ua.netcrackerteam.test.eventBusTest;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 05.02.13
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
public class Event {
    public static Integer EVENT_TYPE_CREATE_PDF = 1; //types of events
    public static Integer EVENT_TYPE_SEND_PDF  = 2;
    public static Integer EVENT_TYPE_DELETE_PDF  = 3;
    public Integer type;

    public Event(Integer type) {
        this.type = type;
    }
}