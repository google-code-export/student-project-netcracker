package ua.netcrackerteam.GUI;

import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import ua.netcrackerteam.controller.GeneralController;

/**
 * @author krygin
 */
public class ResetUserLoginWindow extends Window implements FieldEvents.BlurListener{
    AdminUserManagementLayout adminUserManagementLayout;
    TextField userName;
    Label message;
    String currentUser = null;

    public ResetUserLoginWindow(AdminUserManagementLayout adminUserManagementLayout, String currentUser) {
        this.adminUserManagementLayout = adminUserManagementLayout;
        this.currentUser = currentUser;
        this.setIcon(new ThemeResource("icons/32/change-login.png"));
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("New Login for User");
        VerticalLayout layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        message = new Label("Смена логина для юзера - " + currentUser);
        layout.addComponent(message);

        userName = new TextField("Введите новый логин: ");
        userName.setRequired(true);
        userName.addListener((FieldEvents.BlurListener) this);
        userName.setMaxLength(25);
        layout.addComponent(userName);

        layout.setComponentAlignment(userName, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(message, Alignment.TOP_CENTER);

        userName.addValidator(new RegexpValidator("\\w{3,}", "Имя должно быть не короче 3х символов латиницы."));

        Button okBut = new Button("reset user login");
        okBut.addListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                buttonClickRegistr();
            }
        });
        layout.addComponent(okBut);
        layout.setComponentAlignment(okBut, Alignment.TOP_CENTER);
    }

    public void buttonClickRegistr() {
        if (isValid()) {
            String oldUserName = currentUser;
            String newUserName = String.valueOf(userName);
            if (!(GeneralController.checkUserName(newUserName))){
                GeneralController.setNewLogin(oldUserName, newUserName);
                Notification n = new Notification("Изменение логина юзера завершено успешно!", Notification.TYPE_TRAY_NOTIFICATION);
                n.setDescription("На email " + newUserName + " выслано письмо с новым паролем.\n" +
                        "Теперь юзер может зайти под своими новыми аккаунт данными.");
                n.setPosition(Notification.POSITION_CENTERED);
                adminUserManagementLayout.getWindow().showNotification(n);
                adminUserManagementLayout.refreshTableData();
                adminUserManagementLayout.refreshTableData();
                ResetUserLoginWindow.this.close();
                /*try {
                    SendMails.sendMailToUserAfterReg(userEmail, userName, userPassword);
                } catch (EmailException e) {
                    e.printStackTrace();
                }*/
            } else {
                getWindow().showNotification("Такой никнейм уже существует, пожалуйста выберите другой !", Notification.TYPE_TRAY_NOTIFICATION);
            }

        }
    }

    private boolean isValid() {
        if (userName.isValid()) {
            return true;
        }
        return false;
    }

    public void blur(FieldEvents.BlurEvent event) {
        Object source = event.getComponent();
        if(source instanceof TextField) {
            TextField tf = (TextField) source;
            tf.isValid();
        }
    }
}
