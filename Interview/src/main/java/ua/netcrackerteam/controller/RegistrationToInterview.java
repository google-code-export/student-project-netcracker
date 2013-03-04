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
     
    //Anna changed this
    public static void updateRegistrationToInterview(String userName, int interviewId) {        
            
                Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);  
                //Interview interview = HibernateFactory.getInstance().getDAOInterview().getInterview(dateInterview);
                //form.setInterview(interview);
                HibernateFactory.getInstance().getStudentDAO().updateForm(form);             
    }
    
     //Anna changed this
    public static List<StudentInterview> getInterviews(){ 
       
       List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
       ListIterator iterator = interviews.listIterator();
       
       List<StudentInterview> listInterviews = new ArrayList();
       Interview interview = null;
       int restOfPositions = 10; //temp
       while(iterator.hasNext()){
            interview = (Interview)iterator.next();
            StudentInterview stInterview = new StudentInterview(interview.getIdInterview(),
                    interview.getStartDate(), interview.getEndDate(), restOfPositions);
            listInterviews.add(stInterview);
            //dataInterviews.add(HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview()).size());
       }
       return listInterviews;
    }
    
    //Anna changed this
    public static StudentInterview getInterview(String userName){
          Interview interview = (HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName)).getInterview();
          StudentInterview currentInterview = null;
          int restOfPositions = 10; //temp
          if (interview != null) {
              currentInterview = new StudentInterview(interview.getIdInterview(),
                    interview.getStartDate(), interview.getEndDate(), restOfPositions);
          }
          return currentInterview;
    }
    
     public static void main(String[] args){
         List list = getInterviews();
         ListIterator iterator = list.listIterator();
          while(iterator.hasNext()){
            System.out.println(iterator.next());         
       }
          
     }
    
}
