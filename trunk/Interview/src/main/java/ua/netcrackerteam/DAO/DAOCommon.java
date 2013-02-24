package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.configuration.ShowHibernateSQLInterceptor;

import javax.interceptor.Interceptors;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

/**
 *
 */
public class DAOCommon {
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public void setUser(String userName,
                        String userPassword,
                        String userEmail,
                        String active,
                        int idUserCategory) throws SQLException{
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            UserList userList = new UserList();
            userList.setUserName(userName);
            userList.setPassword(userPassword);
            userList.setEmail(userEmail);
            userList.setActive(active);
            //Filipenko
            //---------
            //userList.setIdUserCategory(idUserCategory);
            //+++++++++
            userList.setIdUserCategory(getUserCategoeyByID(idUserCategory, session));
            session.save(userList);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public int deleteUserById(int user_Id) {
        Session session = null;
        Query re = null;
        Transaction transaction = null;
        int number = 0;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            re = session.createQuery("delete from UserList where idUser = '" + user_Id + "'");
            number = re.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return number;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static List getUser() throws SQLException {
        Session session = null;
        Query re = null;
        List listOfForms = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList");
            listOfForms = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listOfForms;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public static UserCategory getUserCategoeyByID(int currUserCategoryID, Session session) throws SQLException {
        //Session session = null;
        Query re = null;
        List listOfCategories = null;
        UserCategory currUserCategory = new UserCategory();
        try {
            Locale.setDefault(Locale.ENGLISH);
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            re = session.createQuery("from UserCategory where idUSerCategory = '" + currUserCategoryID + "'");
            listOfCategories = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            /*if (session != null && session.isOpen()) {
                session.close();
            }*/
        }
        currUserCategory = (UserCategory) listOfCategories.get(0);
        return currUserCategory;
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public void addSomethingNew(Object newData) {
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(newData);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Interceptors(ShowHibernateSQLInterceptor.class)
    public List getUserByName(String userName) throws SQLException {
        Session session = null;
        Query re = null;
        List listOfUsers = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            listOfUsers = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listOfUsers;
    }


}
