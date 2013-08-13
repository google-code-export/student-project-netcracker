package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.Entities.AuditInterview;
import ua.netcrackerteam.DAO.Entities.UserList;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.Logable;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * @author krygin
 */
public class UsersData implements Serializable, Logable {
    private static final long serialVersionUID = -1212126055681181451L;

    private List<UserList> usersNonBannedList = null;
    private List<UserList> usersBannedList = null;
    private List<UserList> userListFilteredNonBanned = null;
    private List<UserList> userListSearchNonBannedNonFiltered = null;
    private List<UserList> userListSearchNonBannedFiltered = null;
    private List<AuditInterview> auditInterviews = null;

    public void setAuditInterviews(){
        List<AuditInterview> auditInterviews = HibernateFactory.getInstance().getAdminDAO().getAuditInterview();
        this.setAuditInterviews(auditInterviews);
    }

    public void setUsersListSearchNonFilteredNonBanned(String searchString) {
        List<UserList> userListNonFilteredNonBanned = HibernateFactory.getInstance().getAdminDAO().getUsersSearchResultNonBanned(searchString);
        this.setUserListSearchNonBannedNonFiltered(userListNonFilteredNonBanned);
    }

    public void setUsersListSearchFilteredNonBanned(String searchString, String userCategoryString){
        try{
            if(userCategoryString.toUpperCase().equals("Admin".toUpperCase())){
                List<UserList> userListSearchFilteredNonBanned = HibernateFactory.getInstance().getAdminDAO().getUsersSearchResultByCategoryNonBanned(searchString, 1);
                this.setUserListSearchNonBannedFiltered(userListSearchFilteredNonBanned);
            } else if(userCategoryString.toUpperCase().equals("HR".toUpperCase())){
                List<UserList> userListSearchFilteredNonBanned = HibernateFactory.getInstance().getAdminDAO().getUsersSearchResultByCategoryNonBanned(searchString, 2);
                this.setUserListSearchNonBannedFiltered(userListSearchFilteredNonBanned);
            } else if(userCategoryString.toUpperCase().equals("Interviewer".toUpperCase())){
                List<UserList> userListSearchFilteredNonBanned = HibernateFactory.getInstance().getAdminDAO().getUsersSearchResultByCategoryNonBanned(searchString, 3);
                this.setUserListSearchNonBannedFiltered(userListSearchFilteredNonBanned);
            } else if(userCategoryString.toUpperCase().equals("Student".toUpperCase())){
                List<UserList> userListSearchFilteredNonBanned = HibernateFactory.getInstance().getAdminDAO().getUsersSearchResultByCategoryNonBanned(searchString, 4);
                this.setUserListSearchNonBannedFiltered(userListSearchFilteredNonBanned);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUserListFilteredNonBanned(String userCategoryString){
        try {
        if(userCategoryString.toUpperCase().equals("Admin".toUpperCase())){
            List<UserList> userListFilteredNonBanned = HibernateFactory.getInstance().getAdminDAO().getUsersFiltered(1);
            this.setUserListFiltered(userListFilteredNonBanned);
        } else if(userCategoryString.toUpperCase().equals("HR".toUpperCase())){
            List<UserList> userListFilteredNonBanned = HibernateFactory.getInstance().getAdminDAO().getUsersFiltered(2);
            this.setUserListFiltered(userListFilteredNonBanned);
        } else if(userCategoryString.toUpperCase().equals("Interviewer".toUpperCase())){
            List<UserList> userListFilteredNonBanned = HibernateFactory.getInstance().getAdminDAO().getUsersFiltered(3);
            this.setUserListFiltered(userListFilteredNonBanned);
        } else if(userCategoryString.toUpperCase().equals("Student".toUpperCase())){
            List<UserList> userListFilteredNonBanned = HibernateFactory.getInstance().getAdminDAO().getUsersFiltered(4);
            this.setUserListFiltered(userListFilteredNonBanned);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserDataNonBanned() throws IOException {
        try {
            List<UserList> usersNonBannedList = HibernateFactory.getInstance().getAdminDAO().getUsersNonBanned();
            this.setUsersNonBannedList(usersNonBannedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUserDataBanned() throws IOException {
        try {
            List<UserList> usersNonBannedList = HibernateFactory.getInstance().getAdminDAO().getUsersBanned();
            this.setUsersBannedList(usersNonBannedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserList> getUsersNonBannedList() {
        return usersNonBannedList;
    }

    public void setUsersNonBannedList(List<UserList> usersNonBannedList) {
        this.usersNonBannedList = usersNonBannedList;
    }

    public List<UserList> getUsersBannedList() {
        return usersBannedList;
    }

    public void setUsersBannedList(List<UserList> usersBannedList) {
        this.usersBannedList = usersBannedList;
    }

    public List<UserList> getUserListFilteredNonBanned() {
        return userListFilteredNonBanned;
    }

    public void setUserListFiltered(List<UserList> userListFiltered) {
        this.userListFilteredNonBanned = userListFiltered;
    }

    public List<UserList> getUserListSearchNonBannedNonFiltered() {
        return userListSearchNonBannedNonFiltered;
    }

    public void setUserListSearchNonBannedNonFiltered(List<UserList> userListSearchNonBannedNonFiltered) {
        this.userListSearchNonBannedNonFiltered = userListSearchNonBannedNonFiltered;
    }

    public List<UserList> getUserListSearchNonBannedFiltered() {
        return userListSearchNonBannedFiltered;
    }

    public void setUserListSearchNonBannedFiltered(List<UserList> userListSearchNonBannedFiltered) {
        this.userListSearchNonBannedFiltered = userListSearchNonBannedFiltered;
    }

    public List<AuditInterview> getAuditInterviews() {
        return auditInterviews;
    }

    public void setAuditInterviews(List<AuditInterview> auditInterviews) {
        this.auditInterviews = auditInterviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersData usersData = (UsersData) o;
        if (userListFilteredNonBanned != null ? !userListFilteredNonBanned.equals(usersData.userListFilteredNonBanned) : usersData.userListFilteredNonBanned != null)
            return false;
        if (userListSearchNonBannedFiltered != null ? !userListSearchNonBannedFiltered.equals(usersData.userListSearchNonBannedFiltered) : usersData.userListSearchNonBannedFiltered != null)
            return false;
        if (userListSearchNonBannedNonFiltered != null ? !userListSearchNonBannedNonFiltered.equals(usersData.userListSearchNonBannedNonFiltered) : usersData.userListSearchNonBannedNonFiltered != null)
            return false;
        if (usersBannedList != null ? !usersBannedList.equals(usersData.usersBannedList) : usersData.usersBannedList != null)
            return false;
        if (usersNonBannedList != null ? !usersNonBannedList.equals(usersData.usersNonBannedList) : usersData.usersNonBannedList != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = usersNonBannedList != null ? usersNonBannedList.hashCode() : 0;
        result = 31 * result + (usersBannedList != null ? usersBannedList.hashCode() : 0);
        result = 31 * result + (userListFilteredNonBanned != null ? userListFilteredNonBanned.hashCode() : 0);
        result = 31 * result + (userListSearchNonBannedNonFiltered != null ? userListSearchNonBannedNonFiltered.hashCode() : 0);
        result = 31 * result + (userListSearchNonBannedFiltered != null ? userListSearchNonBannedFiltered.hashCode() : 0);
        return result;
    }
}