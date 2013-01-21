package ua.netcrackerteam.users;

/**
 */
public class InterviewerPerson implements InterviewerRights{
    @Override
    public void login(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(String userName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean changePassword(String exsistPassword, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
