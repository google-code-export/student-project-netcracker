package ua.netcrackerteam.users;

import java.util.Observable;

/**
 * author tanya
 */
public interface StudentPerson extends Persons{
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
}
