package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.List;

/**
 * @author
 */
public class DAOCoreObject {
    private Query query = null;
    private Session session = null;
    private Transaction transaction = null;

    protected void beginTransaction() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        //this.session.beginTransaction();
        transaction = session.beginTransaction();
    }

    protected void commitTransaction() {
        if (null == this.session)
            return;
        if (this.session.isOpen()) {
            this.transaction.commit();
            this.session.close();
        }
        this.session = null;
    }

    public <T> T executeSingleGetQuery(String inputQuery){
        T object = null;
        try {
            query = session.createQuery(inputQuery);
            object = (T) query.uniqueResult();
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public <T> T executeSingleGetQuery1(String inputQuery, String param){
        T object = null;
        try {
            query = session.createQuery(inputQuery);
            query.setParameter("userNameParam", param.toUpperCase());
            object = (T) query.uniqueResult();
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public <T> List<T> executeListGetQuery(String inputQuery){
        List<T> object = null;
        try {
            query = this.session.createQuery(inputQuery);
            object = query.list();
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public <T> void saveUpdatedObject(T updatedObjects) {
        try {
            session.save(updatedObjects);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void executeDeleteQuery(String inputQuery)  {
        try {
            query = session.createQuery(inputQuery);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
