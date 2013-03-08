package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.UserList;
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
    private List<UserList> usersNonBannedList = null;
    private List<UserList> usersBannedList = null;

    public void getUserDataNonBanned() throws IOException {
        try {
            List<UserList> usersNonBannedList = HibernateFactory.getInstance().getAdminDAO().getUsersNonBanned();
            this.setUsersNonBannedList(usersNonBannedList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUserDataBanned() throws IOException {
        try {
            List<UserList> usersNonBannedList = HibernateFactory.getInstance().getAdminDAO().getUsersBanned();
            this.setUsersBannedList(usersNonBannedList);
        } catch (SQLException e) {
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
}