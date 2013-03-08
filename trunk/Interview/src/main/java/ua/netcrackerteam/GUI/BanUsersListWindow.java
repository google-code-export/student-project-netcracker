package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.controller.GeneralController;
import ua.netcrackerteam.controller.UsersData;

/**
 * @author krygin
 */
public class BanUsersListWindow extends Window implements Button.ClickListener{
    AdminUserManagementLayout adminUserManagementLayout;
    private Table table;
    private UsersData usersData = null;
    private Label current = new Label("Selected: -");
    private String currentUser = "noUser";
    private int height;
    HorizontalLayout buttonsLayout;
    VerticalLayout tableLayout;
    Button activateUserButton;
    Button exitButton;
    HorizontalLayout tempLayout = new HorizontalLayout();
    Button yesButton = new Button("Activate user ?");
    Button cancelButton = new Button("No");

    public BanUsersListWindow(AdminUserManagementLayout adminUserManagementLayout, MainPage mainPage) {
        this.adminUserManagementLayout = adminUserManagementLayout;
        this.setIcon(new ThemeResource("icons/32/ban-list.png"));

        setModal(true);
        setWidth(800);
        setResizable(false);
        center();
        setCaption("Banned Users List");

        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-440;

        buttonsLayout = new HorizontalLayout();
        addComponent(buttonsLayout);
        tableLayout = new VerticalLayout();
        addComponent(tableLayout);

        activateUserButton = new Button("Activate user");
        exitButton = new Button("Exit");

        activateUserButton.setVisible(true);
        exitButton.setVisible(true);

        activateUserButton.setIcon(new ThemeResource("icons/32/user-unlock.png"));
        exitButton.setIcon(new ThemeResource("icons/32/exit.png"));

        buttonsLayout.addComponent(activateUserButton);
        buttonsLayout.addComponent(exitButton);

        activateUserButton.addListener(this);
        exitButton.addListener(this);

        yesButton.setVisible(true);
        cancelButton.setVisible(true);
        yesButton.addListener(this);
        cancelButton.addListener(this);
        yesButton.setIcon(new ThemeResource("icons/32/ok.png"));
        cancelButton.setIcon(new ThemeResource("icons/32/cancel.png"));
        tempLayout.addComponent(yesButton);
        tempLayout.addComponent(cancelButton);

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
        //table.setMultiSelect(true);
        try {
            usersData = new UsersData();
            usersData.getUserDataBanned();
            int i = 1;
            for (UserList userListIter : usersData.getUsersBannedList()){
                Integer itemId = new Integer(i);
                table.addItem(new Object[] {i, userListIter.getIdUser(), userListIter.getUserName(), userListIter.getEmail(),
                        userListIter.getActive(), userListIter.getIdUserCategory().getName()}, itemId);
                i++;
            }
            table.addListener(new Table.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    if (event.getProperty() == null) {
                        current.setValue("Selected: -");
                        currentUser = "noUser";
                    } else if (event.getProperty() != null) {
                        current.setValue("Selected: " + table.getContainerProperty(table.getValue(), "Login").getValue());
                        currentUser = String.valueOf(table.getContainerProperty(table.getValue(), "Login").getValue());
                    }
                }

            });
        }  catch (Exception e) {
            e.printStackTrace();
        }

        tableLayout.setMargin(false);
        tableLayout.addComponent(current);
        tableLayout.addComponent(table);
    }

    public void refreshTableData(){
        try {
            table.removeAllItems();
            usersData = new UsersData();
            usersData.getUserDataBanned();
            int i = 1;
            for (UserList userListIter : usersData.getUsersBannedList()){
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
        if (source == exitButton) {
            BanUsersListWindow.this.close();
            adminUserManagementLayout.refreshTableData();
            adminUserManagementLayout.refreshTableData();
        } else if (source == activateUserButton) {
            if (currentUser.equals("noUser")){
                Window.Notification n = new Window.Notification("Юзер не выбран", Window.Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("Для того, чтобы активировать юзера, пожалйста выбирите его из списка !");
                n.setPosition(Window.Notification.POSITION_CENTERED);
                getWindow().showNotification(n);
            } else {
                addComponent(tempLayout);
            }
        } else if (source == cancelButton) {
            this.removeComponent(tempLayout);
            currentUser = "noUser";
        } else if (source == yesButton) {
            String userName = currentUser;
            GeneralController.activateUserByName(userName);
            Notification n = new Notification("Активация юзера завершена успешно!", Notification.TYPE_TRAY_NOTIFICATION);
            n.setDescription(" Юзер " + userName + " был активирован. Соотвествующее уведомление отправлено юзеру на его e-mail.");
            n.setPosition(Notification.POSITION_CENTERED);
            getWindow().showNotification(n);
            refreshTableData();
            refreshTableData();
            this.removeComponent(tempLayout);
            currentUser = "noUser";
        }
    }
}
