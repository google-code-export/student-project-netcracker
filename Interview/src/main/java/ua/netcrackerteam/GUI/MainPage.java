/*
 * MainPage.java
 *
 * Created on 27 Январь 2013 г., 12:53
 */
 
package ua.netcrackerteam.GUI;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Button;
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
    private VerticalLayout layoutfull = null;
    private RegistrationWindow regWindow = null;

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
        exit = new Button("Выход");
        enter.addListener(this);
        registr.addListener(this);
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
        } else if (source == registr) {
            if (regWindow == null) {
                regWindow = new RegistrationWindow(this);
            }
            getMainWindow().addWindow(regWindow);
        }
    }
    /**
     * Temporary method
     * Show greeting pop-up window and call method to change interface 
     * according the user role
     */
    public void changeMode(int mode, String username) {
        switch(mode) {
            case 0:getMainWindow().showNotification("Добро пожаловать, "+username,Notification.TYPE_TRAY_NOTIFICATION); changeModeAdmin(username); break;
            case 1:getMainWindow().showNotification("Добро пожаловать, "+username,Notification.TYPE_TRAY_NOTIFICATION); changeModeHR(username); break;
            case 2:getMainWindow().showNotification("Добро пожаловать, "+username,Notification.TYPE_TRAY_NOTIFICATION);changeModeStudent(username); break;
            case 3:getMainWindow().showNotification("Добро пожаловать, "+username,Notification.TYPE_TRAY_NOTIFICATION);changeModeInterviewer(username); break;
            default: {
                Notification error = new Notification("Логин и/или пароль не верны!",Notification.TYPE_ERROR_MESSAGE);
                error.setPosition(Notification.POSITION_CENTERED);
                getMainWindow().showNotification(error);
            } break;
        }
    }

    private void changeModeAdmin(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        MainPanel oldPanel = panel;
        panel = new MainPanelAdmin(hlayoutUser);
        layoutfull.replaceComponent(oldPanel, panel);
    }

    private void changeModeHR(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        MainPanel oldPanel = panel;
        panel = new MainPanelHR(hlayoutUser);
        layoutfull.replaceComponent(oldPanel, panel);
    }

    private void changeModeStudent(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        MainPanel oldPanel = panel;
        panel = new MainPanelStudent(hlayoutUser);
        layoutfull.replaceComponent(oldPanel, panel);
    }
    
    private void changeModeInterviewer(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        MainPanel oldPanel = panel;
        panel = new MainPanelInterviewer(hlayoutUser);
        layoutfull.replaceComponent(oldPanel, panel);
    }

    private void changeModeGuest() {
        MainPanel oldPanel = panel;
        panel = new MainPanel(hlayoutGuest);
        layoutfull.replaceComponent(oldPanel, panel);
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
