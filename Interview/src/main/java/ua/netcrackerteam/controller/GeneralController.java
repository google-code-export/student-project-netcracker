package ua.netcrackerteam.controller;


import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.configuration.HibernateFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralController {

    public static String passwordHashing(String pass){
        String hashedPass = String.valueOf(("user".hashCode() * pass.hashCode() + (pass.hashCode() + pass.hashCode()) * 10 + pass.hashCode() * "pass".hashCode()) * 10);
        return hashedPass;
    }

    public static int checkLogin(String user, String pass){
        List<UserList> listOfForms = null;
        String userName = null;
        String userPass = null;
        String hashedPass = null;
        int idUserCategory = 0;
        try {
            listOfForms = HibernateFactory.getInstance().getCommonDao().getUser();
            hashedPass = GeneralController.passwordHashing(pass);
            for (UserList userList : listOfForms) {
                userName = userList.getUserName();
                userPass = userList.getPassword();
                if (user.equals(userName) && hashedPass.equals(userPass)){
                    idUserCategory = userList.getIdUserCategory();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idUserCategory;
    }

    public static List<Integer> checkLoginIdUser(String user, String pass){
        List<UserList> listOfForms = null;
        List<Integer> checkedUserIds = new ArrayList<Integer>();
        String userName = null;
        String userPass = null;
        String hashedPass = null;
        try {
            listOfForms = HibernateFactory.getInstance().getCommonDao().getUser();
            hashedPass = GeneralController.passwordHashing(pass);
            for (UserList userList : listOfForms) {
                userName = userList.getUserName();
                userPass = userList.getPassword();
                if (user.equals(userName) && hashedPass.equals(userPass)){
                    checkedUserIds.add(userList.getIdUser());
                    checkedUserIds.add(userList.getIdUserCategory());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkedUserIds;
    }

    public static void setUsualUser(String userName, String userPassword, String userEmail){
        String active = "active";
        int idUserCategory = 4;
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

    public static void main(String[] args) throws SQLException {
        //setUsualUser("Alex3", "12345", "sdfsdf@sdfsdf.df");
        /*String nickName = userNameSplitFromEmail("fdgdfg@gdfgdf.com");
        System.out.println(nickName);*/
        List<Integer> ids = checkLoginIdUser("admin", "abyrabyrabyr");
        for(int i = 0; i < ids.size(); i++){
            System.out.println(ids.get(i));
        }

        /*List<UserList> userLists = HibernateFactory.getInstance().getCommonDao().getUser();
        for (UserList userList : userLists){
            System.out.println(userList.getUserName() + "   " + userList.getPassword());
        }*/
    }
}
