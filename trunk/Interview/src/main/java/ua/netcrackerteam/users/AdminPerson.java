package ua.netcrackerteam.users;


/**
 * author tanya
 */
public class AdminPerson extends Persons implements AdminRights{
    
     /**
     * Deleting a user
     * @param userName
     */
    @Override
    public final void removeUser(String userName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Adding a user
     * @param userName
     * @param rights
     */
    @Override
    public final void addUser(String userName, String rights) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
     /**
     * Resetting a user's password
     * @param userName
     * @param password
     */
    @Override
    public final void changePasswordUser(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Changing a user's login
     * @param userName
     * @param newUserName 
     */
    public void changeLoginUser(String userName, String newUserName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
 
    
}
