package ua.netcrackerteam.DAO;

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
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}