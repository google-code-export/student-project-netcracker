package ua.netcrackerteam.controller;

import java.util.Locale;
import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.DAO.DAOStudentImpl;
import ua.netcrackerteam.DAO.Form;
import ua.netcrackerteam.DAO.UserList;
import ua.netcrackerteam.configuration.HibernateUtil;

/**
 *
 * @author Maksym Zhokha
 */
public class StudentPageByMaks {
    
    public static void main(String[] args) {
//        DAOStudentImpl stImpl = new DAOStudentImpl();
//        Form form = stImpl.getFormByUserLogin("ThirdLogin");
//        System.out.println("blabla");
        
//        Form form1 = new StudentPageByMaks().getFormByUserLogin("ThirdLogin");
//         System.out.println("blabla");
        
    }
    
    
//    public Form getFormByUserLogin(String userName) {
//        
//        //-----------------in test------------------
//        Session session = null;
//        Query query;        
//        Form form = null;
//        try {
//            Locale.setDefault(Locale.ENGLISH);
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            session.beginTransaction();
//            query = session.createQuery("from Form, UserList "
//                                        + "where Form.idUser = Form.idUser and "
//                                        + "UserList.userName = '" + userName + "'");
//            form = (Form) query.uniqueResult();           
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//        return form;       
//    }
    
    
    
    

}
