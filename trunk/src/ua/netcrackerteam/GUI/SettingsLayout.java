/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import ua.netcrackerteam.controller.GeneralController;

/**
 *
 * @author akush_000, krygin
 */
public class SettingsLayout extends VerticalLayout implements Button.ClickListener{
    private final MainPage mainPage;
    private final Button saveButton;
    private final String userName;
    Panel panelEmail = new Panel("Настройки почты");
    Panel panelPassword = new Panel("Настройки пароля");
    VerticalLayout vl = (VerticalLayout) panelEmail.getContent();
    Label oldEmailInscription = new Label("Ваш текущий email:");
    Label oldEmail = new Label();
    TextField newEmail = new TextField("Новый email:");
    Label label = new Label("Для смены пароля нажмите кнопку ниже!");
    Button resetUserPasswordButton = new Button   ("Reset Password");
    ResetUserPasswordWindow resetUserPasswordWindow = null;
    HorizontalSplitPanel hl = new HorizontalSplitPanel();
    private int height;

    public SettingsLayout(String userName, MainPage mainPage) {
        this.mainPage = mainPage;
        this.userName = userName;

        WebApplicationContext context = (WebApplicationContext) mainPage.getContext();
        WebBrowser webBrowser = context.getBrowser();
        height = webBrowser.getScreenHeight()-310;

        setMargin(true);
        setSpacing(true);
        vl.setMargin(true);
        vl.setSpacing(true);
        panelEmail.setWidth("100%");
        addComponent(hl);

        hl.setFirstComponent(panelEmail);
        hl.setSecondComponent(panelPassword);

        oldEmail.setValue(GeneralController.getEmailFromUserName(userName));
        oldEmail.setWidth("250");
        oldEmail.setReadOnly(false);

        newEmail.setWidth("250");
        newEmail.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));

        saveButton = new Button("Сохранить изменения");
        saveButton.setVisible(true);
        saveButton.setIcon(new ThemeResource("icons/32/save.png"));

        resetUserPasswordButton.setVisible(true);
        resetUserPasswordButton.setIcon(new ThemeResource("icons/32/group_key.png"));
        resetUserPasswordButton.addListener(this);

        oldEmailInscription.setReadOnly(true);
        panelEmail.addComponent(oldEmailInscription);
        panelEmail.addComponent(oldEmail);
        panelEmail.addComponent(newEmail);
        panelEmail.addComponent(saveButton);
        panelPassword.addComponent(label);
        panelPassword.addComponent(resetUserPasswordButton);

        panelEmail.setHeight(height);
        panelPassword.setHeight(height);

        saveButton.addListener(this);
    }

    private boolean isValid() {
        if(newEmail.isValid()) {
            return true;
        }
        return false;
    }

    public void refreshLable(){
        oldEmail.setValue(GeneralController.getEmailFromUserName(userName));
        oldEmail.setValue(GeneralController.getEmailFromUserName(userName));
        oldEmail.setValue(GeneralController.getEmailFromUserName(userName));
        oldEmail.setValue(GeneralController.getEmailFromUserName(userName));
    }

    public void refrachTextField(){
        newEmail.setValue("");
    }

    @Override
    public void buttonClick(ClickEvent event) {
        Button b = event.getButton();
        if (b == saveButton) {
            if (isValid()){
                if (!(newEmail.getValue().equals(""))){
                    if (!GeneralController.checkUserEmail(userName, String.valueOf(newEmail))){
                        GeneralController.setNewEmail(userName, String.valueOf(newEmail));
                        getWindow().showNotification("Изменение электронной почты юзера завершено успешно! ", Window.Notification.TYPE_TRAY_NOTIFICATION);
                        refrachTextField();
                        refreshLable();
                    } else {
                        getWindow().showNotification("У Вас уже такой email - " + newEmail.toString().trim() + ". " +
                                "Введите пожалуйста другой.", Window.Notification.TYPE_TRAY_NOTIFICATION);
                    }
                } else {
                    getWindow().showNotification("Введите имейл для изменения", Window.Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        } else if (b == resetUserPasswordButton) {
                resetUserPasswordWindow = new ResetUserPasswordWindow(this, userName);
                getWindow().addWindow(resetUserPasswordWindow);
        }
    }
}
