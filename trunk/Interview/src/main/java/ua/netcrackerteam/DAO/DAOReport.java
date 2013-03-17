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
 * @author Klitna Tetiana
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
                                           "group by start_date, max_number " +
                                           "union "+
                                           "select 'Итого', sum(max_number), count(form.id_form) as form_number, sum(max_number) - count(form.id_form) as free_number " +                                       
                                           "from interview left join form on interview.id_interview = form.id_interview ");          
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
    
    public List getReportAmountRegistrationForms(){
        Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery("select sum(amount_users) as total_users, sum(amount_forms) as total_forms, sum(amount_interview) as total_inteview " +                                        
                                           "from " +
                                           "(select count(id_user) as amount_users, 0 as amount_forms, 0 as amount_interview " +
                                           "from user_list inner join user_category on "+
                                           "user_list.id_user_category = user_category.id_user_category " +                                       
                                           "where user_category.category = 'abiturient' " +
                                           "union "+
                                           "select 0, count(id_form), 0 "+
                                           "from form "+
                                           "union "+
                                           "select 0, 0, count(id_form) "+
                                           "from form " +
                                           "where form.id_interview is not null)");          
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
