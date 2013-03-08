package ua.netcrackerteam.controller;


import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.Logable;
import ua.netcrackerteam.configuration.ShowHibernateSQLInterceptor;

import javax.interceptor.Interceptors;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralController implements Logable {

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static String passwordHashing(String pass){
        String hashedPass = null;
        try {
            hashedPass = String.valueOf(("user".hashCode() * pass.hashCode() + (pass.hashCode() + pass.hashCode()) * 10 + pass.hashCode() * "pass".hashCode()) * 10);
        } catch (Exception e) {
            e.printStackTrace();
            logger.getLog().error(e);
        }
        return hashedPass;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static String passwordHashingMD5(String pass) {
        String hashedPass = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(pass.getBytes(), 0, pass.length());
            hashedPass = new BigInteger(1, m.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            logger.getLog().error(e);
        }
        return hashedPass;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
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
                    idUserCategory = userList.getIdUserCategory().getIdUSerCategory();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.getLog().error(e);
        }
        return idUserCategory;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static boolean checkUserName(String user){
        List<UserList> listOfForms = null;
        String userName = null;
        try {
            listOfForms = HibernateFactory.getInstance().getCommonDao().getUser();
            for (UserList userList : listOfForms) {
                userName = userList.getUserName();
                if (user.equals(userName)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.getLog().error(e);
        }
        return false;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
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
                    checkedUserIds.add(userList.getIdUserCategory().getIdUSerCategory());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.getLog().error(e);
        }
        return checkedUserIds;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static void setUsualUser(String userName, String userPassword, String userEmail){
        String active = "active";
        int idUserCategory = 4;
        String hashPassword = passwordHashing(userPassword);
        try {
            HibernateFactory.getInstance().getCommonDao().setUser(userName, hashPassword, userEmail, active, idUserCategory);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.getLog().error(e);
        }
    }

    public static void setAdminUser(String userName, String userPassword, String userEmail){
        String active = "active";
        int idUserCategory = 1;
        String hashPassword = passwordHashing(userPassword);
        try {
            HibernateFactory.getInstance().getCommonDao().setUser(userName, hashPassword, userEmail, active, idUserCategory);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.getLog().error(e);
        }
    }

    public static void setInterviewerUser(String userName, String userPassword, String userEmail){
        String active = "active";
        int idUserCategory = 3;
        String hashPassword = passwordHashing(userPassword);
        try {
            HibernateFactory.getInstance().getCommonDao().setUser(userName, hashPassword, userEmail, active, idUserCategory);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.getLog().error(e);
        }
    }

    public static void setHRUser(String userName, String userPassword, String userEmail){
        String active = "active";
        int idUserCategory = 2;
        String hashPassword = passwordHashing(userPassword);
        try {
            HibernateFactory.getInstance().getCommonDao().setUser(userName, hashPassword, userEmail, active, idUserCategory);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.getLog().error(e);
        }
    }

    public static void deleteUserByName(String userName){
        HibernateFactory.getInstance().getCommonDao().deleteUserByName(userName);
    }

    public static void bunUserByName(String userName){
        HibernateFactory.getInstance().getAdminDAO().banUserByName(userName);
    }

    public static void activateUserByName(String userName){
        HibernateFactory.getInstance().getAdminDAO().activateUserByName(userName);
    }

    public static boolean checkUserBan(String userName) {
        boolean checkResult = HibernateFactory.getInstance().getAdminDAO().checkUserBanStatus(userName);
        return checkResult;
    }

    public static void deleteDirectory(File dir) {
        try {
            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i=0; i<children.length; i++) {
                    File f = new File(dir, children[i]);
                    deleteDirectory(f);
                }
                dir.delete();
            } else {
                dir.delete();
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.getLog().error("Something wrong with folder deleting", e);
        }
    }

    public static void setNewPassword(String userName, String newPassword){
        String newHashedPassword = GeneralController.passwordHashing(newPassword);
        HibernateFactory.getInstance().getAdminDAO().resetOnNewPassword(userName, newHashedPassword);
    }

    public static void setNewLogin(String oldUserName, String newUserName){
        HibernateFactory.getInstance().getAdminDAO().resetOnNewLogin(oldUserName, newUserName);
    }

    public static void setNewEmail(String userName, String userEmail){
        HibernateFactory.getInstance().getCommonDao().resetOnNewEmail(userName, userEmail);
    }

    public static String getEmailFromUserName(String userName) {
        String currentEmail = HibernateFactory.getInstance().getStudentDAO().getEmailByUserName(userName);
        return currentEmail;
    }

    public static void changeUserType(String userName, String newTypeString){
        int newTypeInt = 0;
        if (newTypeString.equals("Admin")){
            newTypeInt = 1;
            HibernateFactory.getInstance().getAdminDAO().changeUserType(userName, newTypeInt);
        } else if (newTypeString.equals("HR")){
            newTypeInt = 2;
            HibernateFactory.getInstance().getAdminDAO().changeUserType(userName, newTypeInt);
        } else if (newTypeString.equals("Interviewer")){
            newTypeInt = 3;
            HibernateFactory.getInstance().getAdminDAO().changeUserType(userName, newTypeInt);
        }
    }

   /* public static String checkInputText(String inputText){
        String result = inputText.replaceAll("\n", " ");
        return result;
    }*/

    /*public static String userNameSplitFromEmail(String email){
        String[] splitedString = email.split("@");
        String splitedName = splitedString[0];
        return splitedName;
    }*/

    public static void main(String[] args) throws SQLException {
        setUsualUser("gglex34zfzd2e", "1234556", "sdfsdf@sdfsdf.df");
        /*String nickName = userNameSplitFromEmail("fdgdfg@gdfgdf.com");
        System.out.println(nickName);*/
        /*List<Integer> ids = checkLoginIdUser("admin", "abyrabyrabyr");
        for(int i = 0; i < ids.size(); i++){
            System.out.println(ids.get(i));
        }*/

//        String hashedPass = passwordHashingMD5("abyrabyrabyr");
//        String hashedPass2 = passwordHashingMD5("abyrabyraby");
//        String hashedPass3 = passwordHashingMD5("abyrabyrabyr");
//        String hashedPass4 = passwordHashingMD5("abyrabyrabr");
//        System.out.println(hashedPass);
//        System.out.println(hashedPass2);
//        System.out.println(hashedPass3);
//        System.out.println(hashedPass4);
//
//        List<UserList> userLists = HibernateFactory.getInstance().getCommonDao().getUser();
//        for (UserList userList : userLists){
//            System.out.println(userList.getUserName() + "   " + userList.getPassword());
//        }
    }
}
