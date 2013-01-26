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
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("Select f from TableForm f left join f.contacts c");
            listOfForms = re.list();
            System.out.println(listOfForms);
            /*for (Object currForm : listOfForms) {
                System.out.print(((TableForm)currForm).getLastName() +
                        " " + ((TableForm)currForm).getFirstName() +
                        " " + ((TableForm)currForm).getMiddleName() +
                        " " + ((TableContact)currForm).getInfo());
                System.out.println();
            }*/
            for (Object currForm : listOfForms) {
                System.out.print(((TableForm)currForm).getLastName() +
                        " " + ((TableForm)currForm).getFirstName() +
                        " " + ((TableForm)currForm).getMiddleName());
                System.out.println();
            }
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
        List<TableForm> listOfForms = HibernateFactory.getInstance().getFormDAO().GetNamesAndContacts();
       /* System.out.println(listOfForms);
        for (TableForm currForm : listOfForms) {
            for (int i = 0; i < currForm.size(); i++)
                System.out.println(currForm.getLastName());
            //System.out.print("Last name " + currForm.getLastName());
           // System.out.println();*/
        //}

        /*for (TableForm currForm: listOfForms){
            System.out.print("Last name " + currForm.getLastName());
            System.out.println();
        }*/
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
