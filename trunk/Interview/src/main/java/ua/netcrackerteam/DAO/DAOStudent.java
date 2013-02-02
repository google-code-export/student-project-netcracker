package ua.netcrackerteam.DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Public interface DAOStudent - Entity of user Student.
 * Include methods to data transfer between StudentPerson type with DataBase.
 * @author krygin, maxym, Fillipenko
 */
public interface DAOStudent {
    public Collection GetNamesAndContacts() throws SQLException;
    public Form getFormById(Long idForm);
    public void addForm(Form form);
    public void updateFormById(Long idForm, Form form);
    public Collection getAllForms();
    public void deleteFormById(Long FormId);
    
}