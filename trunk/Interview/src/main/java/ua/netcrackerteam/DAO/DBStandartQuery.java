package ua.netcrackerteam.DAO;

import ua.netcrackerteam.configuration.InterviewLoggerSingleton;
import ua.netcrackerteam.configuration.Logable;
import java.lang.*;
import java.sql.*;
import java.util.*;

/**
 * Static class contains methods which perform standard database queries: Insert, Update, Select, Delete
 * Use class DbConnectionSingleton for get connections to DB.
 * @author krygin, maxym, Filipenko
 */
public class DBStandartQuery implements Logable {

    /**
     * Method perform Select query to database using DbConnectionSingleton for connect to DB.
     * "SELECT fields FROM tables WHERE condition"
     * @param tableName - input variable number of table names.
     * @param tableFields - input variable number of table fields.
     * @param whereCondition - input variable number of where conditions in query.
     * @return list - query result in collection ArrayList
     */
    public static List SelectQuery(String tableFields, String tableName, String whereCondition){
        int countColumns = 0;
        List list = null;
        Connection con = null;
        String query = null;
        Statement stm = null;
        ResultSet rz = null;
        try
        {
            list = new ArrayList();
            query = "select " + tableFields + " from " + tableName + " where " + whereCondition;
            con = DbConnectionSingleton.getInstance().getConn();
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rz = stm.executeQuery(query);
            if (rz != null) {
                countColumns = rz.getMetaData().getColumnCount();
                while (rz.next()) {
                    for (int i = 1; i <= countColumns; i++)
                        list.add(rz.getString(i));
                }
            }
            logger.info();
        } catch (SQLException er) {
            logger.error(er);
            System.out.println("SELECT is wrong!");
            System.out.println(er.getMessage());
        } catch (IndexOutOfBoundsException er){
            er.printStackTrace();
            logger.error(er);
        }
        return list;
    }

    public static List SelectQuery(String tableFields, String tableName){
        InterviewLoggerSingleton.getInstance().info();
        return SelectQuery(tableFields, tableName, "");
    }
}
