package ua.netcrackerteam.DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 24.01.13
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
public interface NewDAOForm {
    public Collection GetNamesAndContacts() throws SQLException;
}
