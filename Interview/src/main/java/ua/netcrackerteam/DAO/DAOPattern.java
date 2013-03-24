package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.List;

/**
 * @author
 */
public class DAOPattern {
    private Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    private Query re = null;
    private Transaction transaction = null;

    public <T> T getDAOObject(String inputQuery, Session session){
        T object = null;
        try {
            session.beginTransaction();
            re = session.createQuery(inputQuery);
            object = (T) re.uniqueResult();
        } catch (Exception e) {
            System.out.println(e);
        /*} finally {
            if (session != null && session.isOpen()) {
                session.close();
            }*/
        }
        return object;
    }

    public <T> List<T>  getListOfDAOObjects(String inputQuery, Session session){
        List<T> object = null;
        try {
            session.beginTransaction();
            re = session.createQuery(inputQuery);
            object = re.list();
        } catch (Exception e) {
            System.out.println(e);
        /*} finally {
            if (session != null && session.isOpen()) {
                session.close();
            }*/
        }
        return object;
    }

    public <T> void saveDAOObject(T newData, Session session) {
        try {
            transaction = session.beginTransaction();
            session.save(newData);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        /*} finally {
            if (session != null && session.isOpen()) {
                session.close();
            }*/
        }
    }
}
