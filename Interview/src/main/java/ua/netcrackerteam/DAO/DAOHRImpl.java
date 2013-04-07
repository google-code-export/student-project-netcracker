package ua.netcrackerteam.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.DAO.Entities.*;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Kushnirenko Anna, Filipenko Aleksey
 */
public class DAOHRImpl extends DAOCoreObject implements DAOHR {
    
    public static void main(String[] args) {
        
        DAOHRImpl test = new DAOHRImpl();
        test.getAllRegisteredForms();

    }

    @Override
    public List<Form> search(String category, String value) {
        //Session session = null;
        //Query query;
        //String query = "";
        //List<Form> formList = null;
        //List listOfParam = new ArrayList();
        /*try {
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
        }*/
        String query = "";
        List listOfParam = new ArrayList();
        value = '%'+value+'%';
        beginTransaction();
        if (category.equals("institute")) {
            listOfParam.add(value);
            query = "from Form where status = 1 and interview is not null and upper(cathedra.faculty.institute.name) like upper(:param0)";
        }
        else if (category.equals("faculty")) {
            listOfParam.add(value);
            query = "from Form where status = 1 and interview is not null and upper(cathedra.faculty.name) like upper(:param0)";
        }
        else if (category.equals("cathedra")) {
            listOfParam.add(value);
            query = "from Form where status = 1 and interview is not null and upper(cathedra.name) like upper(:param0)";
        }
        else {
            listOfParam.add(category);
            listOfParam.add(value);
            query = "from Form where status = 1 and interview is not null and upper(:param0) like upper(:param1)";
        }
        List<Form> formList = super.<Form>executeListGetQuery(query, listOfParam);
        commitTransaction();
        return formList;
    }
    
    @Override
    public void setHRMark(int selectedFormID, String insertedMark, String userNameHR) {
        /*Session session = null;
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
        }*/
        String query                        = "";
        InterviewRes currInterviewResult    = null;
        Form currFormForInsertResults       = null;
        UserList currHRUser                 = null;
        beginTransaction();
        List listOfParams = new ArrayList();
        listOfParams.add(selectedFormID);
        listOfParams.add(userNameHR);
        query = "from InterviewRes where form = :param0 and user.userName = :param1";
        currInterviewResult = super.<InterviewRes>executeSingleGetQuery(query, listOfParams);
        listOfParams.clear();
        if (currInterviewResult != null) {
            currInterviewResult.setScore(insertedMark);
        }
        else {
            currInterviewResult = new InterviewRes();
            listOfParams.add(selectedFormID);
            listOfParams.add(selectedFormID);
            query = "from Form where to_char(idForm) = to_char(:param0)";
            currFormForInsertResults = super.<Form>executeSingleGetQuery(query, listOfParams);
            currInterviewResult.setForm(currFormForInsertResults);
            listOfParams.clear();
            query = "from UserList where userName = :param0";
            listOfParams.add(userNameHR);
            currHRUser = super.<UserList>executeSingleGetQuery(query, listOfParams);
            currInterviewResult.setIdUser(currHRUser);
            currInterviewResult.setScore(insertedMark);
            super.saveUpdatedObject(currInterviewResult);
        }
        commitTransaction();

    }

    public UserList getUserDataByFormId(int formId) {
        /*Session session = null;
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

        return selectedUser;*/
        String query            = "";
        List listOfParams       = new ArrayList();
        Form userForm           = null;
        UserList selectedUser   = null;
        beginTransaction();
        query = "from Form where to_char(idForm) = to_char(:param0)";
        listOfParams.add(formId);
        userForm = super.<Form>executeSingleGetQuery(query, listOfParams);
        selectedUser = userForm.getUser();
        commitTransaction();
        return selectedUser;
    }
 
    @Override
    public List<Form> getAllRegisteredForms() {
        /*Session session = null;
        Query query;                
        List<Form> formList = null;        
        try {
            //Locale.setDefault(Locale.ENGLISH);
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
        return formList;*/
        String query                = "";
        List<Form> selectedForms    = null;
        beginTransaction();
        query = "from Form where status = 1 and interview is not null";
        selectedForms = super.<Form>executeListGetQuery(query);
        commitTransaction();
        return selectedForms;
    }

    public List<HrTempInfo> getHrTempInfo() {
        /*Session session = null;
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
        return tempInfos;*/
        String query                = "";
        List<HrTempInfo> selectedInfo     = null;
        beginTransaction();
        query = "from HrTempInfo";
        selectedInfo = super.<HrTempInfo>executeListGetQuery(query);
        commitTransaction();
        return selectedInfo;
    }

