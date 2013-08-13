package ua.netcrackerteam.GUI;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import ua.netcrackerteam.controller.GeneralController;

import java.util.Date;

/**
 * @author krygin
 */
public class ResetUserPasswordWindow extends Window implements FieldEvents.BlurListener{
    AdminUserManagementLayout adminUserManagementLayout;
    PasswordField password;
    PasswordField password2;
    Label message;
    String currentUser;
    SettingsLayout settingsLayout;

    public ResetUserPasswordWindow(AdminUserManagementLayout adminUserManagement, String currentUser, final String userLogin) {
        this.adminUserManagementLayout = adminUserManagement;
        this.currentUser = currentUser;
        this.setIcon(new ThemeResource("icons/32/group_key.png"));
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("New Password for User");
        VerticalLayout layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        message = new Label("Смена пароля для юзера - " + currentUser);
        layout.addComponent(message);

        password = new PasswordField("Введите новый пароль: ");
        password.setRequired(true);
        password.addListener(this);
        password.setMaxLength(25);
        layout.addComponent(password);

        password2 = new PasswordField("Повторите пароль: ");
        password2.setRequired(true);
        password2.addListener(this);
        password2.setMaxLength(25);
        layout.addComponent(password2);

        layout.setComponentAlignment(password, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(password2, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(message, Alignment.TOP_CENTER);

        password.addValidator(new RegexpValidator("\\w{6,}", "Пароль должен содержать буквы английского алфавита и/или цифры, и быть не короче 6 символов."));
        password2.addValidator(new AbstractValidator("Пароли должны совпадать.") {
            public boolean isValid(Object value) {
                return password.getValue().equals(password2.getValue());
            }
        });

        Button okBut = new Button("reset user password");
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                buttonClickRegistrFromAdmin(userLogin);
            }
        });
        layout.addComponent(okBut);
        layout.setComponentAlignment(okBut, Alignment.TOP_CENTER);
    }

    public ResetUserPasswordWindow(SettingsLayout settingsLayout, String currentUser) {
        this.settingsLayout = settingsLayout;
        this.currentUser = currentUser;
        this.setIcon(new ThemeResource("icons/32/group_key.png"));
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("New Password for User");
        VerticalLayout layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        message = new Label("Смена пароля для юзера - " + currentUser);
        layout.addComponent(message);

        password = new PasswordField("Введите новый пароль: ");
        password.setRequired(true);
        password.addListener(this);
        password.setMaxLength(25);
        layout.addComponent(password);

        password2 = new PasswordField("Повторите пароль: ");
        password2.setRequired(true);
        password2.addListener(this);
        password2.setMaxLength(25);
        layout.addComponent(password2);

        layout.setComponentAlignment(password, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(password2, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(message, Alignment.TOP_CENTER);

        password.addValidator(new RegexpValidator("\\w{6,}", "Пароль должен содержать буквы английского алфавита и/или цифры, и быть не короче 6 символов."));
        password2.addValidator(new AbstractValidator("Пароли должны совпадать.") {
            public boolean isValid(Object value) {
                return password.getValue().equals(password2.getValue());
            }
        });

        Button okBut = new Button("reset user password");
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                buttonClickRegistr();
            }
        });
        layout.addComponent(okBut);
        layout.setComponentAlignment(okBut, Alignment.TOP_CENTER);
    }

    public void buttonClickRegistr() {
        if (isValid()) {
            String userName = currentUser;
            String userPassword = String.valueOf(this.getPassword());
            GeneralController.setNewPassword(userName, userPassword);
            GeneralController.setAuditInterviews(6, "Password changed for user - " + currentUser, currentUser, new Date());
            Notification n = new Notification("Изменение пароля юзера завершено успешно!", Notification.TYPE_TRAY_NOTIFICATION);
            n.setDescription("На email юзера" + userName + " выслано письмо с новым паролем.\n" +
                    "Теперь юзер может зайти под своими новыми аккаунт данными.");
            n.setPosition(Notification.POSITION_CENTERED);
            adminUserManagementLayout.getWindow().showNotification(n);
            ResetUserPasswordWindow.this.close();
            /*try {
                SendMails.sendMailToUserAfterReg(userEmail, userName, userPassword);
            } catch (EmailException e) {
                e.printStackTrace();
            }*/
        }
    }

    public void buttonClickRegistrFromAdmin(String userLogin) {
        if (isValid()) {
            String userName = currentUser;
            String userPassword = String.valueOf(this.getPassword());
            GeneralController.setNewPassword(userName, userPassword);
            GeneralController.setAuditInterviews(6, "Password changed for user - " + currentUser, userLogin, new Date());
            Notification n = new Notification("Изменение пароля юзера завершено успешно!", Notification.TYPE_TRAY_NOTIFICATION);
            n.setDescription("На email юзера" + userName + " выслано письмо с новым паролем.\n" +
                    "Теперь юзер может зайти под своими новыми аккаунт данными.");
            n.setPosition(Notification.POSITION_CENTERED);
            adminUserManagementLayout.getWindow().showNotification(n);
            ResetUserPasswordWindow.this.close();
            /*try {
                SendMails.sendMailToUserAfterReg(userEmail, userName, userPassword);
            } catch (EmailException e) {
                e.printStackTrace();
            }*/
        }
    }

    public PasswordField getPassword() {
        return password2;
    }

    private boolean isValid() {
        if(password.isValid() && password2.isValid()) {
            return true;
        }
        return false;
    }

    public void blur(FieldEvents.BlurEvent event) {
        Object source = event.getComponent();
        if(source instanceof PasswordField){
            PasswordField pf = (PasswordField) source;
            pf.isValid();
        }
    }
}
