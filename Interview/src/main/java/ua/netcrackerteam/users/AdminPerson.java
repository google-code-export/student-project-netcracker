package ua.netcrackerteam.users;

import java.util.Observable;
import java.util.Observer;

/**
 * author tanya
 */
public class AdminPerson extends Persons implements AdminRights{
    
     /**
     * Удаление пользователя
     * @param userName логин пользователя 
     */
    @Override
    public void removeUser(String userName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Добавление полльзователя
     * @param userName - логин пользователя
     * @param rights - набор прав пользователя
     */
    @Override
    public void addUser(String userName, String rights) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
     /**
     * Сброс пароля пользователя
     * @param userName - логин пользователя
     * @param password - новый пароль пользователя
     */
    @Override
    public void changePasswordUser(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
 
    
}
