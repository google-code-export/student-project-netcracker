package ua.netcrackerteam.DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 * @author
 */
public interface DAOInterface {
    public Collection GetNamesAndContacts() throws SQLException;
}
