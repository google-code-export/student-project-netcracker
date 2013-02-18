/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

import java.util.Date;
import java.util.List;
import ua.netcrackerteam.DAO.Form;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;

public class RegistrationToInterview {
    
    public void updateRegistrationToInterview(String userName, Date dateInterview) {        
            
                Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);  
                Interview interview = HibernateFactory.getInstance().getDAOInterview().getInterview(dateInterview);
                form.setInterview(interview);
                HibernateFactory.getInstance().getStudentDAO().updateForm(form);              
             
    }
    
    
    public List<Interview> getInterviews(){ 
        
       return HibernateFactory.getInstance().getDAOInterview().getInterview();
       
    }
    
    
}
