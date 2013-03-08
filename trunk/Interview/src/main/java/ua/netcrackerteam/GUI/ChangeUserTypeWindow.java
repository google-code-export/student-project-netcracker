package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import ua.netcrackerteam.controller.GeneralController;

/**
 * @author krygin
 */
public class ChangeUserTypeWindow extends Window implements AbstractSelect.NewItemHandler,
        Property.ValueChangeListener, Button.ClickListener{
    AdminUserManagementLayout adminUserManagementLayout;
    private final String currentUser;
    private ComboBox comboBox;
    private String selectedUserType = "";
    private Boolean lastAdded = false;
    private static final String[] userTypes = new String[] { "Admin", "HR", "Interviewer" };
    Button acceptChangeButton = new Button("change user type");

    public ChangeUserTypeWindow(AdminUserManagementLayout adminUserManagementLayout, String currentUser) {
        this.adminUserManagementLayout = adminUserManagementLayout;
        this.currentUser = currentUser;
        setModal(true);
        setWidth("30%");
        setIcon(new ThemeResource("icons/32/change-user-type.png"));
        setResizable(false);
        center();
        setCaption("Change User Type");
        VerticalLayout layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        comboBox = new ComboBox("Выбирете категорию юзера для - " + currentUser);
        for (int i = 0; i < userTypes.length; i++) {
            comboBox.addItem(userTypes[i]);
        }
        comboBox.setNewItemHandler(this);
        comboBox.setNewItemsAllowed(false);
        comboBox.setRequired(true);
        comboBox.setImmediate(true);
        comboBox.addListener(this);
        layout.addComponent(comboBox);

        acceptChangeButton.addListener((Button.ClickListener) this);
        acceptChangeButton.setIcon(new ThemeResource("icons/32/ok.png"));
        layout.addComponent(acceptChangeButton);

        layout.setComponentAlignment(comboBox, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(acceptChangeButton, Alignment.BOTTOM_CENTER);
    }

    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if (source == acceptChangeButton) {
            if (selectedUserType.equals("")) {
                getWindow().showNotification("Братиша, выбери-ка Тип юзера А??!!!", Notification.TYPE_TRAY_NOTIFICATION);
            }  else {
                GeneralController.changeUserType(currentUser, selectedUserType);
                Notification n = new Notification("Смена типа юзера завершена успешно!", Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("На email юзера " + currentUser + " выслано письмо с уведомлением.");
                n.setPosition(Notification.POSITION_CENTERED);
                adminUserManagementLayout.getWindow().showNotification(n);
                adminUserManagementLayout.refreshTableData();
                adminUserManagementLayout.refreshTableData();
                ChangeUserTypeWindow.this.close();
            }
        }
    }

    public void valueChange(Property.ValueChangeEvent event) {
        if (!lastAdded) {
            getWindow().showNotification("Selected user type: " + event.getProperty(), Notification.TYPE_TRAY_NOTIFICATION);
            selectedUserType = String.valueOf(event.getProperty());
            if (selectedUserType == null) {
                selectedUserType = "";
            }
        }
        lastAdded = false;
    }

    @Override
    public void addNewItem(String newItemCaption) {

    }
}
