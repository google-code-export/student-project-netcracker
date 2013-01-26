package ua.netcrackerteam.configuration;

import ua.netcrackerteam.DAO.DAOImpl;

/**
 * @author
 */
public class HibernateFactory {

    private static DAOImpl formDAO = null;
    private static HibernateFactory instance = null;

    public static synchronized HibernateFactory getInstance(){
        if (instance == null){
            instance = new HibernateFactory();
        }
        return instance;
    }

    public DAOImpl getFormDAO(){
        if (formDAO == null){
            formDAO = new DAOImpl();
        }
        return formDAO;
    }
}
