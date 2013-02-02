package ua.netcrackerteam.DAO;

import java.sql.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.HibernateUtil;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of DAOStudent
 * @author
 */
public class DAOStudentImpl implements DAOStudent {
    public List GetNamesAndContacts() throws SQLException {
        Session session = null;
        Query re = null;
        List listOfForms = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from Form");
            listOfForms = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listOfForms;
    }

    public List GetUser() throws SQLException {
        Session session = null;
        Query re = null;
        List listOfForms = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList");
            listOfForms = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listOfForms;
    }

    public void setUser(Long idUser,
                        String userName,
                        String userPassword,
                        String userEmail,
                        String active,
                        Long idUserCategory) throws SQLException{
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            UserList userList = new UserList();
            userList.setIdUser(idUser);
            userList.setUserName(userName);
            userList.setPassword(userPassword);
            userList.setEmail(userEmail);
            userList.setActive(active);
            userList.setIdUserCategory(idUserCategory);
            session.save(userList);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }



    public int deleteUserById(int user_Id) {
        Session session = null;
        Query re = null;
        Transaction transaction = null;
        int number = 0;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            re = session.createQuery("delete from UserList where idUser = '" + user_Id + "'");
            number = re.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return number;
    }

    public static String passwordHashing(String pass){
        String hashedPAss = String.valueOf(("user".hashCode() * pass.hashCode() +
                (pass.hashCode() + pass.hashCode()) * 10 + pass.hashCode() * "pass".hashCode()) * 10);
        return hashedPAss;
    }

    public boolean userEnteringCheck(String user, String pass){
        try {
            List<UserList> listOfForms = HibernateFactory.getInstance().getStudentDAO().GetUser();
            String userName = null;
            String userPass = null;
            String hashedPass = null;
            hashedPass = DAOStudentImpl.passwordHashing(pass);
            for (UserList currForm : listOfForms) {
                userName = currForm.getUserName();
                userPass = currForm.getPassword();
                if (user.equals(userName) && hashedPass.equals(userPass)){
                        return true;
                }
                continue;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        boolean check = HibernateFactory.getInstance().getStudentDAO().userEnteringCheck("admin", "abyrabyrabyr");
        System.out.println(check);
        
        Form form = HibernateFactory.getInstance().getStudentDAO().getFormById(9L);
        System.out.println(form.getFirstName());
        
        Form form1 = new Form();
        form1.setIdForm(20L);
        form1.setFirstName("Иван");
        form1.setLastName("Царевич");
        form1.setMiddleName("Дурак");
        form1.setExecProject("Сайт неткрекера");
        form1.setReason("adfd");
        form1.setExtraInfo("dfdfd");
        form1.setInstituteYear(4);
        Date date = new Date(2013, 1, 1);
        form1.setInstituteGradYear(date);
        Date date1 = new Date(2007, 6, 25);
        form1.setSchoolGradYear(date1);
        form1.setExtraKnowledge("dfdfdf");
        form1.setInterestStudy("+");
        form1.setInterestWork("+");
        form1.setInterestSoftware("-");
        form1.setInterestTelecom("?");
        form1.setAvgScore(95.0);
        form1.setAvgLast(92.0);
        form1.setPhoto("photo");
        form1.setIdStatus(1L);
        form1.setIdInstitute(1L);
        form1.setIdSchool(1L);
        form1.setIdUser(2L);
        form1.setIdInterview(1L);
        HibernateFactory.getInstance().getStudentDAO().addForm(form1);

        /*int sdfsdf = HibernateFactory.getInstance().getStudentDAO().deleteUserById(9);
        System.out.println(sdfsdf);*/

        /*List<UserList> listOfForms = HibernateFactory.getInstance().getStudentDAO().GetUseById(9);
        for (UserList currForm : listOfForms) {
            String fName = currForm.getUserName();
            String lName = currForm.getPassword();
            System.out.println(fName + " " + lName);
        }*/

        /*String password = "abyrabyrabyr";
        String readyPass= DAOStudentImpl.passwordHashing(password);
        HibernateFactory.getInstance().getStudentDAO().setUser(new Long(9),
                "admin", readyPass, "email@com.ua", "active", new Long(1));*/
    }

    @Override
    public Form getFormById(Long idForm) {        
        Session session = null;
        Query query;        
        Form form = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from Form where idForm = " + idForm);
            form = (Form) query.uniqueResult();           
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return form;       
    }

    @Override
    public void addForm(Form form) {
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();            
            session.save(form);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateFormById(Long idForm, Form form) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection getAllForms() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteFormById(Long FormId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


