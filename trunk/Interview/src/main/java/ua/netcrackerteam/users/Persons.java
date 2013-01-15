package ua.netcrackerteam.users;


/**
 * author tanya
 */
public class Persons {
   
       
    /**
     * Authorization
     * @param userName 
     * @param password
     */
    public void login(String userName, String password){
            
    }
    
     /**
     * UnAuthorization
     * @param userName 
     */
    public void logout(String userName){
        
    }
    /**
     * Check the password and login
     * @param userName
     * @param password
     * @return true/false
     */
    public final boolean passwordValid(String userName, String password){
        return true;
    }  
    
     /** Recover password  - password send to the email
     * @param email 
     */
    public void recoverPassword(String email){
        
    }
    
    /**
     * Check the email for recover email
     * @param email
     * @return true/false
     */
    public final boolean emailValid(String email) {
        return true;
    }
}
