package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.controller.StudentPage;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

/**
 * @author krygin
 */
public class DAOAdminImpl implements DAOAdmin {

    @Override
    public void banUserByName(String userName) {
        Session session = null;
        Query re = null;
        UserList userList = null;
        Transaction transaction = null;
        String activity = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            activity = "banned";
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            userList = (UserList) re.uniqueResult();
            userList.setActive(activity);
            session.save(userList);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean checkUserAvailability(String userName){
        Session session = null;
        Query re = null;
        UserList userList = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            if (re.list().isEmpty()){
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return true;
    }

    @Override
    public boolean checkUserBanStatus(String userName){
        Session session = null;
        Query re = null;
        UserList userList = null;
        String activity = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            activity = "banned";
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            userList = (UserList) re.uniqueResult();
            if (userList.getActive().equals(activity)){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public List getUsersNonBanned() throws SQLException {
        Session session = null;
        Query re = null;
        List listOfForms = null;
        String activity = "";
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            activity = "banned".toUpperCase();
            re = session.createQuery("from UserList where upper(active) != '" + activity + "'");
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

    @Override
    public List getUsersBanned() throws SQLException {
        Session session = null;
        Query re = null;
        List listOfForms = null;
        String activity = "";
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            activity = "banned".toUpperCase();
            re = session.createQuery("from UserList where upper(active) = '" + activity + "'");
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

    @Override
    public void activateUserByName(String userName) {
        Session session = null;
        Query re = null;
        UserList userList = null;
        Transaction transaction = null;
        String activity = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            activity = "active";
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            userList = (UserList) re.uniqueResult();
            userList.setActive(activity);
            session.save(userList);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void resetOnNewPassword(String userName, String password){
        Session session = null;
        Query re = null;
        UserList userList = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            userList = (UserList) re.uniqueResult();
            userList.setPassword(password);
            session.save(userList);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void resetOnNewLogin(String oldUserName, String newUserName){
        Session session = null;
        Query re = null;
        UserList userList = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            re = session.createQuery("from UserList where upper(userName) ='" + oldUserName.toUpperCase() + "'");
            userList = (UserList) re.uniqueResult();
            userList.setUserName(newUserName);
            session.save(userList);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void changeUserType(String userName, int newType){
        //prepare data for filling user
        List userTypes = StudentPage.searchSomethingByID("UserCategory", "idUSerCategory", newType);
        UserCategory currUserCat = (UserCategory)userTypes.get(0);
        //change user type value
        Session session = null;
        Query re = null;
        UserList userList = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            userList = (UserList) re.uniqueResult();
            userList.setIdUserCategory(currUserCat);
            session.save(userList);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public String checkUserEmail(String userName){
        Session session = null;
        Query re = null;
        UserList userList = null;
        String userEmail = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            userList = (UserList) re.uniqueResult();
            userEmail = userList.getEmail();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userEmail;
    }

    @Override
    public String checkUserCategory(String userName){
        Session session = null;
        Query re = null;
        UserList userList = null;
        String userCategory = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            userList = (UserList) re.uniqueResult();
            userCategory = userList.getIdUserCategory().getName();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userCategory;
    }

    @Override
    public List getUsersFiltered(int userCategory) throws SQLException {
        Session session = null;
        Query re = null;
        List listOfForms = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList where upper(idUserCategory) = " + userCategory);
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

    @Override
    public List getUsersSearchResultNonBanned(String searchString){
        Session session = null;
        Query re = null;
        List listOfForms = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList where active = 'active' and " +
                    "(upper(userName) like upper('%" + searchString + "%') or " +
                    "idUser like '%" + searchString + "%' or upper(email) like upper('%" + searchString + "%'))");
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

    @Override
    public List getUsersSearchResultByCategoryNonBanned(String searchString, int userCategory){
        Session session = null;
        Query re = null;
        List listOfForms = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList where idUserCategory = " + userCategory + " and active = 'active' and " +
                    "(upper(userName) like upper('%" + searchString + "%') or " +
                    "idUser like '%" + searchString + "%' or upper(email) like upper('%" + searchString + "%'))");
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

    public void addAudit(AuditInterview auditInterview){
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(auditInterview);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List getAuditInterview(){
        Session session = null;
        Query query;
        List<AuditInterview> auditInterviews = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from AuditInterview where userName is not null order by idAudit desc");
            auditInterviews = query.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return auditInterviews;
    }

    @Override
    public int getUserCategoryIDByUserName(String userName){
        Session session = null;
        Query re = null;
        UserList userList = null;
        int userCategory = 0;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            userList = (UserList) re.uniqueResult();
            userCategory = userList.getIdUserCategory().getIdUSerCategory();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userCategory;
    }

    @Override
    public Long getCountTotalActions(){
        Session session = null;
        Query re = null;
        Long count = null;
        try{
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            count = (Long) session.createQuery("select count(idAudit) from AuditInterview").uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return count;
    }

    @Override
    public Long getCountRegisteredUsersTotal(){
        Session session = null;
        Query re = null;
        Long count = null;
        try{
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            count = (Long) session.createQuery("select count(idUser) from UserList").uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return count;
    }

    @Override
    public Long getCountRegisteredUsersByUserCategory(int id){
        Session session = null;
        Query re = null;
        Long count = null;
        try{
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            count = (Long) session.createQuery("select count(idUser) from UserList where idUserCategory = " + id).uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return count;
    }

    @Override
    public Long getCountUsersLoginTriedAll(){
        Session session = null;
        Query re = null;
        Long count = null;
        try{
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            count = (Long) session.createQuery("select count(idAudit) from AuditInterview where actionCategories = 1").uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return count;
    }

    @Override
    public Long getCountUsersLoginTriedNonRegUsers(){
        Session session = null;
        Query re = null;
        Long count = null;
        try{
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            count = (Long) session.createQuery("select count(idAudit) from AuditInterview where actionCategories = 1 and idUserCategory = 5").uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return count;
    }

    @Override
    public Long getCountUsersByActivity(String status){
        Session session = null;
        Query re = null;
        Long count = null;
        String upperStatus = status.toUpperCase();
        try{
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            count = (Long) session.createQuery("select count(idUser) from UserList where upper(active) = '" + upperStatus + "'").uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return count;
    }
}
