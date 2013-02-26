/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author akush_000
 */
class SettingsLayout extends VerticalLayout implements Button.ClickListener{
//    private final Button newPass;
    private final Button save;
    private final String username;

    public SettingsLayout(String username) {
        this.username = username;
        setMargin(true);
        setSpacing(true);
        Panel panel = new Panel("Настройки аккаунта");
        VerticalLayout vl = (VerticalLayout) panel.getContent();
        vl.setMargin(true);
        vl.setSpacing(true);
        panel.setWidth("100%");
        addComponent(panel);
        TextField oldEmail = new TextField("Ваш текущий email:");
        oldEmail.setValue(getEmailFromUsername());
        oldEmail.setWidth("250");
        oldEmail.setReadOnly(true);
        TextField newEmail = new TextField("Новый email:");
        newEmail.setWidth("250");
//        newPass = new Button("Запросить новый пароль");
//        newPass.addListener(this);
        save = new Button("Сохранить изменения");
        panel.addComponent(oldEmail);
        panel.addComponent(newEmail);
//        panel.addComponent(newPass);
        panel.addComponent(save);
    }

    private String getEmailFromUsername() {
        return new ua.netcrackerteam.DAO.DAOStudentImpl().getEmailByUserName(username);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        Button b = event.getButton();
        if (b == save) {
            
        }
    }
}
