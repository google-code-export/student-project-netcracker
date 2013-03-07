package ua.netcrackerteam.GUI;

import com.vaadin.ui.*;
import ua.netcrackerteam.controller.GeneralController;

/**
 * @author krygin
 */
public class DeleteUserWindow extends Window implements Button.ClickListener{
    AdminUserManagement adminUserManagement;
    Button deleteButton;
    Button cancelButton;

    String currentUser = null;
    Label message = null;

    public DeleteUserWindow(AdminUserManagement adminUserManagement, String currentUser) {
        this.adminUserManagement = adminUserManagement;
        this.currentUser = currentUser;
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("Delete User");
        VerticalLayout layout = (VerticalLayout) getContent();
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
        layout.addComponent(deleteButton);
        layout.setComponentAlignment(deleteButton, Alignment.TOP_CENTER);
        layout.addComponent(cancelButton);
        layout.setComponentAlignment(cancelButton, Alignment.TOP_CENTER);
    }

    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if (source == deleteButton) {
            String userName = currentUser;
            GeneralController.deleteUserByName(userName);
            Notification n = new Notification("Удаление юзера завершено успешно!", Notification.TYPE_TRAY_NOTIFICATION);
            n.setDescription(" Юзер " + userName + " был удалён");
            n.setPosition(Notification.POSITION_CENTERED);
            adminUserManagement.getWindow().showNotification(n);
            adminUserManagement.refreshTableData();
            adminUserManagement.refreshTableData();
            DeleteUserWindow.this.close();
        } else if (source == cancelButton) {
            DeleteUserWindow.this.close();
        }
    }
}
