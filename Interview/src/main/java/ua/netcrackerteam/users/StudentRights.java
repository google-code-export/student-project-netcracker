package ua.netcrackerteam.users;

import java.util.Observable;

/**
 * author tanya
 */
public interface StudentRights extends Persons{
    /**
     * Registration for interview
     * @param interview
     */
    public void registrationToInterview(Observable interview);
     /**
     * De-registering for an interview
     */
    public void deleteRegistrationToInterview();
    /**
     * Rewrite to another interview
     * @param interview
     */
    public void changeRegistrationToInterview(Observable interview);
    /**
     * Request to change the form
     */
    public void requestToChangeForm();

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
