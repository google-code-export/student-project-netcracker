package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.controller.UsersData;

import static com.vaadin.data.Property.ValueChangeEvent;

/**
 * @author krygin
 */
public class AdminUserManagementLayout extends VerticalLayout implements Button.ClickListener, AbstractSelect.NewItemHandler,
        Property.ValueChangeListener, FieldEvents.BlurListener {
    private final String username;
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
    private Button changeUserTypeButton = new Button      ("Change User Type");
    private Button clearSearchButton = new Button         ();
    private Button searchButton = new Button              ("Search");
    private Button userActionsButton = new Button         ("Users Actions");
    private Table tableCommon;
    private UsersData usersData = null;
    private UsersData usersDataFiltered = null;
    //private Label current = new Label("Selected: -");
    private String currentUser = "noUser";
    private AddNewUserWindow addNewUserWindow = null;
    private DeleteUserWindow deleteUserWindow = null;
    private ResetUserPasswordWindow resetUserPasswordWindow = null;
    private ResetUserLoginWindow resetUserLoginWindow = null;
    private BanUserWindow banUserWindow = null;
    private BanUsersListWindow banUsersListWindow = null;
    private ChangeUserTypeWindow changeUserTypeWindow = null;
    private UsersActionsListWindow usersActionsListWindow;
    private int height;
    private int width = 200;
    private ComboBox searchUserCategoryComboBox;
    private static final String[] userTypes = new String[] { "Admin", "HR", "Interviewer", "Student" };
    private String selectedUserType = "";
    private Boolean lastAdded = false;
    private TextField searchField;
    VerticalLayout tableLayout = new VerticalLayout();

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
        changeUserTypeButton.setVisible(true);
        clearSearchButton.setVisible(true);
        searchButton.setVisible(true);
        userActionsButton.setVisible(true);

        refreshDataButton.setIcon(new ThemeResource("icons/32/reload.png"));
        addNewUserButton.setIcon(new ThemeResource("icons/32/add-user.png"));
        deleteUserButton.setIcon(new ThemeResource("icons/32/remove-user.png"));
        resetUserPasswordButton.setIcon(new ThemeResource("icons/32/group_key.png"));
        resetUserLoginButton.setIcon(new ThemeResource("icons/32/change-login.png"));
        banUserButton.setIcon(new ThemeResource("icons/32/ban-user.png"));
        banUsersListButton.setIcon(new ThemeResource("icons/32/ban-list.png"));
        changeUserTypeButton.setIcon(new ThemeResource("icons/32/change-user-type.png"));
        clearSearchButton.setIcon(new ThemeResource("icons/32/clear.png"));
        searchButton.setIcon(new ThemeResource("icons/32/search.png"));
        userActionsButton.setIcon(new ThemeResource("icons/32/monitoring.png"));

        addNewUserButton.setWidth(width);
        deleteUserButton.setWidth(width);
        refreshDataButton.setWidth(width);
        resetUserPasswordButton.setWidth(width);
        resetUserLoginButton.setWidth(width);
        banUserButton.setWidth(width);
        banUsersListButton.setWidth(width);
        changeUserTypeButton.setWidth(width);
        searchButton.setWidth(130);
        userActionsButton.setWidth(width);

        deleteUserButton.addListener((Button.ClickListener)this);
        addNewUserButton.addListener((Button.ClickListener)this);
        refreshDataButton.addListener((Button.ClickListener)this);
        resetUserPasswordButton.addListener((Button.ClickListener)this);
        resetUserLoginButton.addListener((Button.ClickListener)this);
        banUserButton.addListener((Button.ClickListener)this);
        banUsersListButton.addListener((Button.ClickListener)this);
        changeUserTypeButton.addListener((Button.ClickListener)this);
        searchButton.addListener((Button.ClickListener)this);
        clearSearchButton.addListener((Button.ClickListener)this);
        userActionsButton.addListener((Button.ClickListener)this);

        clearSearchButton.setDescription("Click for clear search field");
        deleteUserButton.setDescription("Click for delete user from base");
        addNewUserButton.setDescription("Click for add new user in base");
        refreshDataButton.setDescription("Click for refresh table");
        resetUserPasswordButton.setDescription("Click for change user password");
        resetUserLoginButton.setDescription("Click for change user login");
        banUserButton.setDescription("Click for ban user");
        banUsersListButton.setDescription("Click for open ban list");
        changeUserTypeButton.setDescription("Click for change user type");
        searchButton.setDescription("Click for search in base");
        userActionsButton.setDescription("Click for watch users actions in program");

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

        VerticalLayout userMonitoringLayout = new VerticalLayout();
        userMonitoringLayout.addComponent(userActionsButton);

        VerticalLayout deleteButtonLayout = new VerticalLayout();
        deleteButtonLayout.addComponent(deleteUserButton);

        VerticalLayout modifyUsersLayout = new VerticalLayout();
        modifyUsersLayout.addComponent(resetUserPasswordButton);
        modifyUsersLayout.addComponent(resetUserLoginButton);
        modifyUsersLayout.addComponent(changeUserTypeButton);

        VerticalLayout banUserLayout = new VerticalLayout();
        banUserLayout.addComponent(banUserButton);
        banUserLayout.addComponent(banUsersListButton);

        VerticalLayout searchVerticalLayout = new VerticalLayout();
        searchUserCategoryComboBox = new ComboBox("User category filter for search");
        for (int i = 0; i < userTypes.length; i++) {
            searchUserCategoryComboBox.addItem(userTypes[i]);
        }
        searchUserCategoryComboBox.setNewItemHandler(this);
        searchUserCategoryComboBox.setNewItemsAllowed(false);
        searchUserCategoryComboBox.setImmediate(true);
        searchUserCategoryComboBox.addListener((Property.ValueChangeListener) this);
        searchVerticalLayout.addComponent(searchUserCategoryComboBox);
        HorizontalLayout searchFieldLayout = new HorizontalLayout();
        searchField = new TextField("Input text for search :");
        searchField.setRequired(true);
        searchField.addListener((FieldEvents.BlurListener) this);
        searchVerticalLayout.addComponent(searchField);
        searchFieldLayout.addComponent(searchButton);
        searchFieldLayout.addComponent(clearSearchButton);
        searchVerticalLayout.addComponent(searchFieldLayout);
        searchVerticalLayout.addComponent(refreshDataButton);

        accordion = new Accordion();
        accordion.setSizeFull();
        accordion.addTab(addButtonsLayout, "Add users");
        accordion.addTab(searchVerticalLayout, "Search");
        accordion.addTab(modifyUsersLayout, "Modify Users");
        accordion.addTab(userMonitoringLayout, "Monitoring");
        accordion.addTab(banUserLayout, "User Bans");
        accordion.addTab(deleteButtonLayout, "Delete User");
        accordion.addListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                refreshTableData();
            }
        });
        sidebar.setContent(accordion);

        tableCommon = new Table();
        tableCommon.addContainerProperty("#", Integer.class, null);
        tableCommon.addContainerProperty("User ID", String.class, null);
        tableCommon.addContainerProperty("Login", String.class, null);
        tableCommon.addContainerProperty("e-Mail", String.class, null);
        tableCommon.addContainerProperty("Status", String.class, null);
        tableCommon.addContainerProperty("User Type", String.class, null);
        tableCommon.setSelectable(true);
        tableCommon.setWidth("100%");
        tableCommon.setHeight(height);
        tableCommon.setImmediate(true);

        tableCommon.setStyleName("iso3166");
        tableCommon.setColumnReorderingAllowed(true);
        tableCommon.setColumnCollapsingAllowed(true);

        try {
            usersData = new UsersData();
            usersData.setUserDataNonBanned();
            int i = 1;
            for (UserList userListIter : usersData.getUsersNonBannedList()){
                Integer itemId = new Integer(i);
                tableCommon.addItem(new Object[]{i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                        userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                i++;
            }
            tableCommon.addListener(new Table.ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {
                    try {
                        //current.setValue("Selected: " + tableCommon.getContainerProperty(tableCommon.getValue(), "Login").getValue());
                        currentUser = String.valueOf(tableCommon.getContainerProperty(tableCommon.getValue(), "Login").getValue());
                    } catch (NullPointerException e) {
                        currentUser = "noUser";
                        //current.setValue("Selected: -");
                    }
                }
            });
        }  catch (Exception e) {
            e.printStackTrace();
        }

        splitH.setSecondComponent(tableLayout);
        tableLayout.setMargin(false);
        //tableLayout.addComponent(current);
        tableLayout.addComponent(tableCommon);
    }

    public void refreshTableData(){
        try {
            tableCommon.removeAllItems();
            usersData = new UsersData();
            if(selectedUserType.equals("")){
                usersData.setUserDataNonBanned();
                int i = 1;
                for (UserList userListIter : usersData.getUsersNonBannedList()){
                    Integer itemId = new Integer(i);
                    tableCommon.addItem(new Object[]{i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                            userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                    i++;
                }
            } else {
                usersData.setUserListFilteredNonBanned(selectedUserType);
                int i = 1;
                for (UserList userListIter : usersData.getUserListFilteredNonBanned()){
                    Integer itemId = new Integer(i);
                    tableCommon.addItem(new Object[]{i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                            userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                    i++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTableDataFiltered(){
        try {
            tableCommon.removeAllItems();
            usersData = new UsersData();
            usersData.setUserListFilteredNonBanned(selectedUserType);
            int i = 1;
            for (UserList userListIter : usersData.getUserListFilteredNonBanned()){
                Integer itemId = new Integer(i);
                tableCommon.addItem(new Object[]{i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                        userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTableDataSearchedNonFiltered(){
        try {
            tableCommon.removeAllItems();
            usersData = new UsersData();
            usersData.setUsersListSearchNonFilteredNonBanned(String.valueOf(searchField.getValue()));
            int i = 1;
            for (UserList userListIter : usersData.getUserListSearchNonBannedNonFiltered()){
                Integer itemId = new Integer(i);
                tableCommon.addItem(new Object[]{i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                        userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTableDataSearchedFiltered(){
        try {
            tableCommon.removeAllItems();
            usersData = new UsersData();
            usersData.setUsersListSearchFilteredNonBanned(String.valueOf(searchField.getValue()), selectedUserType);
            int i = 1;
            for (UserList userListIter : usersData.getUserListSearchNonBannedFiltered()){
                Integer itemId = new Integer(i);
                tableCommon.addItem(new Object[]{i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                        userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void valueChange(ValueChangeEvent event) {
        if (!lastAdded) {
            getWindow().showNotification("Selected user type: " + event.getProperty(), Window.Notification.TYPE_TRAY_NOTIFICATION);
            selectedUserType = String.valueOf(event.getProperty());
            refreshTableDataFiltered();
            if (selectedUserType == null) {
                selectedUserType = "";
                refreshTableData();
            }
        }
        lastAdded = false;
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
        } else if (source == changeUserTypeButton) {
            if (currentUser.equals("noUser")){
                Window.Notification n = new Window.Notification("Юзер не выбран", Window.Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("Для смены типа юзера пожалуйста выбирите его из списка!");
                n.setPosition(Window.Notification.POSITION_CENTERED);
                getWindow().showNotification(n);
            } else {
                changeUserTypeWindow = new ChangeUserTypeWindow(this, currentUser);
                getWindow().addWindow(changeUserTypeWindow);
            }
        } else if (source == clearSearchButton) {
            searchField.setValue("");
            if(selectedUserType.equals("")){
                refreshTableData();
            } else {
                refreshTableDataFiltered();
            }
        } else if (source == userActionsButton){
            usersActionsListWindow = new UsersActionsListWindow(this, mainPage);
            getWindow().addWindow(usersActionsListWindow);
        } else if (source == searchButton){
            if (selectedUserType.equals("")) {
                if(searchField.getValue().equals("")){
                    getWindow().showNotification("Please type any text for searching", Window.Notification.TYPE_TRAY_NOTIFICATION);
                } else {
                    refreshTableDataSearchedNonFiltered();
                }
            }  else {
                if(searchField.getValue().equals("")){
                    getWindow().showNotification("Please type any text for searching", Window.Notification.TYPE_TRAY_NOTIFICATION);
                } else {
                    refreshTableDataSearchedFiltered();
                }
            }
        }
    }

    /*private boolean isValid() {
        if(searchField.isValid()) {
            return true;
        }
        return false;
    }*/

    public void blur(FieldEvents.BlurEvent event) {
        Object source = event.getComponent();
        if(source instanceof TextField) {
            TextField tf = (TextField) source;
            tf.isValid();
        }
    }

    @Override
    public void addNewItem(String newItemCaption) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
