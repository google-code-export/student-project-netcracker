package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.configuration.Logable;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

/**
 * @author
 */
public class DAOImpl implements DAOInterface, Logable {

    public List GetNamesAndContacts() throws SQLException {
        Session session = null;
        Query re = null;
        List listOfForms = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from Form f left join f.contacts c");
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
        System.out.println("OH MY GGGGGGGGGGGGOOOOOOOOOOOOD!!!! THIS IS SPARTANS FIRST QUERY !!!!");
        System.out.println("=====================================================================");
        try{
            List<Object[]> listOfForms = HibernateFactory.getInstance().getFormDAO().GetNamesAndContacts();
            /*for (TableForm currForm : listOfForms) {
                System.out.println(currForm.getContacts()+"  "+currForm.getFirstName()+"  "+currForm.getFirstName());
            }*/

            for (Object[] currForm : listOfForms) {
                Form formObjects  = (Form) currForm[0];
                Contact contactObjects  = (Contact) currForm[1];
                System.out.println(formObjects.getFirstName() + " " + contactObjects.getInfo());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}