package ua.netcrackerteam.DAO;

import java.util.List;

/**
 * Public interface DAOStudent - Entity of user Student.
 * Include methods to data transfer between StudentData type with DataBase.
 * @author krygin, Zhokha Maksym, Fillipenko
 */
public interface DAOStudent {
    
    public Form getFormByUserId(int idForm);
    public Form getFormByUserName(String userName);
    //public void addForm(Form form);
    public void updateForm(Form form);
    public List<Form> getFormByInterview(Interview idInterview);
    public String getEmailByUserName(String userName);
}