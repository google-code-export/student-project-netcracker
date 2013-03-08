/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import ua.netcrackerteam.controller.GeneralController;

/**
 * Login form
 * @author Anna Kushnirenko
 */
class EnterWindow extends Window {
    private LoginForm loginForm = null;
    public static final int MODE_GUEST = -1;
    private int mode = MODE_GUEST;

    public EnterWindow(final MainPage mainPage) {
        setModal(true);
        setWidth("20%");
        setResizable(false);
        center();
        setCaption("Вход");
        loginForm = new LoginForm();
        loginForm.setWidth("100%");
        loginForm.setHeight("110px");
        loginForm.setLoginButtonCaption("Войти");
        loginForm.setPasswordCaption("Пароль");
        loginForm.setUsernameCaption("Логин");
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(loginForm);
        layout.setComponentAlignment(loginForm, Alignment.BOTTOM_CENTER);
        addComponent(layout);
        loginForm.addListener(new LoginForm.LoginListener() {
            public void onLogin(LoginForm.LoginEvent event) {
                if (GeneralController.checkUserBan(event.getLoginParameter("username"))){
                    getWindow().showNotification("Вы забанены ! Уважаемый, " + event.getLoginParameter("username") + ", Вы были забанены. \n" +
                            "По данному вопросу обращайтесь к Администратору.", Notification.TYPE_TRAY_NOTIFICATION);
                } else {
                    mode = GeneralController.checkLogin(event.getLoginParameter("username"), event.getLoginParameter("password"));
                    mainPage.changeMode(mode, event.getLoginParameter("username"));
                    loginForm.removeListener(this);
                    EnterWindow.this.close();
                }

            }
        });
    }
}
