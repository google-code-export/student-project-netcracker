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
    
    public static void main(String[] args) {
        DAOHRImpl test = new DAOHRImpl();
        test.setHRMark(239, "молодец", "Hum");
    }
 
}
