package ua.netcrackerteam.DAO;

import java.util.List;
import java.util.Locale;
import javax.interceptor.Interceptors;
import org.hibernate.Query;
import org.hibernate.Session;
import ua.netcrackerteam.configuration.HibernateUtil;
import ua.netcrackerteam.configuration.ShowHibernateSQLInterceptor;

/**
 *
 * @author Zhokha Maksym
 */
public class DAOInterviewerImpl implements DAOInterviewer
{
    
    /**
     * Returns forms of students which don't have marks from this interviewer
     * @param idInterview id of interview assigned for students
     * @param interviewerUsername username of interviewer 
     * @return list of form objects related to the specified interview
     */    
    @Override
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public List<Form> getFormsWithoutMark(int idInterview, String interviewerUsername) {
//        select f.id_form from form f, interview i
//        where 
//        f.id_interview = i.id_interview
//        minus
//        select f.id_form from form f, interview i, interview_res ir
//        where f.id_form = ir.id_form and
//        f.id_interview = i.id_interview
//        and ir.score is not null;

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
                                        + "where userName = '" + "userName" + "'");
            user = (UserList) query.uniqueResult();
            idUser = user.getIdUser();           
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        throw new UnsupportedOperationException("Not supported yet.");        
    }
    
    

    /**
     * Returns list of form objects related to the specified interview and
     * have marks from specified interviewer
     * @param idInterview id of interview assigned for students
     * @param interviewerUsername username of interviewer 
     * @return list of form objects related to the specified interview and
     * have marks from specified interviewer
     */
    @Override
    @Interceptors(ShowHibernateSQLInterceptor.class)
    public List<Form> getFormsWithMark(int idInterview, String interviewerUsername) {
//        select f.id_form from form f, interview i, interview_res ir
//        where f.id_form = ir.id_form and
//        f.id_interview = i.id_interview
//        and ir.score is not null;
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
