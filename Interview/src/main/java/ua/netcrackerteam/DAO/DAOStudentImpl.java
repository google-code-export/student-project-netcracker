package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.configuration.ShowHibernateSQLInterceptor;

import javax.interceptor.Interceptors;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of DAOStudent
 * Implements base methods to work with student and related table in database,
 * such as add, update, get information by username,
 * @author Zhokha Maksym
 */
public class DAOStudentImpl implements DAOStudent
{
    public static void main(String[] args) throws SQLException {
        
        //Test get form by user id
//        Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserId(5);
//        System.out.println(form.getFirstName());

        //Test adding new form        
//        Form form1 = new Form();
//        form1.setIdForm(13L);
//        form1.setFirstName("Иван");
//        form1.setLastName("Царевич");
//        form1.setMiddleName("Дурак");
//        form1.setExecProject("Сайт неткрекера");
//        form1.setReason("adfd");
//        form1.setExtraInfo("dfdfd");
//        form1.setInstituteYear(4);
//        Date date = new Date(2013, 1, 1);
//        form1.setInstituteGradYear(date);
//        Date date1 = new Date(2007, 6, 25);
//        form1.setSchoolGradYear(date1);
//        form1.setExtraKnowledge("dfdfdf");
//        form1.setInterestStudy("+");
//        form1.setInterestWork("+");
//        form1.setInterestSoftware("-");
//        form1.setInterestTelecom("?");
//        form1.setAvgScore(95.0);
//        form1.setAvgLast(92.0);
//        form1.setPhoto("photo");
//        form1.setIdStatus(1L);
//        form1.setIdInstitute(1L);
//        form1.setIdSchool(1L);
//        form1.setIdUser(2L);
//        form1.setIdInterview(1L);
//        HibernateFactory.getInstance().getStudentDAO().addForm(form1);
        
        //Test updating form        
//        form1.setFirstName("Йосип");
//        form1.setLastName("Кобзон");
//        HibernateFactory.getInstance().getStudentDAO().updateForm(form1);

        //Test getting form by user name          
//        Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName("briarey");
//        System.out.println(form.getUser().getIdUser());
         
        //Test how to get form by form id
//         Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//         session.beginTransaction();
//         Form form2 = (Form) session.get(Form.class, 9);
//         System.out.println("blabla"); 
        
        //Test getting list of forms by interview id
//        List<Form> forms = new DAOStudentImpl().getFormsByInterviewId(1);
//        System.out.println(forms);
        
        //Test getting user email by username
        String email = new DAOStudentImpl().getEmailByUserName("FirstLogin");
        System.out.println(email);
        
        
    }
    
    /**
     * Returns Form object appropriate to certain user by user id specified
     * @param idUser - user id, whose form is looked
     * @return Returns Form object of the user with id specified in parameter idUser
     */
    @Override
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public Form getFormByUserId(int idUser) {
        Session session = null;
        Query query;        
        Form form = null;
        Form confirmedForm = null;
        Form needConfirmationForm = null;
        Form registeredForm = null;
        Form withoutStatusForm = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from Form where user = " + idUser + "order by idForm");
            List<Form> forms = query.list();
            Iterator formIter = forms.iterator();
            while(formIter.hasNext()) {
                Form curForm = (Form) formIter.next();
                if (curForm.getStatus() != null && 
                        curForm.getStatus().
                        getName().
                        trim().
                        equalsIgnoreCase("Подтверждена")) {
                        confirmedForm = curForm;                    
                }
                else if(curForm.getStatus() != null && 
                        curForm.getStatus().
                        getName().
                        trim().
                        equalsIgnoreCase("Требует подтверждения")) {
                        needConfirmationForm = curForm;
                }
                else if(curForm.getStatus() != null && 
                        curForm.getStatus().
                        getName().
                        trim().
                        equalsIgnoreCase("Зарегистрирована")) {
                        registeredForm = curForm;
                }
                else {
                    withoutStatusForm = curForm;
                }
            }
            if(registeredForm != null) {
                form = registeredForm;
            }
            if(needConfirmationForm != null) {
                form = needConfirmationForm;
            }
            if (confirmedForm != null) {
                form  = confirmedForm;
            }
            if (form == null) {
                form = withoutStatusForm;
            }
            
            System.out.println(forms);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return form;       
    }
    
    
    
    /**
     * Save data, stored in Form object to Form table in database
     * @param form - Form object, stored data
     */
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public void addForm(Form form) {
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();            
            session.save(form);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Update row in database, related to specified Form object
     * @param form - Form object, contained data
     */
    @Override
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public void updateForm(Form form) {
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();            
            session.update(form);            
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    /**
     * Returns Form object appropriate to certain user by user name(login) specified
     * @param userName - user name (login)
     * @return 
     */
    @Override
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public Form getFormByUserName(String userName) {       
        Session session = null;
        Query query;        
        Form form = null;
        UserList user = null;
        int idUser=0;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from UserList "                                        
                                        + "where userName = '" + userName + "'");
            user = (UserList) query.uniqueResult();
            idUser = user.getIdUser();           
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return getFormByUserId(idUser);       
    }
    
   /**
    * Returns list of forms with id of interview specified in param
    * @param idInterview id of interview
    * @return List of forms which have id of interview equaled to param specified
    */    
    @Override
   public List<Form> getFormsByInterviewId(int idInterview){       
        Session session = null;
        Query query;        
        List<Form> forms = null;                
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from Form "                                        
                                        + "where interview = " + idInterview);
            forms = query.list();                        
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return forms;
   }
   
   /**
    * Returns string, represented email of user, whose username specifed in param
    * @param userName username from user_list table
    * @return email of a user, whose username specified in param
    */
    @Override
   public String getEmailByUserName(String userName){
       Session session = null;
       Query query;        
       UserList user = null;                
       String email = null;
       try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from UserList "                                        
                                        + "where userName = '" + userName + "'");
            user = (UserList) query.uniqueResult();                        
            email = user.getEmail();
       } catch (Exception e) {
            System.out.println(e);
       } finally {
       if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return email;
   }
    
}


