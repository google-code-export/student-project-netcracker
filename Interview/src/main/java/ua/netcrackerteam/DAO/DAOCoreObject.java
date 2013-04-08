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

    public <T> T executeSingleGetQuery(String inputQuery, List param){
        T object = null;
        try {
            query = session.createQuery(inputQuery);
            for (int i = 0; i < param.size(); i++) {
                query.setParameter("param" + String.valueOf(i), param.get(i));
            }
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


    public <T> List<T> executeListGetQuery(String inputQuery, List listOfParameters){
        List<T> object = null;
        try {
            query = session.createQuery(inputQuery);
            for (int i = 0; i < listOfParameters.size(); i++) {
                query.setParameter("param" + String.valueOf(i), listOfParameters.get(i));
            }
            object = query.list();
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public <T> List<T> executeListGetSQLQuery(String inputQuery, List listOfParameters){
        List<T> object = null;
        try {
            query = session.createSQLQuery(inputQuery);
            for (int i = 0; i < listOfParameters.size(); i++) {
                query.setParameter("param" + String.valueOf(i), listOfParameters.get(i));
            }
            object = query.list();
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public <T> List<T> executeListGetSQLQuery(String inputQuery){
        List<T> object = null;
        try {
            query = session.createSQLQuery(inputQuery);
            object = query.list();
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public <T> void saveUpdatedObject(T updatedObjects) {
        try {
            session.saveOrUpdate(updatedObjects);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public <T> void updatedObject(T updatedObjects) {
        try {
            session.update(updatedObjects);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public <T> void executeDeleteQuery(T objectsToDelete)  {
        try {
            //query = session.createQuery(inputQuery);
            //query.executeUpdate();
            session.delete(objectsToDelete);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void executeDeleteQuery(String inputQuery, List listOfParameters)  {
        try {
            query = session.createQuery(inputQuery);
            for (int i = 0; i < listOfParameters.size(); i++) {
                query.setParameter("param" + String.valueOf(i), listOfParameters.get(i));
            }
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
