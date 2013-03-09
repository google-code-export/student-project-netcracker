package ua.netcrackerteam.DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author krygin
 */
public interface DAOAdmin {

    void banUserByName(String userName);

    void changeUserType(String userName, int newType);

    void resetOnNewLogin(String oldUserName, String newUserName);

    void resetOnNewPassword(String userName, String password);

    void activateUserByName(String userName);

    List getUsersBanned() throws SQLException;

    List getUsersNonBanned() throws SQLException;

    boolean checkUserBanStatus(String userName);

    String checkUserEmail(String userName);

    String checkUserCategory(String userName);
}
