package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.Logable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author krygin
 */
public class UsersData implements Logable{
    private List<UserList> userList = null;

    public List<UserList> getUserList() {
        return userList;
    }

    public void setUserList(List<UserList> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersData usersData = (UsersData) o;

        if (userList != null ? !userList.equals(usersData.userList) : usersData.userList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userList != null ? userList.hashCode() : 0;
    }

    public void setUserData() throws IOException {
        try {
            List<UserList> userList = HibernateFactory.getInstance().getCommonDao().getUser();
            this.setUserList(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}