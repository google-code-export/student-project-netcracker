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
 *
 * @author akush_000
 */
class EnterWindow extends Window{
    private LoginForm loginForm = null;
    static public final int MODE_ADMIN = 0;
    static public final int MODE_HR = 1;
    static public final int MODE_STUDENT = 2;
    static public final int MODE_GUEST = -1;
    private int mode = MODE_GUEST;
    
    public EnterWindow() {
        setModal(true);
        setWidth("20%");
        setResizable(false);
        center();
        setCaption("Вход");
        getLoginForm();
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(loginForm);
        layout.setComponentAlignment(loginForm, Alignment.BOTTOM_CENTER);
        addComponent(layout);
    }
    
    public LoginForm getLoginForm() {
        if (loginForm == null) {
            loginForm = new LoginForm();
            loginForm.setWidth("100%");
            loginForm.setHeight("110px");
            loginForm.setLoginButtonCaption("Войти");
            loginForm.setPasswordCaption("Пароль");
            loginForm.setUsernameCaption("E-mail");
        }
        return this.loginForm;
    }
    
    public int getMode() {
        return this.mode;
    }
}
