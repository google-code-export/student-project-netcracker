package ua.netcrackerteam.users;

/**
 * author tanya
 */
public interface AdminRights  {
    
    /**
     * Deleting a user
     * @param userName
     */
    public void removeUser(String userName);
    /**
     * Adding a user
     * @param userName
     * @param rights
     */
    public void addUser(String userName, String rights);
    /**
     * Resetting a user's password
     * @param userName
     * @param password
     */
    public void changePasswordUser(String userName, String password);
    
    /**
     * Changing a user's login
     * @param userName
     * @param newUserName 
     */
    public void changeLoginUser(String userName, String newUserName);
    
}
