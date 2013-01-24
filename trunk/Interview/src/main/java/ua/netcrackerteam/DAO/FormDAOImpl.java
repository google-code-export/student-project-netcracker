package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.configuration.Logable;

import java.sql.SQLException;
import java.util.*;

import ua.netcrackerteam.DAO.*;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 24.01.13
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */
public class FormDAOImpl implements NewDAOForm, Logable {
    @Override
    public Collection GetNamesAndContacts() throws SQLException {
        Session session = null;
        Collection<String> queryResult = new ArrayList<String>();
        Query re = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

//            Query query = session.createQuery(
//                    " select f.first_name, f.middle_name, f.last_name, con.info "
//                    + " from Form f left join Contact con");
            re = session.createSQLQuery("select f.first_name, f.middle_name, f.last_name, con.info "+
                                                                    " from form f left join contact con on f.id_form = con.id_form");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return queryResult;
    }

    public static void main (String[] args) throws SQLException{

        Collection firstselect = Factory.getInstance().getFormDAO().GetNamesAndContacts();
        Iterator newIt = firstselect.iterator();
        System.out.println("OH MY GGGGGGGGGGGGOOOOOOOOOOOOD!!!! THIS IS SPARTANSE FIRST QUERY !!!");
        System.out.println("=====================================================================");
        while (newIt.hasNext()) {
            DAOForm newDAOForm = (DAOForm)newIt.next();
            System.out.println("First name " + newDAOForm.getFIRST_NAME() +
                                ", middle name " + newDAOForm.getMIDDLE_NAME() +
                                ", last name " + newDAOForm.getLAST_NAME());
        }

    }
}
