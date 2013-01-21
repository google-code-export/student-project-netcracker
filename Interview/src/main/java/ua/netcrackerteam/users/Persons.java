package ua.netcrackerteam.users;

/**
 * @author tanya
 */
public interface Persons {
    /**
     * Authorization
     * @param userName
     * @param password
     */
    public void login(String userName, String password);

    /**
     * UnAuthorization
     * @param userName
     */
    public void logout(String userName);

    /**
     * Changing of password
     * @param exsistPassword
     * @param newPassword
     * @return true/false
     */
    public boolean changePassword(String exsistPassword, String newPassword);
}
