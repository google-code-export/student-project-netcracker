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
public class TestLayout extends VerticalLayout implements Button.ClickListener {
    private String username;
    private final MainPage mainPage;
    private Accordion accordion;
    private VerticalLayout userManagementLayout;
    private Button deleteUserButton = new Button          ("Delete user______");
    private Button addAdminUserButton = new Button        ("Add Admin________");
    private Button addHRUserButton = new Button           ("Add HR___________");
    private Button addInterviewerUserButton = new Button  ("Add Interviewer__");
    private Button refreshDataButton = new Button         ("Refresh Table____");
    private Button resetUserPasswordButton = new Button   ("Reset Password___");
    private Table table;
    private UsersData usersData = null;
    private Label current = new Label("Selected: -");
    private String currentUser = "noUser";
    private AddNewAdminUserWindow addNewAdminUserWindow = null;
    private AddNewInterviewerUserWindow addNewInterviewerUserWindow = null;
    private AddNewHRUserWindow addNewHRUserWindow = null;
    private DeleteUserWindow deleteUserWindow = null;
    private ResetUserasswordWindow resetUserPasswordWindow = null;
    private int height;

    public TestLayout(String username, MainPage mainPage) {
        this.username = username;
        this.mainPage = mainPage;

        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-310;

        addAdminUserButton.setVisible(true);
        addHRUserButton.setVisible(true);
        addInterviewerUserButton.setVisible(true);
        deleteUserButton.setVisible(true);
        refreshDataButton.setVisible(true);
        resetUserPasswordButton.setVisible(true);

        refreshDataButton.setIcon(new ThemeResource("icons/32/reload.png"));
        addAdminUserButton.setIcon(new ThemeResource("icons/32/document-add.png"));
        addInterviewerUserButton.setIcon(new ThemeResource("icons/32/document-add.png"));
        addHRUserButton.setIcon(new ThemeResource("icons/32/document-add.png"));
        deleteUserButton.setIcon(new ThemeResource("icons/32/document-delete.png"));
        resetUserPasswordButton.setIcon(new ThemeResource("icons/32/group_key.png"));

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
        addButtonsLayout.addComponent(addAdminUserButton);
        addButtonsLayout.addComponent(addHRUserButton);
        addButtonsLayout.addComponent(addInterviewerUserButton);

        VerticalLayout refreshButtonLayout = new VerticalLayout();
        refreshButtonLayout.addComponent(refreshDataButton);

        VerticalLayout deleteButtonLayout = new VerticalLayout();
        deleteButtonLayout.addComponent(deleteUserButton);

        VerticalLayout modifyUsersLayout = new VerticalLayout();
        modifyUsersLayout.addComponent(resetUserPasswordButton);

        accordion = new Accordion();
        accordion.setSizeFull();
        accordion.addTab(addButtonsLayout, "Add users");
        accordion.addTab(modifyUsersLayout, "Modify Users");
        accordion.addTab(refreshButtonLayout, "Refresh Table");
        accordion.addTab(deleteButtonLayout, "Delete User");
        sidebar.setContent(accordion);

        table = new Table();
        table.addContainerProperty("User ID", String.class,  null);
        table.addContainerProperty("Login",  String.class,  null);
        table.addContainerProperty("e-Mail",  String.class, null);
        table.addContainerProperty("Status",  String.class, null);
        table.addContainerProperty("User Type",  String.class, null);
        table.setSelectable(true);
        table.setWidth("100%");
        table.setHeight(height);
        table.setImmediate(true);
        //table.setMultiSelect(true);
        try {
            usersData = new UsersData();
            usersData.setUserData();
            int i = 1;
            for (UserList userListIter : usersData.getUserList()){
                Integer itemId = new Integer(i);
                table.addItem(new Object[] {userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                        userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                i++;
            }
            table.addListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    current.setValue("Selected: " + table.getContainerProperty(table.getValue(), "Login").getValue());
                    currentUser = String.valueOf(table.getContainerProperty(table.getValue(), "Login").getValue());
                }
                /*public void valueChange(Property.ValueChangeEvent event) {
                    Set<?> value = (Set<?>) event.getProperty().getValue();
                    if (null == value || value.size() == 0) {
                        current.setValue("No selection");
                    } else {
                        current.setValue("Selected: " + table.getContainerProperty(table.getValue(), "Login").getValue());
                    }

                }*/
                /*public void valueChange(Property.ValueChangeEvent event) {
                    Set<?> value = (Set<?>) event.getProperty().getValue();
                    if (null == value || value.size() == 0) {
                        current.setValue("Selected: -");
                    } else {
                        current.setValue("Selected: " + table.getContainerProperty(table.getValue(), "Login").getValue());
                    }
                    currentUser = String.valueOf(table.getContainerProperty(table.getValue(), "Login").getValue());
                }*/
            });
        }  catch (Exception e) {
            e.printStackTrace();
        }

        VerticalLayout tableLayout = new VerticalLayout();
        splitH.setSecondComponent(tableLayout);

        tableLayout.setMargin(false);
        tableLayout.addComponent(current);
        tableLayout.addComponent(table);

        deleteUserButton.addListener(this);
        addAdminUserButton.addListener(this);
        addInterviewerUserButton.addListener(this);
        addHRUserButton.addListener(this);
        refreshDataButton.addListener(this);
        resetUserPasswordButton.addListener(this);
    }

    public void refreshTableData(){
        try {
            table.removeAllItems();
            usersData = new UsersData();
            usersData.setUserData();
            int i = 1;
            for (UserList userListIter : usersData.getUserList()){
                Integer itemId = new Integer(i);
                table.addItem(new Object[] {userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
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
            /*if (currentUser.equals("noUser")){
                Window.Notification n = new Window.Notification("Юзер не выбран", Window.Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("Пожалуйста выбирите юзера для удаления из списка !");
                n.setPosition(Window.Notification.POSITION_CENTERED);
                getWindow().showNotification(n);
            } else {
                deleteUserWindow = new DeleteUserWindow(this, currentUser);
                getWindow().addWindow(deleteUserWindow);
            }*/
        } else if (source == addAdminUserButton) {
            addNewAdminUserWindow = new AddNewAdminUserWindow(this);
            getWindow().addWindow(addNewAdminUserWindow);
        } /*else if (source == addInterviewerUserButton) {
            addNewInterviewerUserWindow = new AddNewInterviewerUserWindow(this);
            getWindow().addWindow(addNewInterviewerUserWindow);
        } else if (source == addHRUserButton) {
            addNewHRUserWindow = new AddNewHRUserWindow(this);
            getWindow().addWindow(addNewHRUserWindow);
        }*/ else if (source == refreshDataButton) {
            refreshTableData();
        } else if (source == resetUserPasswordButton) {
            if (currentUser.equals("noUser")){
                Window.Notification n = new Window.Notification("Юзер не выбран", Window.Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("Для смены пароля пожалуйста выбирите юзера из списка!");
                n.setPosition(Window.Notification.POSITION_CENTERED);
                getWindow().showNotification(n);
            } else {
                resetUserPasswordWindow = new ResetUserasswordWindow(this, currentUser);
                getWindow().addWindow(resetUserPasswordWindow);
            }
        }
    }
}
