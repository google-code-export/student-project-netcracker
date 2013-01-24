package ua.netcrackerteam.DAO;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 24.01.13
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public class Factory {

    private static FormDAOImpl formDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public FormDAOImpl getFormDAO(){
        if (formDAO == null){
            formDAO = new FormDAOImpl();
        }
        return formDAO;
    }
}
