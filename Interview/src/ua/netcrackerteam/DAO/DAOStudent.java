package ua.netcrackerteam.DAO;

import ua.netcrackerteam.DAO.Entities.Form;

import java.util.List;

/**
 * Public interface DAOStudent - Entity of user Student.
 * Include methods to data transfer between StudentData type with DataBase.
 * @author krygin, Zhokha Maksym, Fillipenko
 */
public interface DAOStudent {    
    
    public Form getFormByUserName(String userName);
    public void addForm(Form form);
    public void updateForm(Form form);
    public List<Form> getFormsByInterviewId(int idInterview);
    public String getEmailByUserName(String userName);
}