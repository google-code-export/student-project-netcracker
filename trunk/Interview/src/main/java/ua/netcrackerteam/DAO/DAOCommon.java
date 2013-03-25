package ua.netcrackerteam.DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author krygin
 */
public interface DAOCommon {

    void resetOnNewEmail(String userName, String userEmail);

    List getUserByName(String userName) throws SQLException;

    void addSomethingNew(Object newData);

    void setUser(String userName,
                 String userPassword,
                 String userEmail,
                 String active,
                 int idUserCategory) throws SQLException;
}
