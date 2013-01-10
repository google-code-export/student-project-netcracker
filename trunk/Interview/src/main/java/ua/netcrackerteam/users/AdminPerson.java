package ua.netcrackerteam.users;

/**
 * author tanya
 */
public class AdminPerson extends Persons implements AdminRights{

    @Override
    public void removeUser(String userName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addUser(String userName, String rights) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void changePasswordUser(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
