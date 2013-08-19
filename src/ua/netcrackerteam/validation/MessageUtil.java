package ua.netcrackerteam.validation;

import com.vaadin.ui.Window.Notification;

import java.text.MessageFormat;

/**
 * @author AlexK
 * @version 1.0.0
 */
public class MessageUtil {

    public static Notification compositeNotification(Notification notification, Object ... arguments){
       String fullCaption = MessageFormat.format(notification.getCaption(), arguments);
       notification.setCaption(fullCaption);
       return notification;
    }
}
