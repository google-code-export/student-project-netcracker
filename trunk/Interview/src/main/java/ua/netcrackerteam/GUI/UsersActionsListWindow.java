package ua.netcrackerteam.GUI;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import ua.netcrackerteam.DAO.AuditInterview;
import ua.netcrackerteam.controller.GeneralController;
import ua.netcrackerteam.controller.UsersData;

/**
 * @author krygin
 */
public class UsersActionsListWindow extends Window implements AbstractSelect.NewItemHandler {
    AdminUserManagementLayout adminUserManagementLayout;
    private Table table;
    private UsersData usersData = null;
    //private Label current = new Label("Selected: -");
    private String currentUser = "noUser";
    private int height;
    HorizontalLayout horizontalLayoutComboBoxFilters = new HorizontalLayout();
    HorizontalLayout horizontalLayoutTextFilters = new HorizontalLayout();
    HorizontalLayout statisticsLayoutTotalActions = new HorizontalLayout();
    HorizontalLayout statisticsLayoutUserRegistered = new HorizontalLayout();
    HorizontalLayout statisticsLayoutLoginTried = new HorizontalLayout();
    HorizontalLayout statisticsLayoutBanActiveCount = new HorizontalLayout();

    TextField filterFieldLogin = new TextField("Filter by Login");

    private ComboBox comboBoxFilterLogin = new ComboBox("Filter by login");
    private ComboBox comboBoxFilterUserType = new ComboBox("Filter by user type", new IndexedContainer());
    private ComboBox comboBoxFilterAction = new ComboBox("Filter by action");
    private ComboBox comboboxFilterDate = new ComboBox("Filter by Date");

    private String selectedComboboxItem = "";
    private SimpleStringFilter filter = null;

    private Label countTotalActions;
    private Label countRegisteredUsersAll;
    private Label countRegisteredAdmins;
    private Label countRegisteredHRs;
    private Label countRegisteredInterviewers;
    private Label countRegisteredStudents;
    private Label countLoginTriedAll;
    private Label countLoginTriedRegistered;
    private Label countLoginTriedNonRegistered;
    private Label countBanedUsers;
    private Label countActiveUsers;

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
        height = webBrowser.getScreenHeight()-390;

        table = new Table();
        table.addContainerProperty("ID", Integer.class, null);
        table.addContainerProperty("Login", String.class, null);
        table.addContainerProperty("User Type", String.class, null);
        table.addContainerProperty("Action", String.class, null);
        table.addContainerProperty("Action Description", String.class, null);
        table.addContainerProperty("Action Date", String.class, null);
        table.addContainerProperty("Action Time", String.class, null);
        table.setSelectable(true);
        table.setWidth("100%");
        table.setSortAscending(false);
        table.setHeight(height);
        table.setImmediate(true);
        table.setStyleName("iso3166");
        table.setColumnReorderingAllowed(true);
        table.setColumnCollapsingAllowed(true);

