package ua.netcrackerteam.users;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import ua.netcrackerteam.DAO.*;
import ua.netcrackerteam.DAO.Form;
import ua.netcrackerteam.application.*;
import ua.netcrackerteam.configuration.HibernateFactory;

/**
 * author tanya
 */
public class StudentPerson implements StudentRights{
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

    public Set<StudentPerson> setFIO() throws SQLException {
        Set<StudentPerson> ss = null;
        try{
            ss = new HashSet<StudentPerson>();
            List<Form> listOfForms = HibernateFactory.getInstance().getStudentDAO().GetNamesAndContacts();
            for (Form currForm : listOfForms) {
                String fName = currForm.getFirstName();
                String lName = currForm.getLastName();
                StudentPerson sp  = new StudentPerson();
                sp.setName(fName);
                sp.setSurname(lName);
                ss.add(sp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ss;
    }

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
