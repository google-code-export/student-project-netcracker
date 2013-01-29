/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Login form
 * @author Anna Kushnirenko
 */
class EnterWindow extends Window{
    private LoginForm loginForm = null;
    static public final int MODE_ADMIN = 0;
    static public final int MODE_HR = 1;
    static public final int MODE_STUDENT = 2;
    static public final int MODE_GUEST = -1;
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
        loginForm.setUsernameCaption("E-mail");
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(loginForm);
        layout.setComponentAlignment(loginForm, Alignment.BOTTOM_CENTER);
        addComponent(layout);
        loginForm.addListener(new LoginForm.LoginListener() {
            public void onLogin(LoginForm.LoginEvent event) {
                mode = checkLogin(event.getLoginParameter("username"),event.getLoginParameter("password"));
                mainPage.changeMode(mode, event.getLoginParameter("username"));
                EnterWindow.this.close();
            }
            /**
             * Temporary method 
             */
            private int checkLogin(String login, String pass) {
                if(login.equals(Integer.toString(MODE_HR)) && pass.equals(Integer.toString(MODE_HR))) {
                    return MODE_HR;
                }
                else if(login.equals(Integer.toString(MODE_ADMIN)) && pass.equals(Integer.toString(MODE_ADMIN))) {
                    return MODE_ADMIN;
                }
                else if(login.equals(Integer.toString(MODE_STUDENT)) && pass.equals(Integer.toString(MODE_STUDENT))) {
                    return MODE_STUDENT;
                }
                return MODE_GUEST;
                }
            });
    }
}
