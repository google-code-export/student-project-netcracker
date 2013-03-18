/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import java.util.List;
import java.util.Locale;
import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.configuration.HibernateUtil;

/**
 *
 * @author home
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
                                           "group by start_date, max_number ");          
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
     * общие итоги 
     * (сколько анкет зарегистрировано, сколько человек записалось на собеседования, сколько человек прошло собеседование)
     * @return 
     */
    public List getReportAmountRegistrationForms(){
        Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("");          
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
    
     /**
     *Расшифровка другое
     */
        public List getReportAdvertisingEfficiencyOTher(){
        Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select nvl(other, 'Не уточнено'), count(id_form) "+
                                           "from advert " +
                                           "where advert.id_advert_category = 5 " + 
                                           "group by other");          
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
        
    public List ReportStudents(){
               Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("");          
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
}
