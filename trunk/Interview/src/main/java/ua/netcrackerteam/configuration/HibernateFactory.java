package ua.netcrackerteam.configuration;

import ua.netcrackerteam.DAO.*;

/**
 * @author
 */
public class HibernateFactory {

    private static DAOStudentImpl studentDAO = null;
    private static HibernateFactory instance = null;

    public static synchronized HibernateFactory getInstance(){
        if (instance == null){
            instance = new HibernateFactory();
        }
        return instance;
    }

    public DAOStudentImpl getStudentDAO(){
        if (studentDAO == null){
            studentDAO = new DAOStudentImpl();
        }
        return studentDAO;
    }
}
