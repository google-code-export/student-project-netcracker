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
    public ArrayList<TableForm> GetNamesAndContacts() throws SQLException {
        Session session = null;
        //ArrayList <TableForm> queryResult = null;
        //Query re = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            /*Query re = session.createQuery("select f.firstName, f.middleName, f.lastName, con.info " +
                    "from TableForm as f left outer join f.contacts as con");*/
            List<TableForm> listOfForms = re.list();
            System.out.println(listOfForms);
            for (TableForm currForm: listOfForms){
                System.out.print("Last name " + currForm.getLastName());
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    public static void main (String[] args) throws SQLException{
        Locale.setDefault(Locale.ENGLISH);
        Iterator iter = HibernateFactory.getInstance().getFormDAO().GetNamesAndContacts().iterator();
        //Iterator newIt = firstSelect.iterator();
        //System.out.println("OH MY GGGGGGGGGGGGOOOOOOOOOOOOD!!!! THIS IS SPARTANSE FIRST QUERY !!!");
        //System.out.println("=====================================================================");
//        for (TableForm currForm:firstSelect) {
//            System.out.println(currForm.toString());
//        }
        //while (iter.hasNext()) {
        //    Object currForm = iter.next();
        //    System.out.println(currForm);
        //}
    }
}
