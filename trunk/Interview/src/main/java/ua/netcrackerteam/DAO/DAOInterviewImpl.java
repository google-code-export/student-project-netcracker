/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import ua.netcrackerteam.configuration.HibernateFactory;


/**
 *
 * @author home
 */
public class DAOInterviewImpl implements DAOInterview{
    
 
    public void updateRegistrationToInterview(Long idUser, Long idInterview) {
         
        Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserId(idUser);       
        form.setIdInterview(idInterview);
        HibernateFactory.getInstance().getStudentDAO().updateForm(form);       
             
    }

    
}
