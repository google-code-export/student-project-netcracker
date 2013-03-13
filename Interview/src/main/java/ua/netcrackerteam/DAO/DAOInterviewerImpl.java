package ua.netcrackerteam.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.interceptor.Interceptors;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.configuration.ShowHibernateSQLInterceptor;

/**
 *
 * @author Zhokha Maksym
 */
public class DAOInterviewerImpl implements DAOInterviewer
{
    //Parameters to give them into search method
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String ID_FORM = "idForm";
    public static final String INSTITUTE_YEAR = "instituteYear";
    public static final String CATHEDRA = "cathedra.name";
    public static final String FACULTY = "cathedra.faculty.name";
    public static final String INSTITUTE = "cathedra.faculty.institute.name";
    
    
    
    
    public static void main(String[] args)
    {
//        DAOInterviewerImpl interviewer = new DAOInterviewerImpl();
//        List<Form> forms = interviewer.getAllBasicForms();
//        System.out.println(forms);
        
        
//                DAOInterviewerImpl interviewer = new DAOInterviewerImpl();
//                List<Form> forms = interviewer.getAllFormsByInterview(1);
//                System.out.println(forms);
        
        
        //        DAOInterviewerImpl interviewer = new DAOInterviewerImpl();
        //        String mark = interviewer.getStudentInterviewMark(116, "interMaks");
        //        System.out.println(mark);
        
//                DAOInterviewerImpl interviewer = new DAOInterviewerImpl();
//                interviewer.saveStudentInterviewMark(233, "interview123456", "Новая оценка от interview123456");
//                System.out.println("blabla");
        
        //        DAOInterviewerImpl interviewer = new DAOInterviewerImpl();
        //        List<Form> forms= interviewer.search("Фамилия", "Жоха");
        //        System.out.println("blabla");
        
    }
    
    
    @Override
    public List<Form> getAllBasicForms()          
    {
        Session session = null;
        Query query;
        List<Form> formList = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from Form f where f.interview is not null"
                    + " and ("
                    + " status.name = 'Зарегистрирована'"
                    + " or status.name = 'Прошел собеседование'"
                    + " or status.name = 'Не прошел собеседование'"
                    + ")"
                    );
            
            formList =  query.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return formList;
    }
    
    
    @Override
    public List<Form> getAllFormsByInterview(int idInterview) {
        Session session = null;
        Query query;
        List<Form> formList = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from Form where interview = "
                    + idInterview
                    + " and ("
                    + " status.name = 'Зарегистрирована'"
                    + " or status.name = 'Прошел собеседование'"
                    + " or status.name = 'Не прошел собеседование'"
                    + ")"
                    );
            formList =  query.list();            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return formList;
    }
    
    
    /**
     * Returns mark which interviewer gave to the student and which is presented
     * in text
     * @param idForm id form of student
     * @param interviewerUsername username of interviewer
     * @return mark which interviewer gave to the student
     */
    @Override
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public String getStudentInterviewMark(int idForm, String interviewerUsername) {
        Session session = null;
        Query query;
        InterviewRes interviewRes = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from InterviewRes where form = "
                    + idForm
                    + " and user = (select idUser from UserList where user_name = '"
                    +  interviewerUsername +"')");
            interviewRes =  (InterviewRes) query.uniqueResult();            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        if(interviewRes != null ) {
            return interviewRes.getScore();
        } else {
            return "";
        }
    }
    
    
    
    /**
     * Saves interview mark for student which interviewer gave to this student
     * @param idForm id form of student
     * @param interviewerUsername username of interviewer
     * @param mark mark represented in text field
     */
    @Override
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public void saveStudentInterviewMark(int idForm, String interviewerUsername, String mark) {
        Session session = null;
        Query query;
        Transaction transaction = null;        
        try {
            InterviewRes interviewRes = null;
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            query = session.createQuery("from InterviewRes where form = "
                    + idForm
                    + " and user = (select idUser from UserList where user_name = '"
                    +  interviewerUsername +"')");
            interviewRes =  (InterviewRes) query.uniqueResult();
            if(interviewRes != null) {
                interviewRes.setScore(mark);    //if score once had been given already(this is not first time)
            }
            else {                             //if it is first time, when interviewer give score to this form
                interviewRes = new InterviewRes();
                query = session.createQuery("from Form where idForm = " + idForm);
                Form selectedForm = (Form) query.uniqueResult();
                interviewRes.setForm(selectedForm);
                query = session.createQuery("from UserList where userName = '" +interviewerUsername+"'");
                UserList hr = (UserList) query.uniqueResult();
                interviewRes.setIdUser(hr);
                interviewRes.setScore(mark);                
            }            
            session.save(interviewRes);
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
    public List<Form> search(String filter, String searchText) {
        Session session = null;
        Query query;
        List<Form> formList = null;
        String fieldName = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            if(filter.equalsIgnoreCase("Фамилия") ||
                    filter.equalsIgnoreCase("Имя"))
            {
                if(filter.equalsIgnoreCase("Фамилия")) {
                    fieldName = LAST_NAME;
                } else if(filter.equalsIgnoreCase("Имя")) {
                    fieldName = FIRST_NAME;
                }
                query = session.createQuery("from Form where upper(" + fieldName + ") like upper('%" + searchText +"%')"
                        + " and ("
                        + " status.name = 'Зарегистрирована'"
                        + " or status.name = 'Прошел собеседование'"
                        + " or status.name = 'Не прошел собеседование'"
                        + ")"
                        );
                formList =  query.list();
            }
            else if(filter.equalsIgnoreCase("Номер анкеты") ||
                    filter.equalsIgnoreCase("Курс"))
            {
                if(filter.equalsIgnoreCase("Номер анкеты")) {
                    fieldName = ID_FORM;
                } else if(filter.equalsIgnoreCase("Курс")) {
                    fieldName = INSTITUTE_YEAR;
                }
                query = session.createQuery("from Form where " + fieldName + " = " + Integer.parseInt(searchText)
                        + " and ("
                        + " status.name = 'Зарегистрирована'"
                        + " or status.name = 'Прошел собеседование'"
                        + " or status.name = 'Не прошел собеседование'"
                        + ")"
                        );
                formList =  query.list();
            }
            else if(filter.equalsIgnoreCase("Кафедра") ||
                    filter.equalsIgnoreCase("ВУЗ") ||
                    filter.equalsIgnoreCase("Факультет")) {
                if(filter.equalsIgnoreCase("Кафедра")) {
                    fieldName = CATHEDRA;
                }
                else if(filter.equalsIgnoreCase("Факультет")) {
                    fieldName = FACULTY;
                }
                else if(filter.equalsIgnoreCase("ВУЗ")) {
                    fieldName = INSTITUTE;
                }
                query = session.createQuery("from Form where upper(" + fieldName + ") like upper('%" + searchText +"%')"
                        + " and ("
                        + " status.name = 'Зарегистрирована'"
                        + " or status.name = 'Прошел собеседование'"
                        + " or status.name = 'Не прошел собеседование'"
                        + ")"
                        );
                formList =  query.list();
            }            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return formList;
    }
    

}
