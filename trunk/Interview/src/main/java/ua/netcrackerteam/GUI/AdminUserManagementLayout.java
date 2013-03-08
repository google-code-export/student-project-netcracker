package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.controller.UsersData;

/**
 * @author krygin
 */
public class AdminUserManagementLayout extends VerticalLayout implements Button.ClickListener {
    private String username;
    private final MainPage mainPage;
    private Accordion accordion;
    private VerticalLayout userManagementLayout;
    private Button deleteUserButton = new Button          ("Delete user");
    private Button addNewUserButton = new Button          ("Add New User");
    private Button refreshDataButton = new Button         ("Refresh Table");
    private Button resetUserPasswordButton = new Button   ("Reset Password");
    private Button resetUserLoginButton = new Button      ("Reset Login");
    private Button banUserButton = new Button             ("Ban user");
    private Button banUsersListButton = new Button        ("Ban list");
    private Table table;
    private UsersData usersData = null;
    //private Label current = new Label("Selected: -");
    private String currentUser = "noUser";
    private AddNewUserWindow addNewUserWindow = null;
    private DeleteUserWindow deleteUserWindow = null;
    private ResetUserPasswordWindow resetUserPasswordWindow = null;
    private ResetUserLoginWindow resetUserLoginWindow = null;
    private BanUserWindow banUserWindow = null;
    private BanUsersListWindow banUsersListWindow = null;
    private int height;

