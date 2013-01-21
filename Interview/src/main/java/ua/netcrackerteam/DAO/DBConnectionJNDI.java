/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import ua.netcrackerteam.configuration.InterviewLoggerSingleton;

/**
 *
 * @author maxym correct tanya
 */
public class DBConnectionJNDI {
    protected static InterviewLoggerSingleton logger = InterviewLoggerSingleton.getInstance();
    private static DBConnectionJNDI uniqueInstance;
    private Connection connection;
    
    private DBConnectionJNDI() {
        try	{
            connection = getConnection();
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
    public static synchronized DBConnectionJNDI getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DBConnectionJNDI();
        }
        return uniqueInstance;
    }
    
    /**
     * Method for getting connection object once in constructor
     * @return Connection object
     * @throws SQLException 
     */
    private Connection getConnection() throws SQLException{
    
        Context initContext = null;
        Context envContext = null;
        
        Connection con = null;
        try{            
                initContext = new InitialContext();
                envContext  = (Context)initContext.lookup("java:/comp/env");
                DataSource ds = (DataSource)envContext.lookup("jdbc/DBOracle");
                con= ds.getConnection();        
        } 
        catch (NamingException ex) {
           Logger.getLogger(DBConnectionJNDI.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                initContext.close();
            } catch (NamingException ex) {
                Logger.getLogger(DBConnectionJNDI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return con;
    }
    
    /**
     * 
     * @return connection to database
     */
    public Connection getConn()
    {
        return connection;
    }
}
