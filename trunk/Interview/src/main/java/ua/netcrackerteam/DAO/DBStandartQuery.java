package ua.netcrackerteam.DAO;

import java.lang.*;
import java.sql.*;
import java.util.*;
import ua.netcrackerteam.DAO.*;

/**
 * Static class contains methods which perform standart database queries: Insert, Update, Select, Delete
 * @author krygin
 */
public class DBStandartQuery {

    /**
     * Method perform Select query to database using DbConnectionSingleton for connect to DB.
     * @param tableName - input table name
     * @param tableFields - input variable number of table fields
     * @return list - select result in collection ArrayList<String>()
     */
    public static ResultSet SelectQuery(String tableName, String ... tableFields){
        List<String> list = null;
        Connection con = null;
        String query = null;
        String newString = null;
        Statement stm = null;
        ResultSet rz = null;
        int k = 1;
        try
        {
            newString = tableFields[0];
            while (newString.indexOf(",") != -1)  {
                newString = newString.substring(newString.indexOf(",")+1, newString.length());
                k++;
            }
            query = "select " + tableFields[0] + " from " + tableName;
            list = new ArrayList<String>();
            con = DbConnectionSingleton.getInstance().getConn();
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rz = stm.executeQuery(query);
        } catch (SQLException er) {
            System.out.println("SELECT is wrong!");
            System.out.println(er.getMessage());
        } catch (IndexOutOfBoundsException er){
            er.printStackTrace();
        }
        return rz;
    }
}
