package ua.netcrackerteam.GUI;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import ua.netcrackerteam.controller.GeneralController;

/**
 * @author krygin
 */
public class BanUserWindow extends Window implements Button.ClickListener{
    AdminUserManagementLayout adminUserManagementLayout;
    Button banButton;
    Button cancelButton;
    String currentUser = null;
    Label message = null;
    VerticalLayout layout = (VerticalLayout) getContent();
    HorizontalLayout buttonsPanel = new HorizontalLayout();

    public BanUserWindow(AdminUserManagementLayout adminUserManagementLayout, String currentUser) {
        this.adminUserManagementLayout = adminUserManagementLayout;
        this.currentUser = currentUser;
        this.setIcon(new ThemeResource("icons/32/ban-user.png"));
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("Ban User");
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        banButton = new Button("ban");
        cancelButton = new Button("cancelButton");
        message = new Label("Вы действительно хотите забанить " + currentUser + " ?");

        banButton.setVisible(true);
        cancelButton.setVisible(true);

        banButton.addListener(this);
        cancelButton.addListener(this);

        layout.addComponent(message);
        layout.setComponentAlignment(message, Alignment.TOP_CENTER);
        layout.addComponent(buttonsPanel);
        layout.setComponentAlignment(buttonsPanel, Alignment.BOTTOM_CENTER);
        buttonsPanel.addComponent(banButton);
        buttonsPanel.addComponent(cancelButton);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if (source == banButton) {
            String userName = currentUser;
            GeneralController.bunUserByName(userName);
            Notification n = new Notification("Блокироваие юзера завершено успешно!", Notification.TYPE_TRAY_NOTIFICATION);
            n.setDescription(" Юзер " + userName + " был забанен. Уведомление о блокировке отправлено юзеру на его e-mail.");
            n.setPosition(Notification.POSITION_CENTERED);
            adminUserManagementLayout.getWindow().showNotification(n);
            adminUserManagementLayout.refreshTableData();
            adminUserManagementLayout.refreshTableData();
            BanUserWindow.this.close();
        } else if (source == cancelButton) {
            BanUserWindow.this.close();
        }
    }
}
