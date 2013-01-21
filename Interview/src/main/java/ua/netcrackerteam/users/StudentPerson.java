package ua.netcrackerteam.users;

import java.util.Observable;
import ua.netcrackerteam.application.*;
/**
 * author tanya
 */
public class StudentPerson implements StudentRights{
    
    /**
     * Fill student's form
     */
    public Form form;
    
     /**
     * Registration for interview
     * @param interview
     */
    @Override
    public void registrationToInterview(Observable interview) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * De-registering for an interview
     */
    @Override
    public void deleteRegistrationToInterview() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Rewrite to another interview
     * @param interview
     */
    @Override
    public void changeRegistrationToInterview(Observable interview) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Request to change the form
     */
    @Override
    public void requestToChangeForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void login(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(String userName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean changePassword(String exsistPassword, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
