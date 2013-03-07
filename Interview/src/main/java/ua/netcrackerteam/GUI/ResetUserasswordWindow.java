package ua.netcrackerteam.GUI;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import ua.netcrackerteam.controller.GeneralController;

/**
 * @author krygin
 */
public class ResetUserasswordWindow extends Window implements FieldEvents.BlurListener{
    TestLayout adminUserManagement;
    PasswordField password;
    PasswordField password2;

    String currentUser = null;

    public ResetUserasswordWindow(TestLayout testLayout, String currentUser) {
        this.adminUserManagement = testLayout;
        this.currentUser = currentUser;
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("New Admin User");
        VerticalLayout layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        password = new PasswordField("Введите пароль: ");
        password.setRequired(true);
        password.addListener(this);
        password.setMaxLength(25);
        layout.addComponent(password);

        password2 = new PasswordField("Повторите пароль: ");
        password2.setRequired(true);
        password2.addListener(this);
        password2.setMaxLength(25);
        layout.addComponent(password2);

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
            Notification n = new Notification("Изменение пароля юзера завершено успешно!", Notification.TYPE_TRAY_NOTIFICATION);
            n.setDescription("На email " + userName + " выслано письмо с новым паролем.\n" +
                    "Теперы юзер может зайти под своими новыми аккаунт данными.");
            n.setPosition(Notification.POSITION_CENTERED);
            adminUserManagement.getWindow().showNotification(n);
            adminUserManagement.refreshTableData();
            adminUserManagement.refreshTableData();
            ResetUserasswordWindow.this.close();
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
