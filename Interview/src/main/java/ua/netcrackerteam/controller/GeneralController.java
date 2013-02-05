package ua.netcrackerteam.controller;


import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.configuration.HibernateFactory;

import java.sql.SQLException;
import java.util.List;

public class GeneralController {

    public static String passwordHashing(String pass){
        String hashedPass = String.valueOf(("user".hashCode() * pass.hashCode() + (pass.hashCode() + pass.hashCode()) * 10 + pass.hashCode() * "pass".hashCode()) * 10);
        return hashedPass;
    }

    public boolean checkLogin(String user, String pass){
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

    public static void setUsualUser(String userName, String userPassword, String userEmail){
        String active = "active";
        Long idUserCategory = 4L;
        String hashPassword = passwordHashing(userPassword);
        try {
            HibernateFactory.getInstance().getCommonDao().setUser(userName, hashPassword, userEmail, active, idUserCategory);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static String userNameSplitFromEmail(String email){
        String[] splitedString = email.split("@");
        String splitedName = splitedString[0];
        return splitedName;
    }*/

    public static void main(String[] args) {
        //setUsualUser("Alex3", "12345", "sdfsdf@sdfsdf.df");
        /*String nickName = userNameSplitFromEmail("fdgdfg@gdfgdf.com");
        System.out.println(nickName);*/
    }
}
