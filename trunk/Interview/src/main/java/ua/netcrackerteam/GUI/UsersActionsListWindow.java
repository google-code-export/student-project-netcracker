package ua.netcrackerteam.GUI;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import ua.netcrackerteam.controller.UsersData;

/**
 * @author krygin
 */
public class UsersActionsListWindow extends Window {
    AdminUserManagementLayout adminUserManagementLayout;
    private Table table;
    private UsersData usersData = null;
    //private Label current = new Label("Selected: -");
    private String currentUser = "noUser";
    private int height;

    public UsersActionsListWindow(AdminUserManagementLayout adminUserManagementLayout, MainPage mainPage) {
        this.adminUserManagementLayout = adminUserManagementLayout;
        this.setIcon(new ThemeResource("icons/32/monitoring.png"));
        setModal(true);
        setWidth(800);
        setResizable(false);
        center();
        setCaption("Users Actions List");

        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-440;
    }
}
