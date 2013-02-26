/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import ua.netcrackerteam.DAO.Form;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.Logable;

public class RegistrationToInterview implements  Logable{
    
    public static void updateRegistrationToInterview(String userName, Date dateInterview) {        
            
                Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);  
                Interview interview = HibernateFactory.getInstance().getDAOInterview().getInterview(dateInterview);
                form.setInterview(interview);
                HibernateFactory.getInstance().getStudentDAO().updateForm(form);             
    }
    
    
    public static List getInterviews(){ 
       
       List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
       ListIterator iterator = interviews.listIterator();
       
       List dataInterviews = new ArrayList(interviews.size()*3);
       Interview interview = null;
       
       while(iterator.hasNext()){
            interview = (Interview)iterator.next();
            dataInterviews.add(interview.getStartDate());
            dataInterviews.add(interview.getEndDate());
            dataInterviews.add(HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview()).size());
       }

       
       return dataInterviews;
        
    }

    
}
