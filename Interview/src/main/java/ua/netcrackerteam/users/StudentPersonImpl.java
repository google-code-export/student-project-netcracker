package ua.netcrackerteam.users;

import java.util.Observable;

/**
 * author tanya
 */
public class StudentPersonImpl implements StudentPerson {
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

    /*public Set<StudentPersonImpl> setFIO() throws SQLException {
        Set<StudentPersonImpl> ss = null;
        try{
            ss = new HashSet<StudentPersonImpl>();
            List<Form> listOfForms = HibernateFactory.getInstance().getStudentDAO().getAllForms();
            for (Form currForm : listOfForms) {
                String fName = currForm.getFirstName();
                String lName = currForm.getLastName();
                StudentPersonImpl sp  = new StudentPersonImpl();
                sp.setName(fName);
                sp.setSurname(lName);
                ss.add(sp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ss;
    }*/

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
}
