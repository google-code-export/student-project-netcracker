package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.configuration.Logable;
import java.sql.SQLException;
import java.util.*;

/**
 * @author
 */
public class DAOImpl implements DAOInterface, Logable {
    @Override
    public List GetNamesAndContacts() throws SQLException {
        Session session = null;
        Query re = null;
        List listOfForms = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("select cc, c, f from TableContact cc join cc.contact c join cc.idForm f");
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

    public static void main (String[] args) throws SQLException{
        Locale.setDefault(Locale.ENGLISH);
        List<Object[]> listOfForms = null;
        System.out.println("OH MY GGGGGGGGGGGGOOOOOOOOOOOOD!!!! THIS IS SPARTANS FIRST QUERY !!!!");
        System.out.println("=====================================================================");
        try{
            listOfForms = HibernateFactory.getInstance().getFormDAO().GetNamesAndContacts();
            for (Object[] currForm : listOfForms) {
                TableContact contactObjects = (TableContact) currForm[0];
                TableContactCategory contactCategoryObjects = (TableContactCategory) currForm[1];
                TableForm formObjects = (TableForm) currForm[2];
                System.out.println(contactObjects.getInfo() + " " + contactCategoryObjects.getCategory() + " " +formObjects.getLastName());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
