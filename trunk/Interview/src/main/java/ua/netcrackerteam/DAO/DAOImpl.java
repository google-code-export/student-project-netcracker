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
    public Collection GetNamesAndContacts() throws SQLException {
        Session session = null;
        List queryResult = new ArrayList();
        Query re = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
//            Query query = session.createQuery(
//                    " select f.first_name, f.middle_name, f.last_name, con.info "
//                    + " from Form f left join Contact con");
            /*re = session.createSQLQuery("select f.first_name, f.middle_name, f.last_name, con.info "+
                    " from form f left join contact con on f.id_form = con.id_form");*/
            re = session.createQuery("from TableForm where idForm > 0");
            queryResult = re.list();
            System.out.println(re);
            System.out.println(queryResult);
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
        Locale.setDefault(Locale.ENGLISH);
        Collection firstSelect = HibernateFactory.getInstance().getFormDAO().GetNamesAndContacts();
        /*Iterator newIt = firstSelect.iterator();
        System.out.println("OH MY GGGGGGGGGGGGOOOOOOOOOOOOD!!!! THIS IS SPARTANSE FIRST QUERY !!!");
        System.out.println("=====================================================================");
        while (newIt.hasNext()) {
            TableForm newTableForm = (TableForm) newIt.next();
            System.out.println("First name " + newTableForm.getLastName());
        }*/
    }
}
