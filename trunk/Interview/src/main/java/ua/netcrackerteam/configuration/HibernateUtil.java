package ua.netcrackerteam.configuration;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 24.01.13
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static{
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutDown(){
        getSessionFactory().close();
    }
}