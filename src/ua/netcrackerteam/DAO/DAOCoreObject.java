package ua.netcrackerteam.DAO;

import org.hibernate.*;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.util.xls.entity.XlsUserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class DAOCoreObject {
    private Query query = null;
    private SQLQuery sqlQuery = null;
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
        List<T> object = new ArrayList<T>();
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

    public <T> List<T> executeListGetSQLQueryToBean(String inputQuery){
        List<T> object = new ArrayList<T>();
        try {
            sqlQuery = (SQLQuery) session.createSQLQuery(inputQuery).setResultTransformer(new AliasToBeanResultTransformer(XlsUserInfo.class));
            sqlQuery.addScalar("number2",               StringType.INSTANCE);
            sqlQuery.addScalar("surname",               StringType.INSTANCE);
            sqlQuery.addScalar("name",                  StringType.INSTANCE);
            sqlQuery.addScalar("secondName",            StringType.INSTANCE);
            sqlQuery.addScalar("finalResult",           StringType.INSTANCE);
            sqlQuery.addScalar("hr1",                   StringType.INSTANCE);
            sqlQuery.addScalar("result1",               StringType.INSTANCE);
            sqlQuery.addScalar("comment1",              StringType.INSTANCE);
            sqlQuery.addScalar("work_in_team1",         IntegerType.INSTANCE);
            sqlQuery.addScalar("hr2",                   StringType.INSTANCE);
            sqlQuery.addScalar("result2",               StringType.INSTANCE);
            sqlQuery.addScalar("comment2",              StringType.INSTANCE);
            sqlQuery.addScalar("work_in_team2",         IntegerType.INSTANCE);
            sqlQuery.addScalar("javaKnowledge",         StringType.INSTANCE);
            sqlQuery.addScalar("sqlKnowledge",          StringType.INSTANCE);
            sqlQuery.addScalar("cource",                StringType.INSTANCE);
            sqlQuery.addScalar("averageHighSchoolGrade",StringType.INSTANCE);
            sqlQuery.addScalar("speciality",            StringType.INSTANCE);
            sqlQuery.addScalar("highSchoolName",        StringType.INSTANCE);
            sqlQuery.addScalar("email1",                StringType.INSTANCE);
            sqlQuery.addScalar("email2",                StringType.INSTANCE);
            sqlQuery.addScalar("telNumber",             StringType.INSTANCE);
            object = sqlQuery.list();

        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public <T> List<T> executeListGetSQLQuery(String inputQuery){
        List<T> object = new ArrayList<T>();
        try {
            query = session.createSQLQuery(inputQuery);
            object = query.list();
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public List<Object[]> executeObjectListGetSQLQuery(String inputQuery, List listOfParameters){
        List object = new ArrayList();
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
