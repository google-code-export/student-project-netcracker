package ua.netcrackerteam.persons;

import java.util.Observable;

/**
 * author tanya
 */
public class StudentPerson {
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String currName) {
        name = currName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String currSurname) {
        surname = currSurname;
    }

     /**
     * Registration for interview
     * @param interview
     */
    public void registrationToInterview(Observable interview) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * De-registering for an interview
     */
    public void deleteRegistrationToInterview() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Rewrite to another interview
     * @param interview
     */
    public void changeRegistrationToInterview(Observable interview) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Request to change the form
     */
    public void requestToChangeForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
