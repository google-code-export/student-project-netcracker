package ua.netcrackerteam.DAO;

import ua.netcrackerteam.configuration.InterviewLoggerSingleton;

import java.sql.*;
import java.util.Locale;

/**
 * Pattern Singelton. For creating connection to database and one access point in project
 * @author maksym
 */
public class DbConnectionSingleton
{
    protected static InterviewLoggerSingleton logger = InterviewLoggerSingleton.getInstance();
    private static DbConnectionSingleton uniqueInstance;
    private Connection conn;
    
    private DbConnectionSingleton() {
        try	{
            this.conn = getConnection();
        }
        catch (SQLException e)
        {
            while ( e != null)
            {
                System.out.println("" + e);
                e = e.getNextException();
                logger.error(e);
            }
        }
    }
    
    /**
     * @return singleton object
     */
    public static synchronized DbConnectionSingleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DbConnectionSingleton();
        }
        return uniqueInstance;
    }
    
    /**
     * Method for getting connection object once in constructor
     * @return Connection object
     * @throws SQLException 
     */
    private Connection getConnection() throws SQLException
    {
        String url="jdbc:oracle:thin:@85.238.104.112:1521:XE";
        String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        String username="netcracker";
        String password="oracle";
        try
        {
            Class.forName(jdbcDriver);
            Locale locale = Locale.getDefault();
            Locale.setDefault(Locale.ENGLISH);
            this.conn = DriverManager.getConnection(url, username, password);
            this.conn.setAutoCommit(true);
            System.out.println("Connection is OK!");
            logger.info();

            Locale.setDefault(locale);
        }
        catch(java.lang.ClassNotFoundException er)
        {
            System.out.print("JDBC-Driver is wrong!\n");
            logger.error(er);
            System.out.println(er.getMessage());
            er.printStackTrace();
            System.exit(0);
        }
        return this.conn;
    }
    
    /**
     * 
     * @return connection to database
     */
    public Connection getConn()
    {
        return this.conn;
    }
}