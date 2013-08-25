package ua.netcrackerteam.validation;

import com.vaadin.ui.Window.Notification;

/**
 * @author AlexK
 * @version 1.0.0
 */
public enum SystemMessages {
    SQL_CONNECTION_ERROR("Ошибка", Notification.TYPE_TRAY_NOTIFICATION,
            "Возникли проблемы вовремя подключения к базе данных"),

    RUNTIME_ERROR("Ошибка выполнения", Notification.TYPE_TRAY_NOTIFICATION,
            "Пожалуйста перезагрузите страницу"),

    LOGIN_ERROR("Логин и/или пароль не верны!", Notification.TYPE_TRAY_NOTIFICATION),

    PASSWORD_ERROR("Неверный текущий пароль !", Notification.TYPE_TRAY_NOTIFICATION),

    REGISTRATION_SUCCESSFUL("Регистрация пользователя {0} завершена успешно!",  Notification.TYPE_TRAY_NOTIFICATION,
            "На ваш email выслано письмо с регистрационными данными. Теперы Вы можете зайти под своим логином.", Notification.POSITION_CENTERED),

    CHANGE_PASSWORD_SUCCESSFUL("Изменение пароля юзера завершено успешно!",  Notification.TYPE_TRAY_NOTIFICATION,
            "На email юзера {0} выслано письмо с новым паролем. Теперь юзер может зайти под своими новыми аккаунт данными.", Notification.POSITION_CENTERED);

    private String caption;
    private String description;
    private int type;
    private Notification notification;

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }

    public Notification getNotification() {
        return notification;
    }

    private SystemMessages(String caption, int type){
        this.caption = caption;
        this.type = type;
        notification = new Notification(caption, type);
        notification.setDescription(description);
    }

    private SystemMessages(String caption, int type, String description){
        this.caption = caption;
        this.type = type;
        this.description = description;
        notification = new Notification(caption, type);
        notification.setDescription(description);
    }

    private SystemMessages(String caption, int type, String description, int position){
        this.caption = caption;
        this.type = type;
        this.description = description;
        notification = new Notification(caption, type);
        notification.setDescription(description);
        notification.setPosition(position);
    }
}