    public HrTempInfo getHrTempInfoByFormID(int formID) {
        /*Session session = null;
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
        return tempInfos;*/
        String query                = "";
        HrTempInfo selectedInfo     = null;
        List listOfParams           = new ArrayList();
        beginTransaction();
        query = "from HrTempInfo where to_char(form.idForm) = to_char(:param0)";
        listOfParams.add(formID);
        selectedInfo = super.<HrTempInfo>executeSingleGetQuery(query, listOfParams);
        commitTransaction();
        return selectedInfo;
    }

    public void setHrTempInfo(HrTempInfo hrTempInfo){
        /*Session session = null;
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
        }*/
        beginTransaction();
        super.<HrTempInfo>saveUpdatedObject(hrTempInfo);
        commitTransaction();
    }

    /*public void updateHrTempInfo(HrTempInfo hrTempInfo){
        *//*Session session = null;
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
        }*//*

    }*/

    public void deleteHrTempInfo(int tempInfoID){
        /*Session session = null;
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
        }*/
        String query        = "";
        List listOfParam    = new ArrayList();
        HrTempInfo currHRInfo = null;
        beginTransaction();
        query = "from HrTempInfo where to_char(idHrTempInfo) = to_char(:param0)";
        currHRInfo = super.<HrTempInfo>executeSingleGetQuery(query);
        super.<HrTempInfo>executeDeleteQuery(currHRInfo);
        commitTransaction();
    }

    public HrTempInfo getHrTempInfoByID(int tempInfoID){
        /*Session session = null;
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
        return hrTempInfo;*/
        String query        = "";
        List listOfParam    = new ArrayList();
        HrTempInfo currHRInfo = null;
        listOfParam.add(tempInfoID);
        beginTransaction();
        query = "from HrTempInfo where to_char(idHrTempInfo) = to_char(:param0)";
        currHRInfo = super.<HrTempInfo>executeSingleGetQuery(query, listOfParam);
        return currHRInfo;
    }

    @Override
    public List<Form> getNonVerificatedForms() {
        /*Session session = null;
        Query query;
        Query queryForm;
        List<Form> formList = null;        
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            query = session.createQuery("from Status where idStatus =5");
            Status currStatus = (Status) query.uniqueResult();
            queryForm = session.createQuery("from Form where status = " + currStatus.getIdStatus());
            formList =  queryForm.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return formList;*/
        String query            = "";
        String queryForm        = "";
        Status currStatus       = null;
        List listOfParams       = new ArrayList();
        List<Form> listOfForms  = null;
        beginTransaction();
        query = "from Status where idStatus =5";
        currStatus = super.<Status>executeSingleGetQuery(query);
        listOfParams.add(currStatus);
        queryForm = "from Form where status = :param0";
        listOfForms = super.<Form>executeListGetSQLQuery(queryForm, listOfForms);
        commitTransaction();
        return listOfForms;
    }

    @Override
    public void verificateForm(int formID) {
        /*Session session = null;
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
        }*/
        String query            = "";
        Status currStatus       = null;
        List listOfParams       = new ArrayList();
        Form currForm           = null;
        beginTransaction();
        query = "from Status where idStatus = 1";
        currStatus = super.<Status>executeSingleGetQuery(query);
        listOfParams.add(formID);
        query = "from Form where to_char(idForm) = to_char(:param0)";
        currForm = super.<Form>executeSingleGetQuery(query, listOfParams);
        UserList currUser = currForm.getUser();
        listOfParams.clear();
        query = "from Form where user = :param0 and status = :param1";
        listOfParams.add(currUser);
        listOfParams.add(currStatus);
        Form oldForm = super.<Form>executeSingleGetQuery(query, listOfParams);
        super.<Form>executeDeleteQuery(oldForm);
        commitTransaction();
    }

    @Override
    public void deleteForm(int formID) {
        /*Session session = null;
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
        }*/
        String query            = "";
        List listOfParams       = new ArrayList();
        Form currForm           = null;
        beginTransaction();
        query = "from Form where to_char(idForm) = to_char(:param0)";
        listOfParams.add(formID);
        currForm = super.<Form>executeSingleGetQuery(query, listOfParams);
        super.<Form>executeDeleteQuery(currForm);
        commitTransaction();
    }

    @Override
    public void setStudentAttendStatus(int statusID, int formID) {
        /*Session session = null;
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
        }*/
        String query            = "";
        List listOfParams       = new ArrayList();
        Form currForm           = null;
        beginTransaction();

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
            query = session.createQuery("from Form where interview = " + interviewId);
            List<Form> forms = query.list();
            query = session.createQuery("from Interview where idInterview = " + 0);
            Interview nullInterview = (Interview) query.uniqueResult();
            for(Form form: forms) {
                form.setInterview(nullInterview);
            }
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
    
    
    
    
    
    
    
 

