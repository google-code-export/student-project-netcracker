package ua.netcrackerteam.DAO;

import ua.netcrackerteam.DAO.Entities.UserCategory;
import ua.netcrackerteam.DAO.Entities.UserList;

import java.sql.SQLException;
import java.util.List;

/**
 * @author krygin
 */
public interface DAOAdmin {

    void activeChangeUserByName(String userName, String active);

    void changeUserType(String userName, int newType);

    void resetOnNewLogin(String oldUserName, String newUserName);

    void resetOnNewPassword(String userName, String password);

    List getUsersBanned() throws SQLException;

    List getUsersNonBanned() throws SQLException;

    boolean checkUserBanStatus(String userName);

    String checkUserEmail(String userName);

    String checkUserCategory(String userName);

    List getUsersFiltered(int userCategory) throws SQLException;

    List getUsersSearchResultNonBanned(String searchString);

    List getUsersSearchResultByCategoryNonBanned(String searchString, int userCategory);

    List getAuditInterview();

    int getUserCategoryIDByUserName(String userName);

    Long getCountTotalActions();

    Long getCountRegisteredUsersTotal();

    Long getCountRegisteredUsersByUserCategory(int id);

    Long getCountUsersLoginTriedAll();

    Long getCountUsersLoginTriedNonRegUsers();

    Long getCountUsersByActivity(String status);

    UserCategory getUserCategoryByUserName(String userName);

    void deleteUserByName(String userName);

    boolean checkUserAvailability(String userName, String userPassword) throws SQLException;

    boolean checkUserAvailability(String userName) throws SQLException;
    
    UserList getUser(int userId);
}
