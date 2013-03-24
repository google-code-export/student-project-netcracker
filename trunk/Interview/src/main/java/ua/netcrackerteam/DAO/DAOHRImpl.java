package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.List;
import java.util.Locale;

/**
 * @author Kushnirenko Anna
 */
public class DAOHRImpl implements DAOHR{
    
    public static void main(String[] args) {
//        DAOHRImpl test = new DAOHRImpl();
        //test.setHRMark(233, "молодец (HR)", "HR");
        //System.out.println(test.search("institute", "ОДЕСЬкий"));
//        test.deleteInterview(1050);
        
        
//        DAOHRImpl test = new DAOHRImpl();
//        Institute inst = test.addInstitute("Тестовый институт");
//        Faculty fac = test.addFaculty(inst, "Факультет тестового института");
//        Cathedra cat = test.addCathedra(fac, "Кафедра тестового института");
        getDiff(2300);

    }

    @Override
    public List<Form> search(String category, String value) {
        Session session = null;
        Query query;
        List<Form> formList = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String queryStart = "from Form where status = 1 and interview is not null and upper(";
            if (category.equals("institute")) {
               query = session.createQuery(queryStart + "cathedra.faculty.institute.name) like upper('%" + value + "%')");
            } else if (category.equals("faculty")) {
               query = session.createQuery(queryStart + "cathedra.faculty.name) like upper('%" + value + "%')");
            } else if (category.equals("cathedra")) {
               query = session.createQuery(queryStart + "cathedra.name) like upper('%" + value + "%')");
            } else {
                query = session.createQuery(queryStart + category + ") like upper('%" + value + "%')");
            }
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
    public void setHRMark(int selectedFormID, String insertedMark, String userNameHR) {
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        try {
            InterviewRes interviewRes = null;
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            query = session.createQuery("from InterviewRes where form = "
                    + selectedFormID
                    + " and user.userName = '" +  userNameHR +"'");
            interviewRes =  (InterviewRes) query.uniqueResult();
            if(interviewRes != null) {
                interviewRes.setScore(insertedMark);    
            } else {                            
                interviewRes = new InterviewRes();
                query = session.createQuery("from Form where idForm = " + selectedFormID);
                Form selectedForm = (Form) query.uniqueResult();
                interviewRes.setForm(selectedForm);
                query = session.createQuery("from UserList where userName = '" +userNameHR+"'");
                UserList hr = (UserList) query.uniqueResult();
                interviewRes.setIdUser(hr);
                interviewRes.setScore(insertedMark);                
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

    public UserList getUserDataByFormId(int formId) {
        String userName = "";
        Session session = null;
        UserList selectedUser = null;
        Query query;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from Form where idForm = " + formId);
            Form currForm = (Form)query.uniqueResult();
            selectedUser = (UserList)currForm.getUser();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return selectedUser;
    }
 
    @Override
    public List<Form> getAllRegisteredForms() {
        Session session = null;
        Query query;                
        List<Form> formList = null;        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();            
            query = session.createQuery("from Form where status = 1 and interview is not null");
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

    public List<HrTempInfo> getHrTempInfo() {
        Session session = null;
        Query query;
        List<HrTempInfo> tempInfos = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from HrTempInfo");
            tempInfos =  query.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tempInfos;
    }

    public HrTempInfo getHrTempInfoByFormID(int formID) {
        Session session = null;
        Query query;
        HrTempInfo tempInfos = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from HrTempInfo where form.idForm = " + formID);
            tempInfos = (HrTempInfo) query.uniqueResult();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tempInfos;
    }

    public void setHrTempInfo(HrTempInfo hrTempInfo){
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(hrTempInfo);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateHrTempInfo(HrTempInfo hrTempInfo){
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(hrTempInfo);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteHrTempInfo(int tempInfoID){
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            query = session.createQuery("from HrTempInfo where idHrTempInfo = " + tempInfoID);
            HrTempInfo selectedHrTempInfo = (HrTempInfo) query.uniqueResult();
            session.delete(selectedHrTempInfo);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public HrTempInfo getHrTempInfoByID(int tempInfoID){
        Session session = null;
        Query query;
        HrTempInfo hrTempInfo = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from HrTempInfo"
                    + " where idHrTempInfo = " + tempInfoID);
            hrTempInfo = (HrTempInfo) query.uniqueResult();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return hrTempInfo;
    }

    @Override
    public List<Form> getNonVerificatedForms() {
        Session session = null;
        Query query;                
        List<Form> formList = null;        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();            
            query = session.createQuery("from Form where status = 5");
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
    public void verificateForm(int formID) {
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();   
            query = session.createQuery("from Status where idStatus = " + 1);
            Status status = (Status) query.uniqueResult();
            query = session.createQuery("from Form where idForm = " + formID);
            Form selectedForm = (Form) query.uniqueResult();
            UserList user = selectedForm.getUser();
            query = session.createQuery("from Form where user = " + user.getIdUser() 
                    + " and status = " + status.getIdStatus());
            Form oldForm = (Form) query.uniqueResult();
            session.delete(oldForm);
            selectedForm.setStatus(status);
            session.save(selectedForm);
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
    public void deleteForm(int formID) {
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();   
            query = session.createQuery("from Form where idForm = " + formID);
            Form selectedForm = (Form) query.uniqueResult();
            session.delete(selectedForm);
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
    public void setStudentAttendStatus(int statusID, int formID) {
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();   
            query = session.createQuery("from Form where idForm = " + formID);
            Form selectedForm = (Form) query.uniqueResult();
            query = session.createQuery("from Status where idStatus = " + statusID);
            Status status = (Status) query.uniqueResult();
            selectedForm.setStatusAttend(status);
            session.save(selectedForm);
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
    public List<InterviewRes> getInterviewersMarks(int selectedFormID) {
        Session session = null;
        Query query;                
        List<InterviewRes> marks = null;        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();    
            query = session.createQuery("from InterviewRes"
                    + " where form = " + selectedFormID + " and user IN "
                    + "(select idUser from UserList where idUserCategory = 3)");
            marks = query.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return marks;
    }

    public List<InterviewRes> getAllStudentsMarks(int selectedFormID) {
        Session session = null;
        Query query;
        List<InterviewRes> marks = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from InterviewRes"
                    + " where form = " + selectedFormID + " and user IN "
                    + "(select idUser from UserList where (idUserCategory = 3) or (idUserCategory = 2))");
            marks = query.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return marks;
    }

    @Override
    public String getInterviewerNameByID(int userID) {
        Session session = null;
        Query query;
        String name = "";        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();    
            query = session.createQuery("from UserList"
                    + " where idUser = " + userID);
            UserList ul = (UserList) query.uniqueResult();
            name = ul.getUserName();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return name;
    }

    @Override
    public void addNewInterview(Interview newInterview) {
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(newInterview);
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
    public void deleteInterview(int interviewId) {
        Session session = null;
        Query query = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();   
            query = session.createQuery("from Interview where idInterview = " + interviewId);
            Interview selectedInterview = (Interview) query.uniqueResult();
            session.delete(selectedInterview);
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
    public void editInterview(Interview interview) {
        Session session = null;
        Transaction transaction = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();   
            session.update(interview);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    
    //Maksym added here bellow
    
    public Institute addInstitute(String instituteName) {
        Session session = null;
        Transaction transaction = null;
        Institute institute = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            institute = new Institute();
            institute.setName(instituteName);
            session.save(institute);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return institute;
    }
    
    
    public Faculty addFaculty(Institute institute, String facultyName) {
        Session session = null;
        Transaction transaction = null;
        Faculty faculty = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            faculty = new Faculty();
            faculty.setInstitute(institute);
            faculty.setName(facultyName);            
            session.save(faculty);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return faculty;
    }
    
    
    public Cathedra addCathedra(Faculty faculty, String cathedraName) {
        Session session = null;
        Transaction transaction = null;
        Cathedra cathedra = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            cathedra = new Cathedra();
            cathedra.setFaculty(faculty);
            cathedra.setName(cathedraName);
            session.save(cathedra);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cathedra;
        
    }

    public static void getDiff(int currUserId) {
        Query query;
        Session session = null;
        Transaction transaction = null;
        List columnData = null;
        String queryText = "";
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            query = session.createSQLQuery("select column_name from ALL_TAB_COLUMNS where table_name = 'FORM'");
            columnData = query.list();
            Boolean first = true;
            for (Object colunmName:columnData) {
                String currColumnName = (String)colunmName;
                if (!((String) colunmName).toLowerCase().equals("photo")) {
                String currcolumnQuery = "select * from (select '" + currColumnName + "', to_char(f1." + currColumnName + "), to_char(f2." + currColumnName + ")" +
                        "from form f1, form f2 where" +
                        "(f1.id_user = f2.id_user) and (f1.id_user = "  + currUserId + " ) " +
                        "and (f1." + currColumnName + "<> f2." + currColumnName +")) where rownum = 1";
                if (first) {
                    queryText = currcolumnQuery;
                    first = false;
                }
                else {
                    queryText = queryText + " union " + currcolumnQuery;
                }
                }

            }

            query = session.createSQLQuery(queryText);
            columnData = query.list();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }
    
    
    
    
    
    
    
 
}
