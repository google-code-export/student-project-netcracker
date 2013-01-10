package ua.netcrackerteam.users;

import java.util.Observable;
import ua.netcrackerteam.application.*;
/**
 * author tanya
 */
public class StudentPerson extends Persons implements StudentRights{
    
    /**
     * Заполненая анкета студента
     */
    public Form form;
    
     /**
     * Регистрация студента на собеседование
     * @param interview - интервью на определенный день и время
     */
    @Override
    public void registrationToInterview(Observable interview) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Отмена регистрации студента на собеседование
     */
    @Override
    public void deleteRegistrationToInterview() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Перезапись студента на другое собеседование
     * @param interview - интервью на определенный день и время
     */
    @Override
    public void changeRegistrationToInterview(Observable interview) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Запрос на изменение анкеты
     */
    @Override
    public void requestToChangeForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
