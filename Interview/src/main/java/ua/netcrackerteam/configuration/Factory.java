package ua.netcrackerteam.configuration;

import ua.netcrackerteam.DAO.DAOImpl;

/**
 * @author
 */
public class Factory {

    private static DAOImpl formDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
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
