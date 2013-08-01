package ua.netcrackerteam.DAO;

import ua.netcrackerteam.DAO.Entities.AuditInterview;
import ua.netcrackerteam.DAO.Entities.UserCategory;
import ua.netcrackerteam.DAO.Entities.UserList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krygin
 */
public class DAOAdminImpl extends DAOCoreObject implements DAOAdmin{

    PreparedStatement pstmt;

    @Override
    public void activeChangeUserByName(String userName, String active) {
        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(userName);
        String activateUserByNameQuery = "from UserList where userName = :param0";
        UserList userList = super.<UserList>executeSingleGetQuery(activateUserByNameQuery, listOfParam);
        userList.setActive(active);
        saveUpdatedObject(userList);
        commitTransaction();
    }

    @Override
    public boolean checkUserAvailability(String userName){
        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(userName);
        String checkUserAvailabilityQuery = "from UserList where userName = :param0";
        List<UserList> userList = super.<UserList>executeListGetQuery(checkUserAvailabilityQuery, listOfParam);
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
        List listOfParam = new ArrayList();
        listOfParam.add(userName);
        String activity = "banned";
        String checkUserBanStatusQuery = "from UserList where userName = :param0";
        UserList userList = executeSingleGetQuery(checkUserBanStatusQuery, listOfParam);
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
        List listOfParam = new ArrayList();
        listOfParam.add(userName);
        String resetOnNewPasswordQuery = "from UserList where userName = :param0";
        UserList userList = super.<UserList>executeSingleGetQuery(resetOnNewPasswordQuery, listOfParam);
        userList.setPassword(password);
        saveUpdatedObject(userList);
        commitTransaction();
    }

    @Override
    public void resetOnNewLogin(String oldUserName, String newUserName){
        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(oldUserName);
        String resetOnNewLoginQuery = "from UserList where userName = :param0";
        UserList userList = super.<UserList>executeSingleGetQuery(resetOnNewLoginQuery, listOfParam);
        userList.setUserName(newUserName);
        saveUpdatedObject(userList);
        commitTransaction();
    }

    @Override
    public void changeUserType(String userName, int newType){
        beginTransaction();
        List listOfParam1 = new ArrayList();
        listOfParam1.add(newType);
        String userCategoryQuery = "from UserCategory where to_char(idUserCategory)  = to_char(:param0)";
        UserCategory userTypes = super.<UserCategory>executeSingleGetQuery(userCategoryQuery, listOfParam1);
        List listOfParam2 = new ArrayList();
        listOfParam2.add(userName);
        String changeUserTypeQyery = "from UserList where userName = :param0";
        UserList userList = super.<UserList>executeSingleGetQuery(changeUserTypeQyery, listOfParam2);
        userList.setIdUserCategory(userTypes);
        saveUpdatedObject(userList);
        commitTransaction();
    }

    @Override
    public String checkUserEmail(String userName){
        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(userName);
        String checkUserEmailQuery = "from UserList where userName = :param0";
        UserList userList = super.<UserList>executeSingleGetQuery(checkUserEmailQuery, listOfParam);
        String userEmail = userList.getEmail();
        commitTransaction();
        return userEmail;
    }

    @Override
    public String checkUserCategory(String userName){
        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(userName);
        String checkUserCategoryQuery = "from UserList where userName = :param0";
        UserList userList = super.<UserList>executeSingleGetQuery(checkUserCategoryQuery, listOfParam);
        String userCategory = userList.getIdUserCategory().getName();
        commitTransaction();
        return userCategory;
    }

    @Override
    public List getUsersFiltered(int userCategory) throws SQLException {
        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(userCategory);
        String getUsersFilteredQuery = "from UserList where to_char(idUserCategory) = to_char(:param0)";
        List<UserList> listOfUsers = super.<UserList>executeListGetQuery(getUsersFilteredQuery, listOfParam);
        commitTransaction();
        return listOfUsers;
    }

    @Override
    public List getUsersSearchResultNonBanned(String searchString){
        beginTransaction();
        List listOfParams = new ArrayList();
        searchString = '%'+searchString+'%';
        listOfParams.add(searchString);
        String getUsersSearchResultNonBannedQuery = "from UserList where active = 'active' and " +
                                    "(upper(userName) like upper(:param0) or " +
                                    "to_char(idUser) like to_char(:param0) or upper(email) like upper(:param0))";

        List<UserList> listOfUsers = super.<UserList>executeListGetQuery(getUsersSearchResultNonBannedQuery, listOfParams);
        commitTransaction();
        return listOfUsers;
    }

    @Override
    public List getUsersSearchResultByCategoryNonBanned(String searchString, int userCategory){
        beginTransaction();
        List listOfParams = new ArrayList();
        listOfParams.add(userCategory);
        searchString = '%'+searchString+'%';
        listOfParams.add(searchString);
        String getUsersSearchResultByCategoryNonBannedQuery = "from UserList where to_char(idUserCategory) = to_char(:param0) and active = 'active' and " +
                                                "(upper(userName) like upper(:param1) or " +
                                                "to_char(idUser) like to_char(:param1) or upper(email) like upper(:param1))";
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
        List listOfParam = new ArrayList();
        listOfParam.add(userName);
        String getUserCategoryIDByUserNameQuery = "from UserList where userName =:param0";
        UserList userList = super.<UserList>executeSingleGetQuery(getUserCategoryIDByUserNameQuery, listOfParam);
        int userCategory = userList.getIdUserCategory().getIdUserCategory();
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
        List listOfParam = new ArrayList();
        listOfParam.add(id);
        String getCountRegisteredUsersByUserCategoryQuery = "select count(idUser) from UserList where to_char(idUserCategory) = to_char(:param0)";
        Long count = super.<Long>executeSingleGetQuery(getCountRegisteredUsersByUserCategoryQuery, listOfParam);
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
        List listOfParam = new ArrayList();
        listOfParam.add(status);
        String getCountUsersByActivityQuery = "select count(idUser) from UserList where active = :param0";
        Long count = super.<Long>executeSingleGetQuery(getCountUsersByActivityQuery, listOfParam);
        commitTransaction();
        return count;
    }

    @Override
    public UserCategory getUserCategoryByUserName(String userName) {
        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(userName);
        String getUserCategoryByUserNameQuery = "from UserList where userName =:param0";
        UserList userList = super.<UserList>executeSingleGetQuery(getUserCategoryByUserNameQuery, listOfParam);
        UserCategory userCategory = userList.getIdUserCategory();
        commitTransaction();
        return userCategory;
    }

    @Override
    public void deleteUserByName(String userName) {
        beginTransaction();
        List listOfParam = new ArrayList();
        listOfParam.add(userName);
        String deleteUserByNameQuery = "delete from UserList where userName =:param0";
        executeDeleteQuery(deleteUserByNameQuery, listOfParam);
        commitTransaction();
    }
}
