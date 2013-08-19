package ua.netcrackerteam.validation;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Label;
import ua.netcrackerteam.GUI.MainPage;
import ua.netcrackerteam.controller.GeneralController;

import java.sql.SQLException;

import static ua.netcrackerteam.validation.SystemMessages.SQL_CONNECTION_ERROR;

/**
 * @author AlexK
 * @version 1.0.0
 */
public class FormValidator {
    private static final String REQUIRED_MESSAGE = "Заполните пожалуйста обязательное поле(поля)";
    private static final Integer ZERO = new Integer(0);
    private MainPage mainPage;
    private Label errorLable;
    private Validator validator;

    public Label getErrorLable() {
        return errorLable;
    }

    public void setErrorLable(Label errorLable) {
        this.errorLable = errorLable;
    }

    public FormValidator(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void validateTextFields(AbstractField component, AbstractOrderedLayout layout) {
        try{
            component.setRequiredError(REQUIRED_MESSAGE);
            component.validate();
        } catch (Validator.EmptyValueException e){
            if(errorLable != null){
                layout.removeComponent(errorLable);
            }
            errorLable = new Label(component.getRequiredError());
            errorLable.setStyle("errorLable");
            layout.addComponent(errorLable, ZERO);
        } catch (Validator.InvalidValueException e){

        }
    }

    public Validator existingNickNameValidator() throws SQLException{
        try {
            validator = new AbstractValidator("Такой юзер уже существует, введите другой Логин") {
                @Override
                public boolean isValid(Object value) {
                    boolean result = false;
                    try{
                        result = !GeneralController.checkUsersAvailability(String.valueOf(value));
                    } catch (SQLException e) {
                        mainPage.getMainWindow().showNotification(SQL_CONNECTION_ERROR.getNotification());
                        setErrorMessage(String.valueOf(SQL_CONNECTION_ERROR));
                    }
                    return result;
                }
            };
            return validator;
        } catch (Exception e) {
            mainPage.getMainWindow().showNotification(SQL_CONNECTION_ERROR.getNotification());
            throw new SQLException();
        }
    }
}
