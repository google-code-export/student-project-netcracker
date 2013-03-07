package ua.netcrackerteam.GUI;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import ua.netcrackerteam.controller.GeneralController;

/**
 * @author krygin
 */
public class AddNewHRUserWindow extends Window implements FieldEvents.BlurListener{
    AdminUserManagement adminUserManagement;
    TextField userName;
    PasswordField password;
    PasswordField password2;
    TextField userEmail;

    public AddNewHRUserWindow(AdminUserManagement adminUserManagement) {
        this.adminUserManagement = adminUserManagement;
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("New HR User");
        VerticalLayout layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        userName = new TextField("Введите логин: ");
        userName.setRequired(true);
        userName.addListener(this);
        userName.setMaxLength(25);
        layout.addComponent(userName);

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

        userEmail = new TextField("Введите e-mail: ");
        userEmail.setRequired(true);
        userEmail.addListener(this);
        userEmail.setMaxLength(25);
        layout.addComponent(userEmail);

        userName.addValidator(new RegexpValidator("\\w{3,}", "Имя должно быть не короче 3х символов латиницы."));
        userEmail.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
        password.addValidator(new RegexpValidator("\\w{6,}", "Пароль должен содержать буквы английского алфавита и/или цифры, и быть не короче 6 символов."));
        password2.addValidator(new AbstractValidator("Пароли должны совпадать.") {
            public boolean isValid(Object value) {
                return password.getValue().equals(password2.getValue());
            }
        });

        Button okBut = new Button("add admin user");
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
            String userName = String.valueOf(this.getUserName());
            String userPassword = String.valueOf(this.getPassword());
            String userEmail = String.valueOf(this.getUserEmail());
            if (!(GeneralController.checkUserName(userName))){
                GeneralController.setHRUser(userName, userPassword, userEmail);
                Notification n = new Notification("Регистрация нового HR завершена успешно!", Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("На email нового юзера(HR) выслано письмо с регистрационными данными.\n" +
                        "Теперы юзер(HR) может зайти под своими аккаунт данными.");
                n.setPosition(Notification.POSITION_CENTERED);
                /*try {
                    SendMails.sendMailToUserAfterReg(userEmail, userName, userPassword);
                } catch (EmailException e) {
                    e.printStackTrace();
                }*/
                adminUserManagement.getWindow().showNotification(n);
                adminUserManagement.refreshTableData();
                adminUserManagement.refreshTableData();
                AddNewHRUserWindow.this.close();
            } else {
                getWindow().showNotification("Такой никнейм уже существует, пожалуйста выберите другой !", Notification.TYPE_TRAY_NOTIFICATION);
            }
        }
    }

    public TextField getUserName() {
        return userName;
    }

    public PasswordField getPassword() {
        return password;
    }

    public TextField getUserEmail() {
        return userEmail;
    }

    private boolean isValid() {
        if(userEmail.isValid() && password.isValid() && password2.isValid() && userName.isValid()) {
            return true;
        }
        return false;
    }

    public void blur(FieldEvents.BlurEvent event) {
        Object source = event.getComponent();
        if(source instanceof TextField) {
            TextField tf = (TextField) source;
            tf.isValid();
        } else if(source instanceof PasswordField){
            PasswordField pf = (PasswordField) source;
            pf.isValid();
        }
    }
}