        filterFieldLogin.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.LAZY);
        filterFieldLogin.setTextChangeTimeout(200);

        try {
            usersData = new UsersData();
            usersData.setAuditInterviews();
            int i = 1;
            for (AuditInterview userListIter : usersData.getAuditInterviews()){
                Integer itemId = new Integer(i);
                table.addItem(new Object[]{userListIter.getIdAudit(), userListIter.getUserName(), userListIter.getUserCategory().getName(),
                        userListIter.getAction().getActionName(), userListIter.getActionDescription(),
                        (userListIter.getActionDate().getYear() - 100 + 2000) + "-" + (userListIter.getActionDate().getMonth() + 1) + "-" + userListIter.getActionDate().getDate(),
                        userListIter.getActionDate().getHours() + ":" +  userListIter.getActionDate().getMinutes() + ":" + userListIter.getActionDate().getSeconds()}, itemId);

                comboBoxFilterLogin.addItem(userListIter.getUserName());
                comboBoxFilterUserType.addItem(userListIter.getUserCategory().getName());
                comboBoxFilterAction.addItem(userListIter.getAction().getActionName());
                comboboxFilterDate.addItem((userListIter.getActionDate().getYear() - 100 + 2000) + "-" + (userListIter.getActionDate().getMonth() + 1) + "-" + userListIter.getActionDate().getDate());

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

            filterFieldLogin.addListener(new FieldEvents.TextChangeListener() {
                public void textChange(FieldEvents.TextChangeEvent event) {
                    Container.Filterable containerFilterable = (Container.Filterable) table.getContainerDataSource();
                    if (filter != null) {
                        containerFilterable.removeContainerFilter(filter);
                    }
                    filter = new SimpleStringFilter("Login", event.getText(), false, false);
                    containerFilterable.addContainerFilter(filter);
                }
            });

        }  catch (Exception e) {
            e.printStackTrace();
        }

        comboBoxFilterLogin.setNewItemHandler(this);
        comboBoxFilterLogin.setNewItemsAllowed(false);
        comboBoxFilterLogin.setRequired(false);
        comboBoxFilterLogin.setImmediate(true);
        comboBoxFilterLogin.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                //SimpleStringFilter filter = null;
                Container.Filterable containerFilterable = (Container.Filterable) table.getContainerDataSource();
                if (filter != null) {
                    selectedComboboxItem = "";
                    containerFilterable.removeContainerFilter(filter);
                }
                getWindow().showNotification("Selected user login: " + event.getProperty(), Notification.TYPE_TRAY_NOTIFICATION);
                selectedComboboxItem = String.valueOf(event.getProperty());

                if (selectedComboboxItem == null) {
                    selectedComboboxItem = "";
                    containerFilterable.removeContainerFilter(filter);
                } else {
                    filter = new SimpleStringFilter("Login", String.valueOf(event.getProperty()), false, false);
                    containerFilterable.addContainerFilter(filter);
                }
            }
        });

        comboBoxFilterUserType.setNewItemHandler(this);
        comboBoxFilterUserType.setNewItemsAllowed(false);
        comboBoxFilterUserType.setRequired(false);
        comboBoxFilterUserType.setImmediate(true);
        ((IndexedContainer)comboBoxFilterUserType.getContainerDataSource()).sort(new Object[]{"User Type"}, new boolean[]{true});
        comboBoxFilterUserType.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Container.Filterable containerFilterable = (Container.Filterable) table.getContainerDataSource();
                if (filter != null) {
                    selectedComboboxItem = "";
                    containerFilterable.removeContainerFilter(filter);
                }
                getWindow().showNotification("Selected user type: " + event.getProperty(), Notification.TYPE_TRAY_NOTIFICATION);
                selectedComboboxItem = String.valueOf(event.getProperty());
                if (selectedComboboxItem == null) {
                    selectedComboboxItem = "";
                    containerFilterable.removeContainerFilter(filter);
                } else {
                    filter = new SimpleStringFilter("User Type", String.valueOf(event.getProperty()), false, false);
                    containerFilterable.addContainerFilter(filter);
                }
            }
        });

        comboBoxFilterAction.setNewItemHandler(this);
        comboBoxFilterAction.setNewItemsAllowed(false);
        comboBoxFilterAction.setRequired(false);
        comboBoxFilterAction.setImmediate(true);
        comboBoxFilterAction.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Container.Filterable containerFilterable = (Container.Filterable) table.getContainerDataSource();
                if (filter != null) {
                    selectedComboboxItem = "";
                    containerFilterable.removeContainerFilter(filter);
                }
                getWindow().showNotification("Selected action: " + event.getProperty(), Notification.TYPE_TRAY_NOTIFICATION);
                selectedComboboxItem = String.valueOf(event.getProperty());
                if (selectedComboboxItem == null) {
                    selectedComboboxItem = "";
                    containerFilterable.removeContainerFilter(filter);
                } else {
                    filter = new SimpleStringFilter("Action", String.valueOf(event.getProperty()), false, false);
                    containerFilterable.addContainerFilter(filter);
                }
            }
        });

        comboboxFilterDate.setNewItemHandler(this);
        comboboxFilterDate.setNewItemsAllowed(false);
        comboboxFilterDate.setRequired(false);
        comboboxFilterDate.setImmediate(true);
        comboboxFilterDate.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Container.Filterable containerFilterable = (Container.Filterable) table.getContainerDataSource();
                if (filter != null) {
                    selectedComboboxItem = "";
                    containerFilterable.removeContainerFilter(filter);
                }
                getWindow().showNotification("Selected date: " + event.getProperty(), Notification.TYPE_TRAY_NOTIFICATION);
                selectedComboboxItem = String.valueOf(event.getProperty());
                if (selectedComboboxItem == null) {
                    selectedComboboxItem = "";
                    containerFilterable.removeContainerFilter(filter);
                } else {
                    filter = new SimpleStringFilter("Action Date", String.valueOf(event.getProperty()), false, false);
                    containerFilterable.addContainerFilter(filter);
                }
            }
        });

        countTotalActions = new Label("Total actions : " + GeneralController.getCountedTotalActions() + "    ");
        countRegisteredUsersAll = new Label("Total registered users : " + GeneralController.getCountedRegisteredUsersAll() + "  Registered admins");
        countRegisteredAdmins = new Label(": " + GeneralController.getCountedRegisteredAdmins() + "  Registered HRs");
        countRegisteredHRs = new Label(": " + GeneralController.getCountedRegisteredHRs() + "  Registered interviewers");
        countRegisteredInterviewers = new Label(": " + GeneralController.getCountedRegisteredInterviewers() + "  Registered students");
        countRegisteredStudents = new Label(": " + GeneralController.getCountedRegisteredStudents() + " ");
        countLoginTriedAll = new Label("Total login tried : " + GeneralController.getCountedLoginTriedAllUsers() + "  Login tried by registered users ");
        countLoginTriedRegistered = new Label(": " + GeneralController.getCountedLoginTriedRegisteredUsers() + "  Login tried by non-registered users ");
        countLoginTriedNonRegistered = new Label(": " + GeneralController.getCountedLoginTriedNonRegisteredUsers() + " ");
        countActiveUsers = new Label("Total active users : " + GeneralController.getCountedActiveUsers() + "  Total banned users ");
        countBanedUsers = new Label(": " + GeneralController.getCountedBannedUsers());

        horizontalLayoutComboBoxFilters.addComponent(comboBoxFilterLogin);
        horizontalLayoutComboBoxFilters.addComponent(comboBoxFilterUserType);
        horizontalLayoutComboBoxFilters.addComponent(comboBoxFilterAction);
        horizontalLayoutComboBoxFilters.addComponent(comboboxFilterDate);
        horizontalLayoutTextFilters.addComponent(filterFieldLogin);
        statisticsLayoutTotalActions.addComponent(countTotalActions);
        statisticsLayoutUserRegistered.addComponent(countRegisteredUsersAll);
        statisticsLayoutUserRegistered.addComponent(countRegisteredAdmins);
        statisticsLayoutUserRegistered.addComponent(countRegisteredHRs);
        statisticsLayoutUserRegistered.addComponent(countRegisteredInterviewers);
        statisticsLayoutUserRegistered.addComponent(countRegisteredStudents);
        statisticsLayoutLoginTried.addComponent(countLoginTriedAll);
        statisticsLayoutLoginTried.addComponent(countLoginTriedRegistered);
        statisticsLayoutLoginTried.addComponent(countLoginTriedNonRegistered);
        statisticsLayoutBanActiveCount.addComponent(countActiveUsers);
        statisticsLayoutBanActiveCount.addComponent(countBanedUsers);
        addComponent(horizontalLayoutComboBoxFilters);
        addComponent(horizontalLayoutTextFilters);
        addComponent(table);
        addComponent(statisticsLayoutTotalActions);
        addComponent(statisticsLayoutLoginTried);
        addComponent(statisticsLayoutUserRegistered);
        addComponent(statisticsLayoutBanActiveCount);
    }

    @Override
    public void addNewItem(String newItemCaption) {

    }
}

