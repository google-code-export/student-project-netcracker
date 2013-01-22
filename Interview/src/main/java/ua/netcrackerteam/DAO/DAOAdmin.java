package ua.netcrackerteam.DAO;

import ua.netcrackerteam.configuration.InterviewLoggerSingleton;
import ua.netcrackerteam.configuration.Logable;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Test debugging class for testing methods from DBStandartQuery class
 * @author krygin, maksym, Filipenko
 */
public class DAOAdmin implements Logable {
    /**
     * Main debugging method
     * @param args
     * @throws IOException
     */
    public static void main(String args[])  throws IOException
    {
        String userLogin = "FirstLogin";
        String userPassword = "ПервыйПароль";
        Boolean isUser = SelectUserLoginPassword(userLogin, userPassword);
        System.out.println("Admin 1. user authentication.");
        System.out.println("login - FirstLogin, password - ПервыйПароль");
        System.out.println(isUser);

        System.out.println("============================================");
        System.out.println("Admin 2. List of users.");
        List listOfUsers = SelectListOfUsers();
        Iterator<String> listIterator = listOfUsers.iterator();
        while (listIterator.hasNext()) {
            for (int i = 0; i < 4; i++) {
                System.out.print(listIterator.next() + " ");
            }
            System.out.println();
            System.out.println("-------------------------------------------");
        }

        System.out.println("============================================");
        System.out.println("Admin 3. User list by role.");
        String userRole = "admin";
        List userListByRole = SelectUsersByRole(userRole);
        Iterator<String> userListByRoleIterator = userListByRole.iterator();
        while (userListByRoleIterator.hasNext()) {
            for (int i = 0; i < 4; i++) {
                System.out.print(userListByRoleIterator.next() + " ");
            }
            System.out.println();
            System.out.println("-------------------------------------------");
        }

    }

    /**
     * Test debugging method for using methods from DBStandartQuery class
     * @return list - query result in ArrayList collection                                             `
     */
    public static List GetFIOFromForm() {
        List list = null;
        try {
            list = DBStandartQuery.SelectQuery("f.first_name, f.middle_name, f.last_name, con.info",
                    "form f, contact con",
                    "con.id_form = f.id_form");
            if (list.size() != 0){
                logger.info();
            }
        } catch (NullPointerException er) {
            logger.error(er);
        }
        return list;
    }

    public static Boolean SelectUserLoginPassword(String userLogin, String userPassword) {

        List queryResult    = null;
        Boolean returnData  = false;
        try{

            queryResult = DBStandartQuery.SelectQuery("user_name, password",
                    "user_list",
                    "user_name like '" + userLogin + "' and password like '" + userPassword + "'");
            if (queryResult.size() != 0) {
                logger.info();
                returnData = true;
                return returnData;

            }
        }
        catch (NullPointerException ex) {
            logger.error(ex);
        }
        return returnData;
    }

    public static List SelectListOfUsers() {

        List queryResult = null;

        try {
            queryResult = DBStandartQuery.SelectQuery("u_l.user_name, u_l.email, u_l.active, u_c.category",
                    "user_list u_l, user_category u_c",
                    "u_l.id_user_category = u_c.id_user_category",
                    "u_l.user_name");
        }
        catch (NullPointerException ex) {
            logger.error(ex);
        }

        return queryResult;
    }

    public static List SelectUsersByRole(String userRole) {

        List queryResult = null;

        try {
            queryResult = DBStandartQuery.SelectQuery("u_l.user_name, u_l.email, u_l.active, u_c.category",
                    "user_list u_l left join user_category u_c on u_l.id_user_category = u_c.id_user_category",
                    "u_c.category like '" + userRole + "'");
        }
        catch (NullPointerException ex) {
            logger.error(ex);
        }

        return queryResult;

    }

    public static List searchByField(String field) {

        List queryResult = null;

        try {
            queryResult = DBStandartQuery.SelectQuery("u_l.user_name, u_l.email, u_l.active, u_c.category",
                    "user_list u_l left join user_category u_c on u_l.id_user_category = u_c.id_user_category",
                    "u_l.user_name like '" + field + "'");
        }
        catch (NullPointerException ex) {
            logger.error(ex);
        }

        return queryResult;
    }

    public static Boolean DeleteUser(String userName) {

        Boolean result = false;

        try {
            result = DBStandartQuery.DeleteQuery("user_list",
                    "user_list.user_name like '" + userName + "'");
        }
        catch (NullPointerException ex) {
            logger.error(ex);
        }

        return result;

    }

    public static Boolean CreateUser(String userName, String userPassword, String email, String active, Integer userCategory) {

        Boolean result = false;

        try {
            result = DBStandartQuery.insertQuery("user_list",
                    "user_list_seq.NEXTVAL,'" + userName + "','" + userPassword + "','" + email + "','" + active + "," + userCategory);
        }
        catch (NullPointerException ex) {
            logger.error(ex);
        }

        return result;

    }

    public static int PasswordUpdate(String userName, String newPassword) {

        int result = 0;

        try {
            result = DBStandartQuery.updateQuery("user_list",
                    "password ='" + newPassword + "'",
                    userName);
        }
        catch (NullPointerException ex) {
            logger.error(ex);
        }

        return result;

    }


}