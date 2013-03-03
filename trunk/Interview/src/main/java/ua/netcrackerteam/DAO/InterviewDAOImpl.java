/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author home, Filipenko
 */
public class InterviewDAOImpl {

    public static void main(String arrgs[]) {
        InterviewDAOImpl interviewAO = new InterviewDAOImpl();
        List <Interview> interviews = interviewAO.getInterview();

        Interview currInterview = interviewAO.getInterview(new Date(112, 9, 28, 17, 30));
    }

   //Получает все интервью 
   public List<Interview> getInterview(){
       
       /*List<Interview> interwievs = new  ArrayList<Interview>();
        Interview interwiev = new Interview();
        interwiev.setStartDate(new Date(2012, 9, 28, 17, 30));
        interwiev.setEndDate(new Date(2012, 9, 28, 18, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
         interwiev.setStartDate(new Date(2012, 9, 28, 18, 30));
        interwiev.setEndDate(new Date(2012, 9, 28, 19, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
        interwiev.setStartDate(new Date(2012, 9, 29, 17, 30));
        interwiev.setEndDate(new Date(2012, 9, 29, 18, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
        interwiev.setStartDate(new Date(2012, 9, 29, 18, 30));
        interwiev.setEndDate(new Date(2012, 9, 29, 19, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
        interwiev.setStartDate(new Date(2012, 9, 30, 17, 30));
        interwiev.setEndDate(new Date(2012, 9, 30, 18, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
        interwiev.setStartDate(new Date(2012, 9, 30, 18, 30));
        interwiev.setEndDate(new Date(2012, 9, 30, 19, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);  */

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
   
   //Получает интервью по конкретной дате 
   public Interview getInterview(Date startInterview){
      return new Interview();
       /*Session session = null;
       Query query;
       Interview interviewList = null;
       try {
           Locale.setDefault(Locale.ENGLISH);
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           query = session.createQuery("from Interview where year(startDate) = " + startInterview.getYear() +
                   " and month(startDate)= " + startInterview.getMonth()+ " and day(startDate) = " +startInterview.getDay() +
                   " and hour(startDate)= " + startInterview.getHours() + " and minute(startDate) = " +startInterview.getMinutes());
           interviewList = (Interview)query.uniqueResult();
       } catch (Exception e) {
           System.out.println(e);
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
       return interviewList;*/
   } 
   
   
}
