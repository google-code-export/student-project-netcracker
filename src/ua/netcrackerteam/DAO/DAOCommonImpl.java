package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.DAO.Entities.EnrollmentScores;
import ua.netcrackerteam.DAO.Entities.InterviewRes;
import ua.netcrackerteam.DAO.Entities.UserCategory;
import ua.netcrackerteam.DAO.Entities.UserList;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.controller.bean.StudentsMarks;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 */
public class DAOCommonImpl extends DAOCoreObject implements DAOCommon{
    @Override
    public void setUser(String userName,
                        String userPassword,
                        String userEmail,
                        String active,
                        int idUserCategory) throws SQLException{
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            UserList userList = new UserList();
            userList.setUserName(userName);
            userList.setPassword(userPassword);
            userList.setEmail(userEmail);
            userList.setActive(active);
            //Filipenko
            //---------
            //userList.setIdUserCategory(idUserCategory);
            //+++++++++
            userList.setIdUserCategory(getUserCategoryByID(idUserCategory, session));
            session.save(userList);
            transaction.commit();
        } catch (Exception e) {
            //Propagate instead of handling
        	throw new SQLException();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public static List getUser() throws SQLException {
        Session session = null;
        Query re = null;
        List listOfForms = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList");
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

    public static UserCategory getUserCategoryByID(int currUserCategoryID, Session session) throws SQLException {
        //Session session = null;
        Query re = null;
        List listOfCategories = null;
        UserCategory currUserCategory = new UserCategory();
        try {
            Locale.setDefault(Locale.ENGLISH);
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            //session.beginTransaction();
            re = session.createQuery("from UserCategory where idUserCategory = '" + currUserCategoryID + "'");
            listOfCategories = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            /*if (session != null && session.isOpen()) {
                session.close();
            }*/
        }
        currUserCategory = (UserCategory) listOfCategories.get(0);
        return currUserCategory;
    }

    @Override
    public void addSomethingNew(Object newData) {
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(newData);
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
    public List getUserByName(String userName) throws SQLException {
        Session session = null;
        Query re = null;
        List listOfUsers = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserList where upper(userName) ='" + userName.toUpperCase() + "'");
            listOfUsers = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listOfUsers;
    }

    @Override
    public void resetOnNewEmail(String userName, String userEmail){
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
            userList.setEmail(userEmail);
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

    public StudentsMarks getStudentMark(int idForm, int idUserCategory){
        StudentsMarks currStudentsMark = new StudentsMarks();
        InterviewRes currInterviewRes = new InterviewRes();
        UserList currUser = new UserList();
        String query        = "";
        List listOfParams   = new ArrayList();
        listOfParams.add(idForm);
        listOfParams.add(idUserCategory);
        beginTransaction();
        query = "from InterviewRes where to_char(form) = to_char(:param0) and to_char(user.idUserCategory) = to_char(:param1)";
        currInterviewRes = super.executeSingleGetQuery(query, listOfParams);
        query = "from UserList where to_char(idUser)=to_char(:param0)";
        listOfParams.clear();
        StudentsMarks currStMark = null;
        if (!(currInterviewRes == null)) {
            currStMark = new StudentsMarks();
            currStMark.setSqlKnowledge(currInterviewRes.getSqlKnowledge());
            currStMark.setJavaKnowledge(currInterviewRes.getJavaKnowledge());
            currStMark.setComment(currInterviewRes.getScore());
            currStMark.setEnrollment(currInterviewRes.getEnrollmentScore());
            String currUserName = "";
            listOfParams.add(currInterviewRes.getUser().getIdUser());
            currUser = super.executeSingleGetQuery(query, listOfParams);
            currUserName = currUser.getUserName();
            currStMark.setInterviewerName   (currUserName);
            currStMark.setGroupWork(currInterviewRes.getWorkInTeam()==0 ? false: true);
            commitTransaction();
        }

        return currStMark;


    }

    public List<EnrollmentScores> getEnrollmentScores() {
        List<EnrollmentScores> currEnrolls = new ArrayList<EnrollmentScores>();
        String query = "";
        beginTransaction();
        query = "from EnrollmentScores";
        currEnrolls = super.executeListGetQuery(query);
        return currEnrolls;
    }

    public List<String> getEnrollmentScoresInString() {
        List<String> currEnrolls = new ArrayList<String>();
        String query = "";
        beginTransaction();
        query = "select name from ENROLLMENT_SCORES";
        currEnrolls = super.executeListGetSQLQuery(query);
        return currEnrolls;
    }

    /*public static void main (String[] arrgs) {
        DAOCommonImpl currDAO = new DAOCommonImpl();
        List<String> currLit = currDAO.getEnrollmentScoresInString();
    }*/
}
