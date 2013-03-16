/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO.Reports;

import java.util.List;
import java.util.Locale;
import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateUtil;

/**
 *
 * @author Klitna Tetiana
 */
public class DAOReportAmountRegistrationForms implements DAOReport{
     
    public static void main(String[] args)
    {
        List list = (new DAOReportAmountRegistrationForms()).getReportFoView();
    }
    
    public List getReportFoView() {
        
        Session session = null;
        Query query;        
        List report = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createSQLQuery(" select start_date, max_number, count(form.id_form) as form_number, max_number - count(form.id_form) as free_number "                                        
                                         + "from interview left join form on interview.id_interview = form.id_interview "
                                         + "group by start_date, max_number "
                                         + "order by start_date" );
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
