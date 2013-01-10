package ua.netcrackerteam.users;


/**
 * author tanya
 */
public class Persons {
   
       
    /**
     * Авторизация на сайте
     * @param userName - логин пользователя
     * @param password - пароль пользователя 
     */
    public void login(String userName, String password){
            
    }
    
     /**
     * Деавторизация пользователя
     * @param userName - логин пользователя  
     */
    public void logout(String userName){
        
    }
    /**
     * Проверка правельности введенных пароля и логина
     * @param userName - логин пользователя
     * @param password - пароль пользователя
     * @return true/false
     */
    public final boolean passwordValid(String userName, String password){
        return true;
    }  
    
     /** Восстановление пароля - пароль высылается на email
     * @param email - пароль пользователя
     */
    public void recoverPassword(String email){
        
    }
    
    /**
     * Проверка правельности введенного email для восстановления пароля на сайте
     * @param email
     * @return true/false
     */
    public final boolean emailValid(String email) {
        return true;
    }
}
