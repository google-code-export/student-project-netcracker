package ua.netcrackerteam.GUI;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import ua.netcrackerteam.controller.GeneralController;
import ua.netcrackerteam.validation.FormValidator;
import ua.netcrackerteam.validation.MessageUtil;

import java.sql.SQLException;

import static ua.netcrackerteam.validation.SystemMessages.REGISTRATION_SUCCESSFUL;
import static ua.netcrackerteam.validation.SystemMessages.SQL_CONNECTION_ERROR;

/**
 * @author akush_000
 */
class RegistrationWindow extends Window implements FieldEvents.BlurListener {
    private MainPage mainPage;
    private TextField email;
    private TextField username;
    private PasswordField password;
    private PasswordField password2;
    private VerticalLayout layout;
    private Button okBut;
    private FormValidator formValidator;

    public RegistrationWindow(MainPage mainPage) {
        this.mainPage = mainPage;
        formValidator = new FormValidator(this.mainPage);
        setModal(true);
        setWidth("20%");
        setResizable(false);
        center();
        setCaption("Регистрация");
        init();
    }

    public void init(){
        layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);
        username = new TextField("Введите логин: ");
        username.addListener(this);
        username.setMaxLength(25);
        layout.addComponent(username);
        username.setRequired(true);
        username.addValidator(new RegexpValidator("\\w{3,}", "Имя должно быть не короче 3х символов латиницы."));
        try {
            username.addValidator(formValidator.existingNickNameValidator());
        } catch (SQLException e) {
            mainPage.getMainWindow().showNotification(String.valueOf(SQL_CONNECTION_ERROR));
        }
        email = new TextField("Введите email: ");
        email.addListener(this);
        layout.addComponent(email);
        email.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
        email.setRequired(true);
        password = new PasswordField("Введите пароль: ");
        layout.addComponent(password);
        password.addListener(this);
        password.addValidator(new RegexpValidator("\\w{6,}",
                "Пароль должен содержать буквы английского алфавита и/или цифры, и быть не короче 6 символов."));
        password.setRequired(true);
        password2 = new PasswordField("Введите пароль еще раз: ");
        layout.addComponent(password2);
        password2.setRequired(true);
        password2.addListener(this);
        password2.addValidator(new AbstractValidator("Пароли должны совпадать.") {
            public boolean isValid(Object value) {
                return password.getValue().equals(password2.getValue());
            }
        });
        okBut = new Button("Регистрация");
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                buttonClickRegistr();
            }
        });
        layout.setComponentAlignment(username, Alignment.TOP_CENTER);
        layout.setComponentAlignment(email, Alignment.TOP_CENTER);
        layout.setComponentAlignment(password, Alignment.TOP_CENTER);
        layout.setComponentAlignment(password2, Alignment.TOP_CENTER);
        layout.addComponent(okBut);
        layout.setComponentAlignment(okBut, Alignment.TOP_CENTER);
    }

    public void buttonClickRegistr() {
        if(formValidator.getErrorLable() != null) {
            removeComponent(formValidator.getErrorLable());
        }
        for(int i = 0; i < layout.getComponentCount(); i ++){
            if(layout.getComponent(i) instanceof AbstractField){
                formValidator.validateTextFields((AbstractField) layout.getComponent(i), layout);
            }
        }
        if (isValid()) {
            try{
                GeneralController.setUsualUser(username.toString(), password.toString(), email.toString());
                close();
                mainPage.getMainWindow().showNotification(MessageUtil.compositeNotification(REGISTRATION_SUCCESSFUL.getNotification(), username));
            } catch (SQLException e){
                mainPage.getMainWindow().showNotification(SQL_CONNECTION_ERROR.getNotification());
            }
//            try {
//                SendMails.sendMailToUserAfterReg(userEmail, userName, userPassword);
//            } catch (EmailException e) {
//                e.printStackTrace();
//            }

        }
    }

    private boolean isValid() {
        if (email.isValid() && password.isValid() && password2.isValid() && username.isValid()) {
            return true;
        }
        return false;
    }

    public void blur(BlurEvent event) {
        //TODO
    }
}
