/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.DAO.Entities.Interview;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.List;
import java.util.Locale;

/**
 *
 * @author Klitna, Filipenko
 */
public class InterviewDAOImpl {


   //Get all interviews    
   public List<Interview> getInterview(){

       Session session = null;
       Query query;
       List<Interview> interviewList = null;
       try {
           Locale.setDefault(Locale.ENGLISH);
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           query = session.createQuery("from Interview");
           interviewList = query.list();
       } catch (Exception e) {
           System.out.println(e);
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
       return interviewList;
   } 
   
   //Get interview by id
     public Interview getInterview(int idInterview){    
        Session session = null;
        Query query;        
       
        Interview interview = null;
        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from Interview "                                        
                                        + "where id_interview = " +  idInterview);
            interview = (Interview) query.uniqueResult();
                
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return interview;   
       
      
   } 
   
   
}
