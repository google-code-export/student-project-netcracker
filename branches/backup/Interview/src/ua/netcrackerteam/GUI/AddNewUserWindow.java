package ua.netcrackerteam.GUI;

import com.vaadin.data.Property;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import ua.netcrackerteam.controller.GeneralController;

import java.util.Date;

/**
 * @author krygin
 */
public class AddNewUserWindow extends Window implements FieldEvents.BlurListener, AbstractSelect.NewItemHandler,
        Property.ValueChangeListener{
    AdminUserManagementLayout adminUserManagementLayout;
    TextField userName;
    String userLogin;
    PasswordField password;
    PasswordField password2;
    TextField userEmail;
    private ComboBox comboBox;
    private String selectedUserType = "";
    private Boolean lastAdded = false;
    private static final String[] userTypes = new String[] { "Admin", "HR", "Interviewer" };

    public AddNewUserWindow(AdminUserManagementLayout adminUserManagementLayout, String userLogin) {
        this.adminUserManagementLayout = adminUserManagementLayout;
        this.setIcon(new ThemeResource("icons/32/add-user.png"));
        this.userLogin = userLogin;
        setModal(true);
        setWidth("30%");
        setResizable(false);
        center();
        setCaption("New User");
        VerticalLayout layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        comboBox = new ComboBox("Please select a user Category");
        for (int i = 0; i < userTypes.length; i++) {
            comboBox.addItem(userTypes[i]);
        }
        comboBox.setNewItemHandler(this);
        comboBox.setNewItemsAllowed(false);
        comboBox.setRequired(true);
        comboBox.setImmediate(true);
        comboBox.addListener((Property.ValueChangeListener) this);
        layout.addComponent(comboBox);

        userName = new TextField("Введите логин: ");
        userName.setRequired(true);
        userName.addListener((FieldEvents.BlurListener) this);
        userName.setMaxLength(25);
        layout.addComponent(userName);

        password = new PasswordField("Введите пароль: ");
        password.setRequired(true);
        password.addListener((FieldEvents.BlurListener) this);
        password.setMaxLength(25);
        layout.addComponent(password);

        password2 = new PasswordField("Повторите пароль: ");
        password2.setRequired(true);
        password2.addListener((FieldEvents.BlurListener) this);
        password2.setMaxLength(25);
        layout.addComponent(password2);

        userEmail = new TextField("Введите e-mail: ");
        userEmail.setRequired(true);
        userEmail.addListener((FieldEvents.BlurListener) this);
        userEmail.setMaxLength(25);
        layout.addComponent(userEmail);

        userName.addValidator(new RegexpValidator("\\w{3,}", "Имя должно быть не короче 3х символов латиницы."));
        userEmail.addValidator(new EmailValidator("Email должен содержать знак '@' и полный домен."));
        password.addValidator(new RegexpValidator("\\w{6,}", "Пароль должен содержать буквы английского алфавита и/или цифры, и быть не короче 6 символов."));
        password2.addValidator(new AbstractValidator("Пароли должны совпадать.") {
            public boolean isValid(Object value) {
                return password.getValue().equals(password2.getValue());
            }
        });

        layout.setComponentAlignment(comboBox, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(userName, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(password, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(password2, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(userEmail, Alignment.BOTTOM_CENTER);

        Button okBut = new Button("add New user");
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
            String userName = String.valueOf(this.getUserName());
            String userPassword = String.valueOf(this.getPassword());
            String userEmail = String.valueOf(this.getUserEmail());
            if (selectedUserType.equals("")) {
                getWindow().showNotification("Выбирете тип юзера, пожалуйста", Notification.TYPE_TRAY_NOTIFICATION);
            } else if (selectedUserType.equals("Admin")){
                if (!(GeneralController.checkUserName(userName))){
                    GeneralController.setAdminUser(userName, userPassword, userEmail);
                    GeneralController.setAuditInterviews(5, "Admin added new Admin - " + userName, userLogin, new Date());
                    Notification n = new Notification("Регистрация нового Админа завершена успешно!", Notification.TYPE_TRAY_NOTIFICATION);
                    n.setDescription("На email нового юзера(Админа) выслано письмо с регистрационными данными.\n" +
                            "Теперы юзер(Админ) может зайти под своими аккаунт данными.");
                    n.setPosition(Notification.POSITION_CENTERED);
                    adminUserManagementLayout.getWindow().showNotification(n);
                    adminUserManagementLayout.refreshTableData();
                    adminUserManagementLayout.refreshTableData();
                    AddNewUserWindow.this.close();
                } else {
                    getWindow().showNotification("Такой никнейм уже существует, пожалуйста выберите другой !", Notification.TYPE_TRAY_NOTIFICATION);
                }
            }  else if (selectedUserType.equals("HR")){
                if (!(GeneralController.checkUserName(userName))){
                    GeneralController.setHRUser(userName, userPassword, userEmail);
                    GeneralController.setAuditInterviews(5, "Admin added new HR - " + userName, userLogin, new Date());
                    Notification n = new Notification("Регистрация нового HR завершена успешно!", Notification.TYPE_TRAY_NOTIFICATION);
                    n.setDescription("На email нового юзера(HR) выслано письмо с регистрационными данными.\n" +
                            "Теперы юзер(HR) может зайти под своими аккаунт данными.");
                    n.setPosition(Notification.POSITION_CENTERED);
                /*try {
                    SendMails.sendMailToUserAfterReg(userEmail, userName, userPassword);
                } catch (EmailException e) {
                    e.printStackTrace();
                }*/
                    adminUserManagementLayout.getWindow().showNotification(n);
                    adminUserManagementLayout.refreshTableData();
                    adminUserManagementLayout.refreshTableData();
                    AddNewUserWindow.this.close();
                } else {
                    getWindow().showNotification("Такой никнейм уже существует, пожалуйста выберите другой !", Notification.TYPE_TRAY_NOTIFICATION);
                }
            }  else if (selectedUserType.equals("Interviewer")){
                if (!(GeneralController.checkUserName(userName))){
                    GeneralController.setInterviewerUser(userName, userPassword, userEmail);
                    GeneralController.setAuditInterviews(5, "Admin added new Inrerviewer - " + userName, userLogin, new Date());
                    Window.Notification n = new Window.Notification("Регистрация нового Интервьювера завершена успешно!", Window.Notification.TYPE_TRAY_NOTIFICATION);
                    n.setDescription("На email нового юзера(Интервьювера) выслано письмо с регистрационными данными.\n" +
                            "Теперь юзер(Интервьювер) может зайти под своими аккаунт данными.");
                    n.setPosition(Window.Notification.POSITION_CENTERED);
                /*try {
                    SendMails.sendMailToUserAfterReg(userEmail, userName, userPassword);
                } catch (EmailException e) {
                    e.printStackTrace();
                }*/
                    adminUserManagementLayout.getWindow().showNotification(n);
                    adminUserManagementLayout.refreshTableData();
                    adminUserManagementLayout.refreshTableData();
                    AddNewUserWindow.this.close();
                } else {
                    getWindow().showNotification("Такой никнейм уже существует, пожалуйста выберите другой !", Notification.TYPE_TRAY_NOTIFICATION);
                }
            }
        }
    }

    public TextField getUserName() {
        return userName;
    }

    public PasswordField getPassword() {
        return password2;
    }

    public TextField getUserEmail() {
        return userEmail;
    }

    private boolean isValid() {
        if(userEmail.isValid() && password.isValid() && password2.isValid() && userName.isValid()) {
            return true;
        }
        return false;
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

    public void blur(FieldEvents.BlurEvent event) {
        Object source = event.getComponent();
        if(source instanceof TextField) {
            TextField tf = (TextField) source;
            tf.isValid();
        } else if(source instanceof PasswordField){
            PasswordField pf = (PasswordField) source;
            pf.isValid();
        }
    }

    @Override
    public void addNewItem(String newItemCaption) {
    }
}
