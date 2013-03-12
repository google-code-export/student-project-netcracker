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
        //test.setHRMark(233, "молодец (HR)", "Hum");
        
    }

    @Override
    public List<Form> search(String category, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
//Add check if mark contains 
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
            interviewRes.setForm(selectedForm);
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
            query = session.createQuery("from Form where status = 1 and interview is not null");
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

    @Override
    public void deleteForm(int formID) {
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();   
            query = session.createQuery("from Form where idForm = " + formID);
            Form selectedForm = (Form) query.uniqueResult();
            session.delete(selectedForm);
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
    public void setStudentAttendStatus(int statusID, int formID) {
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();   
            query = session.createQuery("from Form where idForm = " + formID);
            Form selectedForm = (Form) query.uniqueResult();
            query = session.createQuery("from Status where idStatus = " + statusID);
            Status status = (Status) query.uniqueResult();
            selectedForm.setStatusAttend(status);
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

    @Override
    public List<InterviewRes> getInterviewersMarks(int selectedFormID) {
        Session session = null;
        Query query;                
        List<InterviewRes> marks = null;        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();    
            query = session.createQuery("from InterviewRes"
                    + " where form = " + selectedFormID + " and user IN "
                    + "(select idUser from UserList where idUserCategory = 3)");
            marks = query.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return marks;
    }

    @Override
    public String getInterviewerNameByID(int userID) {
        Session session = null;
        Query query;                
        String name = "";        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();    
            query = session.createQuery("from UserList"
                    + " where idUser = " + userID);
            UserList ul = (UserList) query.uniqueResult();
            name = ul.getUserName();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return name;
    }
 
}
