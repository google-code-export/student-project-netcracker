package ua.netcrackerteam.DAO;

/**
 * Public interface DAOStudent - Entity of user Student.
 * Include methods to data transfer between StudentData type with DataBase.
 * @author krygin, Zhokha Maksym, Fillipenko
 */
public interface DAOStudent {
    public Form getFormByUserId(int idForm);
    public Form getFormByUserName(String userName);
    public void addForm(Form form);
    public void updateForm(Form form);
    
}