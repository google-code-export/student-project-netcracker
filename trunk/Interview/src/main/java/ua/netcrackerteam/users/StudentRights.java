package ua.netcrackerteam.users;

import java.util.Observable;
import ua.netcrackerteam.application.*;

/**
 */
public interface StudentRights {
    /**
     * Регистрация студента на собеседование
     * @param interview - интервью на определенный день и время
     */
    public void registrationToInterview(Observable interview);
     /**
     * Отмена регистрации студента на собеседование
     */
    public void deleteRegistrationToInterview();
    /**
     * Перезапись студента на другое собеседование
     * @param interview - интервью на определенный день и время
     */
    public void changeRegistrationToInterview(Observable interview);
    /**
     * Запрос на изменение анкеты
     */
    public void requestToChangeForm();
}
