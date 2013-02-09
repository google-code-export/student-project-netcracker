package ua.netcrackerteam.DAO;

import java.sql.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.HibernateUtil;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of DAOStudent
 * @author
 */
public class DAOStudentImpl implements DAOStudent {
public static void main(String[] args) throws SQLException {
        Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserId(5L);
        System.out.println(form.getFirstName());
        
        Form form1 = new Form();
        //form1.setIdForm(13L);
        /*form1.setFirstName("Иван");
        form1.setLastName("Царевич");
        form1.setMiddleName("Дурак");
        form1.setExecProject("Сайт неткрекера");
        form1.setReason("adfd");
        form1.setExtraInfo("dfdfd");
        form1.setInstituteYear(4);
        Date date = new Date(2013, 1, 1);
        form1.setInstituteGradYear(date);
        Date date1 = new Date(2007, 6, 25);
        form1.setSchoolGradYear(date1);
        form1.setExtraKnowledge("dfdfdf");
        form1.setInterestStudy("+");
        form1.setInterestWork("+");
        form1.setInterestSoftware("-");
        form1.setInterestTelecom("?");
        form1.setAvgScore(95.0);
        form1.setAvgLast(92.0);
        form1.setPhoto("photo");
        form1.setIdStatus(1L);
        form1.setIdInstitute(1L);
        form1.setIdSchool(1L);
        form1.setIdUser(2L);
        form1.setIdInterview(1L)*/;
        HibernateFactory.getInstance().getStudentDAO().addForm(form1);
        
        form1.setFirstName("Йосип");
        form1.setLastName("Кобзон");
        HibernateFactory.getInstance().getStudentDAO().updateForm(form1);
    }

    @Override
    public Form getFormByUserId(Long idUser) {        
        Session session = null;
        Query query;        
        Form form = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from Form where idUser = " + idUser);
            form = (Form) query.uniqueResult();           
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return form;       
    }

    @Override
    public void addForm(Form form) {
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();            
            session.save(form);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateForm(Form form) {
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();            
            session.update(form);            
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /*
        Temp method for get user list from DB. In future this method will be move to another entity.
     */

}


