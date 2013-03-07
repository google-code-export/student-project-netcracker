package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.controller.UsersData;

/**
 * @author krygin
 */
public class AdminUserManagement extends VerticalLayout implements Button.ClickListener{
    private String username;
    private final MainPage mainPage;
    private Button deleteUserButton = new Button("Delete user");
    private Button addAdminUserButton = new Button("Add Admin");
    private Button addHRUserButton = new Button("Add HR");
    private Button addInterviewerUserButton = new Button("Add Interviewer");
    private Button refreshDataButton = new Button("Refresh Table");
    private Table table;
    private Label current = new Label("Selected: -");
    private String currentUser = "noUser";
    private UsersData usersData = null;
    private AddNewAdminUserWindow addNewAdminUserWindow = null;
    private AddNewInterviewerUserWindow addNewInterviewerUserWindow = null;
    private AddNewHRUserWindow addNewHRUserWindow = null;
    private DeleteUserWindow deleteUserWindow = null;

    public AdminUserManagement(String username, MainPage mainPage){
        this.username = username;
        this.mainPage = mainPage;

        addAdminUserButton.setVisible(true);
        addHRUserButton.setVisible(true);
        addInterviewerUserButton.setVisible(true);
        deleteUserButton.setVisible(true);
        refreshDataButton.setVisible(true);

        refreshDataButton.setIcon(new ThemeResource("icons/32/reload.png"));
        addAdminUserButton.setIcon(new ThemeResource("icons/32/document-add.png"));
        addInterviewerUserButton.setIcon(new ThemeResource("icons/32/document-add.png"));
        addHRUserButton.setIcon(new ThemeResource("icons/32/document-add.png"));
        deleteUserButton.setIcon(new ThemeResource("icons/32/document-delete.png"));

        table = new Table();
        table.addContainerProperty("№", Integer.class,  null);
        table.addContainerProperty("User ID", String.class,  null);
        table.addContainerProperty("Login",  String.class,  null);
        table.addContainerProperty("e-Mail",  String.class, null);
        table.addContainerProperty("Active Status",  String.class, null);
        table.addContainerProperty("User Category",  String.class, null);
        //table.setMultiSelect(true);
        try {
            usersData = new UsersData();
            usersData.setUserData();
            int i = 1;
            for (UserList userListIter : usersData.getUserList()){
                Integer itemId = new Integer(i);
                table.addItem(new Object[] {i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                        userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                i++;
            }
            table.addListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    current.setValue("Selected: " + table.getContainerProperty(table.getValue(), "Login").getValue());
                    currentUser = String.valueOf(table.getContainerProperty(table.getValue(), "Login").getValue());
                }
            });
        }  catch (Exception e) {
            e.printStackTrace();
        }

        HorizontalLayout buttonsPanel = new HorizontalLayout();
        buttonsPanel.addComponent(addAdminUserButton);
        buttonsPanel.addComponent(addHRUserButton);
        buttonsPanel.addComponent(addInterviewerUserButton);
        buttonsPanel.addComponent(deleteUserButton);
        buttonsPanel.addComponent(refreshDataButton);
        addComponent(buttonsPanel);
        addComponent(table);
        addComponent(current);

        table.setSelectable(true);
        table.setWidth("100%");
        table.setHeight("100%");
        table.setImmediate(true);
        deleteUserButton.addListener(this);
        addAdminUserButton.addListener(this);
        addInterviewerUserButton.addListener(this);
        addHRUserButton.addListener(this);
        refreshDataButton.addListener(this);
    }

    public void refreshTableData(){
        try {
            table.removeAllItems();
            usersData = new UsersData();
            usersData.setUserData();
            int i = 1;
            for (UserList userListIter : usersData.getUserList()){
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
        } /*else if (source == addAdminUserButton) {
            addNewAdminUserWindow = new AddNewAdminUserWindow(this);
            getWindow().addWindow(addNewAdminUserWindow);
        }*/ else if (source == addInterviewerUserButton) {
            addNewInterviewerUserWindow = new AddNewInterviewerUserWindow(this);
            getWindow().addWindow(addNewInterviewerUserWindow);
        } else if (source == addHRUserButton) {
            addNewHRUserWindow = new AddNewHRUserWindow(this);
            getWindow().addWindow(addNewHRUserWindow);
        } else if (source == refreshDataButton) {
            refreshTableData();
        }
    }
}

