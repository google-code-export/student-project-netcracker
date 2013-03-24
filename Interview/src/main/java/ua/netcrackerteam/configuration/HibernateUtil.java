package ua.netcrackerteam.configuration;

/**
 */
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.util.Locale;

public class HibernateUtil implements Logable{
    private static SessionFactory sessionFactory;

    static{
        try {
            Locale.setDefault(Locale.ENGLISH);
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