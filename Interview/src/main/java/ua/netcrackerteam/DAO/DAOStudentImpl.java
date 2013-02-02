package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.HibernateUtil;
import java.sql.SQLException;
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
        String hashedPAss = String.valueOf("user" + (pass.hashCode() + pass.hashCode()) * 10 + "pass");
        return hashedPAss;
    }

    public boolean userEnteringCheck(String user, String pass){
        try {
            List<UserList> listOfForms = HibernateFactory.getInstance().getStudentDAO().GetUser();
            String userName = null;
            String userPass = null;
            String hashedPass = null;
            for (UserList currForm : listOfForms) {
                userName = currForm.getUserName();
                userPass = currForm.getPassword();
                hashedPass = DAOStudentImpl.passwordHashing(userPass);
                if (user.equals(userName) && hashedPass.equals(pass)){
                        return true;
                }
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        boolean check = HibernateFactory.getInstance().getStudentDAO().userEnteringCheck("admin", "adsgsdgsd");
        System.out.println(check);

        /*int sdfsdf = HibernateFactory.getInstance().getStudentDAO().deleteUserById(9);
        System.out.println(sdfsdf);*/

        /*List<UserList> listOfForms = HibernateFactory.getInstance().getStudentDAO().GetUseById(9);
        for (UserList currForm : listOfForms) {
            String fName = currForm.getUserName();
            String lName = currForm.getPassword();
            System.out.println(fName + " " + lName);
        }*/

        /*String password = "adsgsdgsd";
        String readyPass= DAOStudentImpl.passwordHashing(password);
        HibernateFactory.getInstance().getStudentDAO().setUser(new Long(9),
                "admin", readyPass, "email@com.ua", "active", new Long(1));*/
    }
}


