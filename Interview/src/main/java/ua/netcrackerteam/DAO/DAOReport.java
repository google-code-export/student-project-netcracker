/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.DAO.Entities.Institute;
import ua.netcrackerteam.configuration.HibernateUtil;

/**
 *
 * @author Test class
 */
public class DAOReport {
    
    public List getReportDynamicsOfIncreaseStudents() {
        
        Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select to_char(start_date, 'DD/MM/YYYY HH24:MI'), max_number, count(form.id_form) as form_number, max_number - count(form.id_form) as free_number " +                                        
                                           "from interview left join form on interview.id_interview = form.id_interview " +
                                           "where interview.id_interview <> -1 " +
                                           "group by start_date, max_number " +
                                           " order by start_date desc");          
            report = query.list();
                
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return report;   
    } 
    
       
    /**
     *эффективность рекламы
     */
 public List getReportAdvertisingEfficiency(){
        Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select description , count(id_form) "+
                                           "from advert_category left join advert " +
                                           "on advert_category.id_advert_category = advert.id_advert_category " + 
                                           "group by description" + 
                                           " order by count(id_form) desc");          
            report = query.list();
                
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return report;    
    }
    
 /*общие итоги (сколько анкет зарегистрировано, сколько человек записалось на собеседования, сколько человек прошло собеседование)*/
    public List getResultOfInterview(){
        Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select sum(users_on_interview), sum(users_with_form), sum(users_with_set_interview) "+
                                            "from( "+
                                            "select count(distinct id_form) as users_on_interview, 0 as users_with_form, 0 as users_with_set_interview " +
                                            "from interview_res " +
                                            "union all " +
                                            "select 0,  count(id_form) as users_with_form, 0 " +
                                            "from form " +
                                            "union all "+
                                            "select 0, 0, count(id_form) "+
                                            "from form "+
                                            "where id_interview is not null) " );          
            report = query.list();
                
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return report; 
    }
    
      public List getFormByIdInterview(int idInterview){
          
       List report = new LinkedList();
       
       report.add(new Object[]{"1 " + idInterview, "Фамилия1", "Имя1", "ВУЗ1111111111111111111111111111111111111111111111111111", "Телефон1"});
       report.add(new Object[]{"2 " + idInterview, "Фамилия1", "Имя1", "ВУЗ1111111111111111111111111111111111111111111111111111", "Телефон1"});
       
       
       return report;
       
    }
      
      
      public List getResult(){
          List reportData = new ArrayList(5);
          
          reportData.add(new Object[] {"Дата", "34", "30", "30", "0"});
          reportData.add(new Object[] {"Дата", "34", "30", "25", "5"});
          reportData.add(new Object[] {"Дата", "6", "30", "20", "10"});
          reportData.add(new Object[] {"Дата", "34", "30", "20", "10"});
          reportData.add(new Object[] {"Дата", "43", "30", "1", "29"});
          
          return reportData;
      }
      
      public List getInstitute(){
          List reportData = new ArrayList(5);
          
          Institute institute1 = new Institute();
          institute1.setInstituteId(1);
          institute1.setName("Институт 1");
          reportData.add(institute1);
          Institute institute2 = new Institute();
          institute2.setInstituteId(2);
          institute2.setName("Институт 2");
          reportData.add(institute2);
          Institute institute3 = new Institute();
          institute3.setInstituteId(3);
          institute3.setName("Институт 3");
          reportData.add(institute3);
          Institute institute4 = new Institute();
          institute4.setInstituteId(4);
          institute4.setName("Институт 4");         
          reportData.add(institute4); 
          Institute institute5 = new Institute();
          institute5.setInstituteId(5);
          institute5.setName("Институт 5");
          reportData.add(institute5);
          
          return reportData;
      }
      
      public List getFormByIdInstitute(int idInstitute){
           List reportData = new ArrayList(2);
           
           reportData.add(new Object[] {"12" + idInstitute, "Фамилия 1", "Имя 1", "1111-11"});
           reportData.add(new Object[] {"12" + idInstitute, "Фамилия 2", "Имя 2", "2222-22"});
           
           return reportData;
      }
      

}
