package ua.netcrackerteam.users;

/**
 * author tanya
 */
public interface AdminRights  {
    
    /**
     * Удаление пользователя
     * @param userName логин пользователя 
     */
    public void removeUser(String userName);
    /**
     * Добавление полльзователя
     * @param userName - логин пользователя
     * @param rights - набор прав пользователя
     */
    public void addUser(String userName, String rights);
    /**
     * Сброс пароля пользователя
     * @param userName - логин пользователя
     * @param password - новый пароль пользователя
     */
    public void changePasswordUser(String userName, String password);
    
}
