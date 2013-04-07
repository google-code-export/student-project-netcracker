/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.Entities.Form;
import ua.netcrackerteam.DAO.Entities.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.Logable;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author Klitna Tetiana
 */

public class RegistrationToInterview implements  Logable{
     
    /**
     * Registration form's student to the interview
     * @param userName - login student
     * @param interviewId - selected interview by student
     */
    public void updateRegistrationToInterview(String userName, int interviewId) {        
            Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);
            if (form!=null) {
                Interview interview = HibernateFactory.getInstance().getDAOInterview().getInterview(interviewId);
                form.setInterview(interview);
                HibernateFactory.getInstance().getStudentDAO().updateForm(form);
            }
    }
    
    /**
     * Get all interviews for view to the GUI
     * @return List<StudentInterview>
     */
    public List<StudentInterview> getInterviews(){ 
       
       List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
       ListIterator iterator = interviews.listIterator();
       
       List<StudentInterview> listInterviews = new ArrayList();
       Interview interview = null;
             
       while(iterator.hasNext()){
            interview = (Interview)iterator.next();
            if(interview.getReserve()!=1) {
                List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
                int  amountStudentsToInterview = (forms == null? 0: forms.size()); 
                StudentInterview stInterview = new StudentInterview(interview.getIdInterview(),
                        interview.getStartDate(), interview.getEndDate(), interview.getMaxNumber() - amountStudentsToInterview);
                listInterviews.add(stInterview);
            }
       }
       return listInterviews;
    }
    
    /** 
     * Get interview student by the student login
     * @param userName
     * @return 
     */
    public int getInterviewID(String userName){
          Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);
          Interview interview = null;
          if (form != null) {
            interview = form.getInterview();
          }
          int currentInterviewID = -1;
          if (interview != null) {
              currentInterviewID = interview.getIdInterview();
          }
          return currentInterviewID;
    }

    public Interview getInterview(String userName){
        Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);
        Interview interview = null;
        if (form != null) {
            interview = form.getInterview();
        }
        return interview;
    }
    
    public StudentInterview getNullInterview() {
        Interview nullInterview = HibernateFactory.getInstance().getDAOInterview().getReserveInterview();
        List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(nullInterview.getIdInterview());
        int  amountStudentsToInterview = (forms == null? 0: forms.size()); 
        StudentInterview stInterview = new StudentInterview(nullInterview.getIdInterview(),nullInterview.getMaxNumber() - amountStudentsToInterview);
        return stInterview;
    }
    
    
    
}
