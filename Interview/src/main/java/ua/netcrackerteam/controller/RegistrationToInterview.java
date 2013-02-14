/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.Form;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;

/**
 *
 * @author home
 */
public class RegistrationToInterview {
    
        public void updateRegistrationToInterview(String userName, Interview idInterview) {
         
        Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);       
        form.setInterview(idInterview);
        HibernateFactory.getInstance().getStudentDAO().updateForm(form);       
             
    }
}
