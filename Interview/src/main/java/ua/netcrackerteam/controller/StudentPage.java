package ua.netcrackerteam.controller;

import org.hibernate.Session;
import ua.netcrackerteam.DAO.Institute;
import ua.netcrackerteam.DAO.UserCategory;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.List;
import java.util.Locale;

/**
 * Student controller class for using in View
 * @author Filipenko
 */
public class StudentPage {

    public List<Institute> GetUniversityList() {

        Session session = null;
        org.hibernate.Query re = null;
        List instituteList = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from Institute");
            instituteList = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return instituteList;

    }

}
