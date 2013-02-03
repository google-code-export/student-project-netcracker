package ua.netcrackerteam.controller;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.HibernateUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class GeneralController {
    public static String passwordHashing(String pass){
        String hashedPAss = String.valueOf(("user".hashCode() * pass.hashCode() +
                (pass.hashCode() + pass.hashCode()) * 10 + pass.hashCode() * "pass".hashCode()) * 10);
        return hashedPAss;
    }

    public boolean userEnteringCheck(String user, String pass){
        List<UserList> listOfForms = null;
        String userName = null;
        String userPass = null;
        String hashedPass = null;
        try {
            listOfForms = HibernateFactory.getInstance().getStudentDAO().GetUser();
            hashedPass = GeneralController.passwordHashing(pass);
            for (UserList userList : listOfForms) {
                userName = userList.getUserName();
                userPass = userList.getPassword();
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
}
