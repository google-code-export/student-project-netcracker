package ua.netcrackerteam.GUI;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import ua.netcrackerteam.controller.GeneralController;

/**
 * @author krygin
 */
public class DeleteUserWindow extends Window implements Button.ClickListener{
    AdminUserManagementLayout adminUserManagementLayout;
    Button deleteButton;
    Button cancelButton;
    VerticalLayout layout = (VerticalLayout) getContent();
    HorizontalLayout buttonsPanel = new HorizontalLayout();
    String currentUser = null;
    Label message = null;

    public DeleteUserWindow(AdminUserManagementLayout adminUserManagement, String currentUser) {
        this.adminUserManagementLayout = adminUserManagement;
        this.currentUser = currentUser;
        this.setIcon(new ThemeResource("icons/32/remove-user.png"));
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("Delete User");

        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        deleteButton = new Button("delete");
        cancelButton = new Button("cancel");
        message = new Label("Вы действительно хотите удалить " + currentUser + " ?");

        deleteButton.setVisible(true);
        cancelButton.setVisible(true);

        deleteButton.addListener(this);
        cancelButton.addListener(this);

        layout.addComponent(message);
        layout.setComponentAlignment(message, Alignment.TOP_CENTER);
        layout.addComponent(buttonsPanel);
        layout.setComponentAlignment(buttonsPanel, Alignment.BOTTOM_CENTER);
        buttonsPanel.addComponent(deleteButton);
        buttonsPanel.addComponent(cancelButton);
    }

    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if (source == deleteButton) {
            String userName = currentUser;
            GeneralController.deleteUserByName(userName);
            Notification n = new Notification("Удаление юзера завершено успешно!", Notification.TYPE_TRAY_NOTIFICATION);
            n.setDescription(" Юзер " + userName + " был удалён");
            n.setPosition(Notification.POSITION_CENTERED);
            adminUserManagementLayout.getWindow().showNotification(n);
            adminUserManagementLayout.refreshTableData();
            adminUserManagementLayout.refreshTableData();
            DeleteUserWindow.this.close();
        } else if (source == cancelButton) {
            DeleteUserWindow.this.close();
        }
    }
}
