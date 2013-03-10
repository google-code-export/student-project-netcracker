package ua.netcrackerteam.DAO;

import java.util.List;
import java.util.Locale;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateUtil;

/**
 * @author Kushnirenko Anna
 */
public class DAOHRImpl implements DAOHR{
    
    public static void main(String[] args) {
        DAOHRImpl test = new DAOHRImpl();
        //test.setHRMark(239, "молодец", "Hum");
        test.verificateForm(202);
    }

    @Override
    public List<Form> search(String category, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setHRMark(int selectedFormID, String insertedMark, String userNameHR) {
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        InterviewRes interviewRes = new InterviewRes();
        try {
            
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();    
            query = session.createQuery("from Form where idForm = " + selectedFormID);
            Form selectedForm = (Form) query.uniqueResult();
            interviewRes.setIdForm(selectedForm);
            query = session.createQuery("from UserList where userName = '" +userNameHR+"'");
            UserList hr = (UserList) query.uniqueResult();
            interviewRes.setIdUser(hr);
            interviewRes.setScore(insertedMark);
            session.save(interviewRes);
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
    public List<Form> getAllRegisteredForms() {
        Session session = null;
        Query query;                
        List<Form> formList = null;        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();            
            query = session.createQuery("from Form where status = 1");
            formList =  query.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return formList;
    }

    @Override
    public List<Form> getNonVerificatedForms() {
        Session session = null;
        Query query;                
        List<Form> formList = null;        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();            
            query = session.createQuery("from Form where status = 5");
            formList =  query.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return formList;
    }

    @Override
    public void verificateForm(int formID) {
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();   
            query = session.createQuery("from Status where idStatus = " + 1);
            Status status = (Status) query.uniqueResult();
            query = session.createQuery("from Form where idForm = " + formID);
            Form selectedForm = (Form) query.uniqueResult();
            UserList user = selectedForm.getUser();
            query = session.createQuery("from Form where user = " + user.getIdUser() 
                    + " and status = " + status.getIdStatus());
            Form oldForm = (Form) query.uniqueResult();
            session.delete(oldForm);
            selectedForm.setStatus(status);
            session.save(selectedForm);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    
 
}
