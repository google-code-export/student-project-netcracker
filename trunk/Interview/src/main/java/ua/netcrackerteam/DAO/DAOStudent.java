package ua.netcrackerteam.DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Public interface DAOStudent - Entity of user Student.
 * Include methods to data transfer between StudentPersonImpl type with DataBase.
 * @author krygin, maxym, Fillipenko
 */
public interface DAOStudent {
    public Form getFormByUserId(Long idForm);
    public void addForm(Form form);
    public void updateForm(Form form);
    //public void signUpForInterview(Form form);
}