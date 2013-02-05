package ua.netcrackerteam.DAO;

/**
 * Public interface DAOStudent - Entity of user Student.
 * Include methods to data transfer between StudentPerson type with DataBase.
 * @author krygin, maxym, Fillipenko
 */
public interface DAOStudent {
    public Form getFormByUserId(Long idForm);
    public void addForm(Form form);
    public void updateForm(Form form);
    
}