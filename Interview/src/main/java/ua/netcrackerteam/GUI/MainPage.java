/*
 * MainPage.java
 *
 * Created on 27 Январь 2013 г., 12:53
 */
 
package ua.netcrackerteam.GUI;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.Runo;
import ua.netcrackerteam.controller.GeneralController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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
    private Panel layoutfull = null;
    private RegistrationWindow regWindow = null;
    private String userName;

    @Override
    public void init() {
	    buildMainLayout();
    }
    /**
     * Build components of GUI
     */
    private void buildMainLayout() {
        setTheme("netcracker");
        layoutfull = new Panel();
        VerticalLayout vl = (VerticalLayout) layoutfull.getContent();
        vl.setStyleName("body");
        vl.setMargin(false);
        layoutfull.setSizeFull();
        layoutfull.setStyleName(Runo.PANEL_LIGHT);
        setMainWindow(new Window("Учебный Центр NetCracker"));
        enter = new Button("Вход");
        registr = new Button("Регистрация");
        exit = new Button("Выход");
        enter.addListener(this);
        registr.addListener(this);
        exit.addListener(this);
        hlayoutGuest = new HeaderLayout(enter, registr);
        panel = new MainPanel(hlayoutGuest,this);
        layoutfull.addComponent(panel);
        vl.setComponentAlignment(panel, Alignment.TOP_CENTER);
        getMainWindow().setContent(layoutfull);
    }

    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if(source == enter) {
            enterWindow = new EnterWindow(this);
            getMainWindow().addWindow(enterWindow);
        } else if (source == exit) {
            getMainWindow().getApplication().close();
            GeneralController.setAuditInterviews(3, "User logoff from application", getUserName(), new Date());
        } else if (source == registr) {
            if (regWindow == null) {
                registr.removeListener(this);
                regWindow = new RegistrationWindow(this);
            }
            getMainWindow().addWindow(regWindow);
            regWindow.addListener(new Window.CloseListener() {
                public void windowClose(CloseEvent e) {
                    addRegListener();
                }
            });
        }
    }
    
    private void addRegListener() {
        registr.addListener(this);
    }
    

    /**
     * Temporary method
     * Show greeting pop-up window and call method to change interface 
     * according the user role
     */
    public void changeMode(int mode, String username) {
        userName = username;
        switch(mode) {
            case 1:getMainWindow().showNotification("Добро пожаловать, "+userName,Notification.TYPE_TRAY_NOTIFICATION); changeModeAdmin(userName); break;
            case 2:getMainWindow().showNotification("Добро пожаловать, "+userName,Notification.TYPE_TRAY_NOTIFICATION); changeModeHR(userName); break;
            case 3:getMainWindow().showNotification("Добро пожаловать, "+userName,Notification.TYPE_TRAY_NOTIFICATION); changeModeInterviewer(userName); break;
            case 4:getMainWindow().showNotification("Добро пожаловать, "+userName,Notification.TYPE_TRAY_NOTIFICATION); changeModeStudent(userName); break;
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
        panel = new MainPanelAdmin(hlayoutUser,this);
        layoutfull.replaceComponent(oldPanel, panel);
        VerticalLayout vl = (VerticalLayout) layoutfull.getContent();
        vl.setComponentAlignment(panel, Alignment.TOP_CENTER);
    }

    private void changeModeHR(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        MainPanel oldPanel = panel;
        panel = new MainPanelHR(hlayoutUser,this);
        layoutfull.replaceComponent(oldPanel, panel);
        VerticalLayout vl = (VerticalLayout) layoutfull.getContent();
        vl.setComponentAlignment(panel, Alignment.TOP_CENTER);
    }

    private void changeModeStudent(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        MainPanel oldPanel = panel;
        panel = new MainPanelStudent(hlayoutUser,this);
        layoutfull.replaceComponent(oldPanel, panel);
        VerticalLayout vl = (VerticalLayout) layoutfull.getContent();
        vl.setComponentAlignment(panel, Alignment.TOP_CENTER);
    }
    
    private void changeModeInterviewer(String username) {
        hlayoutUser = new HeaderLayout(exit, username);
        MainPanel oldPanel = panel;
        panel = new MainPanelInterviewer(hlayoutUser,this);
        layoutfull.replaceComponent(oldPanel, panel);
        VerticalLayout vl = (VerticalLayout) layoutfull.getContent();
        vl.setComponentAlignment(panel, Alignment.TOP_CENTER);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

 
    /*TEST VAADIN SESSION*/

    public static MainPage getInstance() {
        return threadLocal.get();
    }

    // Set the current applicationForm instance
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
