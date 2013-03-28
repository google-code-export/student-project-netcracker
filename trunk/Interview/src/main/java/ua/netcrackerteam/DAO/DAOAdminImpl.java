package ua.netcrackerteam.DAO;

import ua.netcrackerteam.DAO.Entities.AuditInterview;
import ua.netcrackerteam.DAO.Entities.UserCategory;
import ua.netcrackerteam.DAO.Entities.UserList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author krygin
 */
public class DAOAdminImpl extends DAOCoreObject implements DAOAdmin{

    PreparedStatement pstmt;

    @Override
    public void activeChangeUserByName(String userName, String active) {
        beginTransaction();
        String activateUserByNameQuery = "from UserList where upper(userName) = :userNameParam";
        UserList userList = super.<UserList>executeSingleGetQuery1(activateUserByNameQuery, userName);
        userList.setActive(active);
        saveUpdatedObject(userList);
        commitTransaction();
    }

    @Override
    public boolean checkUserAvailability(String userName){
        beginTransaction();
        String checkUserAvailabilityQuery = "from UserList where upper(userName) ='" + userName.toUpperCase() + "'";
        List<UserList> userList = super.<UserList>executeListGetQuery(checkUserAvailabilityQuery);
        commitTransaction();
        if (userList.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean checkUserBanStatus(String userName){
        beginTransaction();
        String activity = "banned";
        String checkUserBanStatusQuery = "from UserList where upper(userName) ='" + userName.toUpperCase() + "'";
        UserList userList = executeSingleGetQuery(checkUserBanStatusQuery);
        commitTransaction();
        if (userList.getActive().equals(activity)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List getUsersNonBanned(){
        beginTransaction();
        String getUsersNonBannedQuery = "from UserList where active != 'banned'";
        List<UserList> listOfUsers = super.<UserList>executeListGetQuery(getUsersNonBannedQuery);
        commitTransaction();
        return listOfUsers;
    }

    @Override
    public List getUsersBanned(){
        beginTransaction();
        String getUsersBannedQuery = "from UserList where active = 'banned'";
        List<UserList> listOfUsers = super.<UserList>executeListGetQuery(getUsersBannedQuery);
        commitTransaction();
        return listOfUsers;
    }

    @Override
    public void resetOnNewPassword(String userName, String password){
        beginTransaction();
        String resetOnNewPasswordQuery = "from UserList where upper(userName) ='" + userName.toUpperCase() + "'";
        UserList userList = super.<UserList>executeSingleGetQuery(resetOnNewPasswordQuery);
        userList.setPassword(password);
        saveUpdatedObject(userList);
        commitTransaction();
    }

    @Override
    public void resetOnNewLogin(String oldUserName, String newUserName){
        beginTransaction();
        String resetOnNewLoginQuery = "from UserList where upper(userName) ='" + oldUserName.toUpperCase() + "'";
        UserList userList = super.<UserList>executeSingleGetQuery(resetOnNewLoginQuery);
        userList.setUserName(newUserName);
        saveUpdatedObject(userList);
        commitTransaction();
    }

    @Override
    public void changeUserType(String userName, int newType){
        beginTransaction();
        String userCategoryQuery = "from UserCategory where idUSerCategory = " + newType;
        UserCategory userTypes = super.<UserCategory>executeSingleGetQuery(userCategoryQuery);
        String changeUserTypeQyery = "from UserList where upper(userName) ='" + userName.toUpperCase() + "'";
        UserList userList = super.<UserList>executeSingleGetQuery(changeUserTypeQyery);
        userList.setIdUserCategory(userTypes);
        saveUpdatedObject(userList);
        commitTransaction();
    }

    @Override
    public String checkUserEmail(String userName){
        beginTransaction();
        String checkUserEmailQuery = "from UserList where upper(userName) ='" + userName.toUpperCase() + "'";
        UserList userList = super.<UserList>executeSingleGetQuery(checkUserEmailQuery);
        String userEmail = userList.getEmail();
        commitTransaction();
        return userEmail;
    }

    @Override
    public String checkUserCategory(String userName){
        beginTransaction();
        String checkUserCategoryQuery = "from UserList where upper(userName) ='" + userName.toUpperCase() + "'";
        UserList userList = super.<UserList>executeSingleGetQuery(checkUserCategoryQuery);
        String userCategory = userList.getIdUserCategory().getName();
        commitTransaction();
        return userCategory;
    }

    @Override
    public List getUsersFiltered(int userCategory) throws SQLException {
        beginTransaction();
        String getUsersFilteredQuery = "from UserList where idUserCategory = " + userCategory;
        List<UserList> listOfUsers = super.<UserList>executeListGetQuery(getUsersFilteredQuery);
        commitTransaction();
        return listOfUsers;
    }

    @Override
    public List getUsersSearchResultNonBanned(String searchString){
        beginTransaction();
        String getUsersSearchResultNonBannedQuery = "from UserList where active = 'active' and " +
                                    "(upper(userName) like upper('%" + searchString + "%') or " +
                                    "idUser like '%" + searchString + "%' or upper(email) like upper('%" + searchString + "%'))";
        List<UserList> listOfUsers = super.<UserList>executeListGetQuery(getUsersSearchResultNonBannedQuery);
        commitTransaction();
        return listOfUsers;
    }

    @Override
    public List getUsersSearchResultByCategoryNonBanned(String searchString, int userCategory){
        beginTransaction();
        String getUsersSearchResultByCategoryNonBannedQuery = "from UserList where idUserCategory = " + userCategory + " and active = 'active' and " +
                                                "(upper(userName) like upper('%" + searchString + "%') or " +
                                                "idUser like '%" + searchString + "%' or upper(email) like upper('%" + searchString + "%'))";
        List<UserList> listOfUsers = super.<UserList>executeListGetQuery(getUsersSearchResultByCategoryNonBannedQuery);
        commitTransaction();
        return listOfUsers;
    }

    public void addAudit(AuditInterview auditInterview){
        beginTransaction();
        super.<AuditInterview>saveUpdatedObject(auditInterview);
        commitTransaction();
    }

    @Override
    public List getAuditInterview(){
        beginTransaction();
        String getAuditInterviewQuery = "from AuditInterview where userName is not null order by idAudit desc";
        List<AuditInterview> auditInterviews = super.<AuditInterview>executeListGetQuery(getAuditInterviewQuery);
        commitTransaction();
        return auditInterviews;
    }

    @Override
    public int getUserCategoryIDByUserName(String userName){
        beginTransaction();
        String getUserCategoryIDByUserNameQuery = "from UserList where upper(userName) ='" + userName.toUpperCase() + "'";
        UserList userList = super.<UserList>executeSingleGetQuery(getUserCategoryIDByUserNameQuery);
        int userCategory = userList.getIdUserCategory().getIdUSerCategory();
        commitTransaction();
        return userCategory;
    }

    @Override
    public Long getCountTotalActions(){
        beginTransaction();
        String getCountTotalActionsQuery = "select count(idAudit) from AuditInterview";
        Long count = super.<Long>executeSingleGetQuery(getCountTotalActionsQuery);
        commitTransaction();
        return count;
    }

    @Override
    public Long getCountRegisteredUsersTotal(){
        beginTransaction();
        String getCountRegisteredUsersTotalQuery = "select count(idUser) from UserList";
        Long count = super.<Long>executeSingleGetQuery(getCountRegisteredUsersTotalQuery);
        commitTransaction();
        return count;
    }

    @Override
    public Long getCountRegisteredUsersByUserCategory(int id){
        beginTransaction();
        String getCountRegisteredUsersByUserCategoryQuery = "select count(idUser) from UserList where idUserCategory = " + id;
        Long count = super.<Long>executeSingleGetQuery(getCountRegisteredUsersByUserCategoryQuery);
        commitTransaction();
        return count;
    }

    @Override
    public Long getCountUsersLoginTriedAll(){
        beginTransaction();
        String getCountUsersLoginTriedAllQuery = "select count(idAudit) from AuditInterview where actionCategories = 1";
        Long count = super.<Long>executeSingleGetQuery(getCountUsersLoginTriedAllQuery);
        commitTransaction();
        return count;
    }

    @Override
    public Long getCountUsersLoginTriedNonRegUsers(){
        beginTransaction();
        String getCountUsersLoginTriedNonRegUsersQuery = "select count(idAudit) from AuditInterview where actionCategories = 1 and idUserCategory = 5";
        Long count = super.<Long>executeSingleGetQuery(getCountUsersLoginTriedNonRegUsersQuery);
        commitTransaction();
        return count;
    }

    @Override
    public Long getCountUsersByActivity(String status){
        beginTransaction();
        String getCountUsersByActivityQuery = "select count(idUser) from UserList where active = '" + status + "'";
        Long count = super.<Long>executeSingleGetQuery(getCountUsersByActivityQuery);
        commitTransaction();
        return count;
    }

    @Override
    public UserCategory getUserCategoryByUserName(String userName) {
        beginTransaction();
        String getUserCategoryByUserNameQuery = "from UserList where upper(userName) ='" + userName.toUpperCase() + "'";
        UserList userList = super.<UserList>executeSingleGetQuery(getUserCategoryByUserNameQuery);
        UserCategory userCategory = userList.getIdUserCategory();
        commitTransaction();
        return userCategory;
    }

    @Override
    public void deleteUserByName(String userName) {
        beginTransaction();
        String deleteUserByNameQuery = "delete from UserList where userName = '" + userName + "'";
        executeDeleteQuery(deleteUserByNameQuery);
        commitTransaction();
    }
}
