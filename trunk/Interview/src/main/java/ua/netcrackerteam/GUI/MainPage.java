/*
 * MainPage.java
 *
 * Created on 27 Январь 2013 г., 12:53
 */
 
package ua.netcrackerteam.GUI;           

import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.BaseTheme;
import java.io.IOException;
/** 
 *
 * @author akush_000
 * @version 
 */

public class MainPage extends Application implements Button.ClickListener{

    private MainPanel panel;
    private Button registr;
    private Button enter;
    private HeaderLayout hlayout;
    private int mode = -1;
    
    @Override
    public void init() {
	buildMainLayout();
    }

    private void buildMainLayout() {
        setTheme("netcracker");
        VerticalLayout layoutfull = new VerticalLayout();
        layoutfull.setSizeFull();
        layoutfull.setStyleName("body");
        setMainWindow(new Window("Учебный Центр NetCracker"));
        enter = new Button("Вход");
        registr = new Button("Регистрация");
        enter.setStyleName(BaseTheme.BUTTON_LINK);
        registr.setStyleName(BaseTheme.BUTTON_LINK);
        enter.addListener(this);
        registr.addListener(this);
        hlayout = new HeaderLayout(enter, registr);
        try {
            panel = new MainPanel(hlayout);
        } catch (IOException ex) {
            getMainWindow().showNotification(
                        "Ошибка чтения файла!",
                        Notification.TYPE_ERROR_MESSAGE);
        }
        layoutfull.addComponent(panel);
        getMainWindow().setContent(layoutfull);
    }

    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if(source == enter) {
            if (mode == EnterWindow.MODE_GUEST) {
            final EnterWindow ew = new EnterWindow();
            getMainWindow().addWindow(ew);
            LoginForm loginForm = ew.getLoginForm();
            loginForm.addListener(new LoginForm.LoginListener() {
            public void onLogin(LoginForm.LoginEvent event) {
                mode = checkLogin(event.getLoginParameter("username"),event.getLoginParameter("password"));
                getMainWindow().removeWindow(ew);
                changeMode(event.getLoginParameter("username"));
            }

            private int checkLogin(String login, String pass) {
                if(login.equals(Integer.toString(EnterWindow.MODE_HR)) && pass.equals(Integer.toString(EnterWindow.MODE_HR))) {
                    return EnterWindow.MODE_HR;
                }
                else if(login.equals(Integer.toString(EnterWindow.MODE_ADMIN)) && pass.equals(Integer.toString(EnterWindow.MODE_ADMIN))) {
                    return EnterWindow.MODE_ADMIN;
                }
                else if(login.equals(Integer.toString(EnterWindow.MODE_STUDENT)) && pass.equals(Integer.toString(EnterWindow.MODE_STUDENT))) {
                    return EnterWindow.MODE_STUDENT;
                }
                return EnterWindow.MODE_GUEST;
                }
            });
            } else {
                mode = EnterWindow.MODE_GUEST;
                changeModeGuest();
            }
        }
    }

    private void changeMode(String username) {
        switch(mode) {
            case 0:getMainWindow().showNotification("Добро пожаловать, Админ!",Notification.TYPE_TRAY_NOTIFICATION); changeModeAdmin(); break;
            case 1:getMainWindow().showNotification("Добро пожаловать, HR!",Notification.TYPE_TRAY_NOTIFICATION); changeModeHR(); break;
            case 2:getMainWindow().showNotification("Добро пожаловать, Студент!",Notification.TYPE_TRAY_NOTIFICATION);changeModeStudent(); break;
            default: getMainWindow().showNotification("Логин и/или пароль не верны!",Notification.TYPE_TRAY_NOTIFICATION); break;
        }
    }

    private void changeModeAdmin() {
        panel.editButActive(true);
        enter.setCaption("Выход");
        registr.setVisible(false);
    }

    private void changeModeHR() {
        registr.setVisible(false);
        enter.setCaption("Выход");
        panel.editButActive(true);
    }

    private void changeModeStudent() {
        panel.editButActive(false);
        enter.setCaption("Выход");
        registr.setVisible(false);
    }

    private void changeModeGuest() {
        registr.setVisible(true);
        enter.setCaption("Вход");
        panel.editButActive(false);
    }
}
