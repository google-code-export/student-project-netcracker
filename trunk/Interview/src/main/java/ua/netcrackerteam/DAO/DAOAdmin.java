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

    List getUsersFiltered(int userCategory) throws SQLException;

    List getUsersSearchResultNonBanned(String searchString);

    List getUsersSearchResultByCategoryNonBanned(String searchString, int userCategory);

    boolean checkUserAvailability(String userName);

    List getAuditInterview();

    int getUserCategoryIDByUserName(String userName);

    Long getCountTotalActions();

    Long getCountRegisteredUsersTotal();

    Long getCountRegisteredUsersByUserCategory(int id);

    Long getCountUsersLoginTriedAll();

    Long getCountUsersLoginTriedNonRegUsers();

    Long getCountUsersByActivity(String status);
}
