package ua.netcrackerteam.DAO;

import ua.netcrackerteam.configuration.ShowHibernateSQLInterceptor;
import javax.interceptor.Interceptors;
import java.sql.SQLException;
import java.util.List;

/**
 * @author krygin
 */
public interface DAOCommon {

    void resetOnNewEmail(String userName, String userEmail);

    @Interceptors(ShowHibernateSQLInterceptor.class)
    List getUserByName(String userName) throws SQLException;

    @Interceptors(ShowHibernateSQLInterceptor.class)
    void addSomethingNew(Object newData);

    @Interceptors(ShowHibernateSQLInterceptor.class)
    void deleteUserByName(String userName);

    @Interceptors(ShowHibernateSQLInterceptor.class)
    void setUser(String userName,
                 String userPassword,
                 String userEmail,
                 String active,
                 int idUserCategory) throws SQLException;
}
