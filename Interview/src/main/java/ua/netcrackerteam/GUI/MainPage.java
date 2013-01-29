/*
 * MainPage.java
 *
 * Created on 27 Январь 2013 г., 12:53
 */
 
package ua.netcrackerteam.GUI;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GUI start class
 * @author Anna Kushnirenko
 * @version 
 */

public class MainPage extends Application implements Button.ClickListener, HttpServletRequestListener {
    private static ThreadLocal<MainPage> threadLocal = new ThreadLocal<MainPage>();

    private MainPanel panel = null;
    private Button registr = null;
    private Button enter = null;
    private Button exit = null;
    private HeaderLayout hlayoutGuest = null;
    private HeaderLayout hlayoutUser = null;
    private EnterWindow enterWindow = null;
    private MainPanelAdmin panelAdmin = null;
    private MainPanelStudent panelStudent = null;
    private MainPanelHR panelHR = null;
    private VerticalLayout layoutfull = null;

    @Override
    public void init() {
	buildMainLayout();
    }
    /**
     * Build components of GUI
     */
    private void buildMainLayout() {
        setTheme("netcracker");
        layoutfull = new VerticalLayout();
        layoutfull.setSizeFull();
        layoutfull.setStyleName("body");
        setMainWindow(new Window("Учебный Центр NetCracker"));
        enter = new Button("Вход");
        registr = new Button("Регистрация");
        enter.addListener(this);
        registr.addListener(this);
        exit = new Button("Выход");
        exit.addListener(this);
        hlayoutGuest = new HeaderLayout(enter, registr);
        panel = new MainPanel(hlayoutGuest);
        layoutfull.addComponent(panel);
        getMainWindow().setContent(layoutfull);
    }

    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if(source == enter) {
            if (enterWindow == null) {
                enterWindow = new EnterWindow(this);
            } 
            getMainWindow().addWindow(enterWindow);
        } else if (source == exit) {
            changeModeGuest();
        }
    }
    /**
     * Temporary method
     * Show greeting pop-up window and call method to change interface 
     * according the user role
     */
    public void changeMode(int mode, String username) {
        switch(mode) {
            case 0:getMainWindow().showNotification("Добро пожаловать, Админ!",Notification.TYPE_TRAY_NOTIFICATION); changeModeAdmin(username); break;
            case 1:getMainWindow().showNotification("Добро пожаловать, HR!",Notification.TYPE_TRAY_NOTIFICATION); changeModeHR(username); break;
            case 2:getMainWindow().showNotification("Добро пожаловать, Студент!",Notification.TYPE_TRAY_NOTIFICATION);changeModeStudent(username); break;
            default: getMainWindow().showNotification("Логин и/или пароль не верны!",Notification.TYPE_TRAY_NOTIFICATION); break;
        }
    }

    private void changeModeAdmin(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        panelAdmin = new MainPanelAdmin(hlayoutUser);
        layoutfull.replaceComponent(panel, panelAdmin);
    }

    private void changeModeHR(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        panelHR = new MainPanelHR(hlayoutUser);
        layoutfull.replaceComponent(panel, panelHR);
    }

    private void changeModeStudent(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        panelStudent = new MainPanelStudent(hlayoutUser);
        layoutfull.replaceComponent(panel, panelStudent);
    }

    private void changeModeGuest() {
        Component c = layoutfull.getComponent(0);
        layoutfull.replaceComponent(c, panel);
    }

    /*TEST VAADIN SESSION*/

    public static MainPage getInstance() {
        return threadLocal.get();
    }

    // Set the current application instance
    public static void setInstance(MainPage application) {
        threadLocal.set(application);
    }

    @Override
    public void onRequestStart(HttpServletRequest request, HttpServletResponse response) {
        MainPage.setInstance(this);
    }

    @Override
    public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {
        threadLocal.remove();
    }
}
