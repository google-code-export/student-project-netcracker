package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import ua.netcrackerteam.DAO.AuditInterview;
import ua.netcrackerteam.controller.UsersData;

import java.util.Date;

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

        table = new Table();
        table.addContainerProperty("#", Integer.class, null);
        table.addContainerProperty("Audit ID", String.class, null);
        table.addContainerProperty("Login", String.class, null);
        table.addContainerProperty("User Category", String.class, null);
        table.addContainerProperty("Action", String.class, null);
        table.addContainerProperty("Action Description", String.class, null);
        table.addContainerProperty("Action Time", Date.class, null);
        table.setSelectable(true);
        table.setWidth("100%");
        table.setHeight(height);
        table.setImmediate(true);

        table.setStyleName("iso3166");
        table.setColumnReorderingAllowed(true);
        table.setColumnCollapsingAllowed(true);

        try {
            usersData = new UsersData();
            usersData.setAuditInterviews();
            int i = 1;
            for (AuditInterview userListIter : usersData.getAuditInterviews()){
                Integer itemId = new Integer(i);
                table.addItem(new Object[]{i, userListIter.getIdAudit(), userListIter.getUserName(), userListIter.getUserCategory().getName(),
                        userListIter.getAction().getActionName(), userListIter.getActionDescription(), userListIter.getActionDate()}, itemId);
                i++;
            }
            table.addListener(new Table.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        //current.setValue("Selected: " + tableCommon.getContainerProperty(tableCommon.getValue(), "Login").getValue());
                        currentUser = String.valueOf(table.getContainerProperty(table.getValue(), "Login").getValue());
                    } catch (NullPointerException e) {
                        currentUser = "noUser";
                        //current.setValue("Selected: -");
                    }
                }
            });
        }  catch (Exception e) {
            e.printStackTrace();
        }
        addComponent(table);
    }
}