    public AdminUserManagementLayout(String username, MainPage mainPage) {
        this.username = username;
        this.mainPage = mainPage;

        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-310;

        addNewUserButton.setVisible(true);
        deleteUserButton.setVisible(true);
        refreshDataButton.setVisible(true);
        resetUserPasswordButton.setVisible(true);
        resetUserLoginButton.setVisible(true);
        banUserButton.setVisible(true);
        banUsersListButton.setVisible(true);

        refreshDataButton.setIcon(new ThemeResource("icons/32/reload.png"));
        addNewUserButton.setIcon(new ThemeResource("icons/32/add-user.png"));
        deleteUserButton.setIcon(new ThemeResource("icons/32/remove-user.png"));
        resetUserPasswordButton.setIcon(new ThemeResource("icons/32/group_key.png"));
        resetUserLoginButton.setIcon(new ThemeResource("icons/32/change-login.png"));
        banUserButton.setIcon(new ThemeResource("icons/32/ban-user.png"));
        banUsersListButton.setIcon(new ThemeResource("icons/32/ban-list.png"));

        userManagementLayout = new VerticalLayout();
        userManagementLayout.setHeight("100%");
        userManagementLayout.setWidth("100%");
        userManagementLayout.setMargin(true, false, false, false);
        addComponent(userManagementLayout);

        HorizontalSplitPanel splitH = new HorizontalSplitPanel();
        splitH.setStyleName(Runo.SPLITPANEL_REDUCED);
        splitH.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        userManagementLayout.addComponent(splitH);

        Panel sidebar = new Panel("Navigation");
        sidebar.setIcon(new ThemeResource("icons/32/users.png"));
        splitH.setFirstComponent(sidebar);
        VerticalLayout layout = (VerticalLayout) sidebar.getContent();
        layout.setMargin(false);
        sidebar.setHeight(height);
        splitH.setLocked(true);

        VerticalLayout addButtonsLayout = new VerticalLayout();
        addButtonsLayout.addComponent(addNewUserButton);

        VerticalLayout refreshButtonLayout = new VerticalLayout();
        refreshButtonLayout.addComponent(refreshDataButton);

        VerticalLayout deleteButtonLayout = new VerticalLayout();
        deleteButtonLayout.addComponent(deleteUserButton);

        VerticalLayout modifyUsersLayout = new VerticalLayout();
        modifyUsersLayout.addComponent(resetUserPasswordButton);
        modifyUsersLayout.addComponent(resetUserLoginButton);

        VerticalLayout banUserLayout = new VerticalLayout();
        banUserLayout.addComponent(banUserButton);
        banUserLayout.addComponent(banUsersListButton);

        accordion = new Accordion();
        accordion.setSizeFull();
        accordion.addTab(addButtonsLayout, "Add users");
        accordion.addTab(modifyUsersLayout, "Modify Users");
        accordion.addTab(refreshButtonLayout, "Refresh Table");
        accordion.addTab(banUserLayout, "User Bans");
        accordion.addTab(deleteButtonLayout, "Delete User");
        sidebar.setContent(accordion);

        table = new Table();
        table.addContainerProperty("#", Integer.class,  null);
        table.addContainerProperty("User ID", String.class,  null);
        table.addContainerProperty("Login",  String.class,  null);
        table.addContainerProperty("e-Mail",  String.class, null);
        table.addContainerProperty("Status",  String.class, null);
        table.addContainerProperty("User Type",  String.class, null);
        table.setSelectable(true);
        table.setWidth("100%");
        table.setHeight(height);
        table.setImmediate(true);
        try {
            usersData = new UsersData();
            usersData.getUserDataNonBanned();
            int i = 1;
            for (UserList userListIter : usersData.getUsersNonBannedList()){
                Integer itemId = new Integer(i);
                table.addItem(new Object[] {i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                        userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                i++;
            }
            table.addListener(new Table.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        //current.setValue("Selected: " + table.getContainerProperty(table.getValue(), "Login").getValue());
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

        VerticalLayout tableLayout = new VerticalLayout();
        splitH.setSecondComponent(tableLayout);

        tableLayout.setMargin(false);
        //tableLayout.addComponent(current);
        tableLayout.addComponent(table);

        deleteUserButton.addListener(this);
        addNewUserButton.addListener(this);
        refreshDataButton.addListener(this);
        resetUserPasswordButton.addListener(this);
        resetUserLoginButton.addListener(this);
        banUserButton.addListener(this);
        banUsersListButton.addListener(this);
    }

    public void refreshTableData(){
        try {
            table.removeAllItems();
            usersData = new UsersData();
            usersData.getUserDataNonBanned();
            int i = 1;
            for (UserList userListIter : usersData.getUsersNonBannedList()){
                Integer itemId = new Integer(i);
                table.addItem(new Object[] {i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                        userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if (source == deleteUserButton) {
            if (currentUser.equals("noUser")){
                Window.Notification n = new Window.Notification("Юзер не выбран", Window.Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("Пожалуйста выбирите юзера для удаления из списка !");
                n.setPosition(Window.Notification.POSITION_CENTERED);
                getWindow().showNotification(n);
            } else {
                deleteUserWindow = new DeleteUserWindow(this, currentUser);
                getWindow().addWindow(deleteUserWindow);
            }
        } else if (source == addNewUserButton) {
            addNewUserWindow = new AddNewUserWindow(this);
            getWindow().addWindow(addNewUserWindow);
        } else if (source == refreshDataButton) {
            refreshTableData();
        } else if (source == resetUserPasswordButton) {
            if (currentUser.equals("noUser")){
                Window.Notification n = new Window.Notification("Юзер не выбран", Window.Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("Для смены пароля пожалуйста выбирите юзера из списка!");
                n.setPosition(Window.Notification.POSITION_CENTERED);
                getWindow().showNotification(n);
            } else {
                resetUserPasswordWindow = new ResetUserPasswordWindow(this, currentUser);
                getWindow().addWindow(resetUserPasswordWindow);
            }
        } else if (source == resetUserLoginButton) {
            if (currentUser.equals("noUser")){
                Window.Notification n = new Window.Notification("Юзер не выбран", Window.Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("Для смены логина пожалуйста выбирите юзера из списка!");
                n.setPosition(Window.Notification.POSITION_CENTERED);
                getWindow().showNotification(n);
            } else {
                resetUserLoginWindow = new ResetUserLoginWindow(this, currentUser);
                getWindow().addWindow(resetUserLoginWindow);
            }
        }else if (source == banUserButton) {
            if (currentUser.equals("noUser")){
                Window.Notification n = new Window.Notification("Юзер не выбран", Window.Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("Для того, чтобы забанить юзера, пожалйста выбирите его из списка !");
                n.setPosition(Window.Notification.POSITION_CENTERED);
                getWindow().showNotification(n);
            } else {
                banUserWindow = new BanUserWindow(this, currentUser);
                getWindow().addWindow(banUserWindow);
            }
        } else if (source == banUsersListButton) {
            banUsersListWindow = new BanUsersListWindow(this, mainPage);
            getWindow().addWindow(banUsersListWindow);
        }
    }
}
