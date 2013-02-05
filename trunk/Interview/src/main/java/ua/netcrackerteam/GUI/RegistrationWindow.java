/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.apache.commons.mail.EmailException;
import ua.netcrackerteam.controller.GeneralController;
import ua.netcrackerteam.controller.SendMails;

/**
 *
 * @author akush_000
 */
class RegistrationWindow extends Window implements Button.ClickListener{
    CaptchaField captchaField;
    TextField captchaInput;
    MainPage mainPage;
    TextField email;
    TextField username;
    PasswordField password;
    PasswordField password2;

    public TextField getEmail() {
        return email;
    }

    public TextField getUsername() {
        return username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public RegistrationWindow(MainPage mainPage) {
        this.mainPage = mainPage;
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("Регистрация");
        VerticalLayout layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);
        username = new TextField("Введите имя: ");
        username.setRequired(true);
        username.setMaxLength(25);
        layout.addComponent(username);
        username.addValidator(new RegexpValidator("\\w{3,}", "Имя должно быть не короче 3х символов."));
        email = new TextField("Введите email: ");
        layout.addComponent(email);
        email.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
        email.setRequired(true);
        password = new PasswordField("Введите пароль: ");
        layout.addComponent(password);
        password.addValidator(new RegexpValidator("\\w{6,}",
                        "Пароль должен содержать минимум 6 символов."));
        password.setRequired(true);
        password2 = new PasswordField("Введите пароль еще раз: ");
        layout.addComponent(password2);
        password2.setRequired(true);
        password2.addValidator(new AbstractValidator("Пароли должны совпадать.") {
            public boolean isValid(Object value) {
                return password.getValue().equals(password2.getValue());
            }
        });
        captchaField = new CaptchaField(this.mainPage);
        captchaField.setHeight("150");
        layout.addComponent(captchaField);
        captchaInput = new TextField("Введите текст, изображенный на картинке: ");
        layout.addComponent(captchaInput);
        captchaInput.setRequired(true);
        captchaInput.addValidator(new AbstractValidator("Текст введен неверно.") {
            public boolean isValid(Object value) {
                return captchaField.validateCaptcha((String)captchaInput.getValue());
            }
        });
        Button okBut = new Button("Регистрация");
        okBut.addListener(this);
        layout.addComponent(okBut);
        layout.setComponentAlignment(okBut, Alignment.TOP_CENTER);
    }

    public void buttonClick(ClickEvent event) {
        if (isValid()) {
            String userName = String.valueOf(this.getUsername());
            String userPassword = String.valueOf(this.getPassword());
            String userEmail = String.valueOf(this.getEmail());
            GeneralController.setUsualUser(userName, userPassword, userEmail);
            Notification n = new Notification("Регистрация завершена успешно!", Notification.TYPE_TRAY_NOTIFICATION);
            n.setDescription("На ваш email выслано письмо с регистрационными данными.\n" +
                    "Теперы Вы можете зайти под своим логином.");
            n.setPosition(Notification.POSITION_CENTERED);
            try {
                SendMails.sendMailToUserAfterReg(userEmail, userName, userPassword);
            } catch (EmailException e) {
                e.printStackTrace();
            }
            mainPage.getMainWindow().showNotification(n);
            RegistrationWindow.this.close();
        }
    }
    
    private boolean isValid() {
        if(email.isValid() && password.isValid() && password2.isValid() && captchaInput.isValid() && username.isValid()) {
            return true;
        }
        captchaField.validateCaptcha("");
        return false;
    }
}
