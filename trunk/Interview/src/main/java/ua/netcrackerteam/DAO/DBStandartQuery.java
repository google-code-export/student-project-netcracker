package ua.netcrackerteam.DAO;

import java.lang.*;
import java.sql.*;

/**
 * Static class contains methods which perform standard database queries: Insert, Update, Select, Delete
 * Use class DbConnectionSingleton for get connections to DB.
 * @author krygin, maxym, Filipenko
 */
public class DBStandartQuery {

    /**
     * Method perform Select query to database using DbConnectionSingleton for connect to DB.
     * @param tableName - input variable number of table names.
     * @param tableFields - input variable number of table fields.
     * @param whereCondition - input variable number of where conditions in query.
     * @return rz - resultset for using in another classes for get list of values of query.
     */
    public static ResultSet SelectQuery(String tableFields, String tableName, String whereCondition){
        Connection con = null;
        String query = null;
        Statement stm = null;
        ResultSet rz = null;
        try
        {
            query = "select " + tableFields + " from " + tableName + " where " + whereCondition;
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
