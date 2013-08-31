/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import ua.netcrackerteam.configuration.Logable;
import ua.netcrackerteam.controller.GeneralController;
import ua.netcrackerteam.validation.FormValidator;

import java.sql.SQLException;

import static ua.netcrackerteam.validation.SystemMessages.LOGIN_ERROR;
import static ua.netcrackerteam.validation.SystemMessages.RUNTIME_ERROR;
import static ua.netcrackerteam.validation.SystemMessages.SQL_CONNECTION_ERROR;

/**
 * Login form
 * @author Anna Kushnirenko
 */
@SuppressWarnings("serial")
class EnterWindow extends Window implements Logable, FieldEvents.BlurListener {
    private MainPage mainPage;
    public static final int MODE_GUEST = -1;
    private int mode = MODE_GUEST;
    private TextField username;
    private PasswordField password;
    private Button okBut;
    private final VerticalLayout layout;
    private FormValidator formValidator;

    public EnterWindow(final MainPage mainPage) {
        this.mainPage = mainPage;
        formValidator = new FormValidator(this.mainPage);
        layout = new VerticalLayout();
        setModal(true);
        setWidth(300,UNITS_PIXELS);
        setResizable(false);
        center();
        setCaption("Вход");
        init();
        username.focus();
    }

    private void init() {
        username = new TextField("Введите логин: ");
        username.setRequired(true);
        username.addValidator(new RegexpValidator("\\w{3,}", "Имя должно быть не короче 3х символов."));
        password = new PasswordField("Введите пароль: ");
        password.addValidator(new RegexpValidator("\\w{6,}",
                "Пароль должен содержать буквы английского алфавита и/или цифры, и быть не короче 6 символов."));
        password.setRequired(true);
        okBut = new Button("Войти");
        username.addListener(this);
        password.addListener(this);
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                buttonClickEnter();
            }
        });

        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(username);
        layout.addComponent(password);
        layout.addComponent(okBut);
        layout.setComponentAlignment(username, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(password, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(okBut, Alignment.BOTTOM_CENTER);
        addComponent(layout);
    }

    private void buttonClickEnter() {
        for(int i = 0; i < layout.getComponentCount(); i ++){
            if(layout.getComponent(i) instanceof AbstractField){
                formValidator.validateTextFields((AbstractField) layout.getComponent(i), layout);
            }
        }
        if (isValid()) {
            try {
                if(GeneralController.checkUsersAvailability((String)username.getValue(), (String)password.getValue())){
                    if(GeneralController.checkUserBan((String)username.getValue())){
                        getWindow().showNotification(LOGIN_ERROR.getNotification());
                        getWindow().showNotification("Вы забанены ! Уважаемый, " + username + ", Вы были забанены. \n" +
                                "По данному вопросу обращайтесь к Администратору.", Notification.TYPE_TRAY_NOTIFICATION);
                    } else {
                        mode = GeneralController.checkLogin((String)username.getValue(), (String)password.getValue());
                        mainPage.changeMode(mode, (String)username.getValue());
                        EnterWindow.this.close();
                    }
                } else {
                    getWindow().showNotification(LOGIN_ERROR.getNotification());
                    username.setValue("");
                    password.setValue("");
                }
            } catch(SQLException e){
                mainPage.getMainWindow().showNotification(SQL_CONNECTION_ERROR.getNotification());
                return;
            } catch(RuntimeException e){
                mainPage.getMainWindow().showNotification(RUNTIME_ERROR.getNotification());
                e.printStackTrace();
                mainPage.getMainWindow().executeJavaScript("window.location.reload();");
                return;
            }
        }

    }

    private boolean isValid() {
        if (password.isValid() && username.isValid()) {
            return true;
        }
        return false;
    }

    @Override
    public void blur(FieldEvents.BlurEvent event) {
    }
}
