package ua.netcrackerteam.users;

/**
 * author tanya
 */
public interface AdminRights  {
    
    public void removeUser(String userName);
    public void addUser(String userName, String rights);
    public void changePasswordUser(String userName, String password);
    
}
