package ua.netcrackerteam.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import ua.netcrackerteam.DAO.DAOHRImpl;
import ua.netcrackerteam.DAO.DAOInterviewerImpl;
import ua.netcrackerteam.DAO.Form;
import ua.netcrackerteam.applicationForm.ApplicationForm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 12.03.13
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
public class HRPage {

    public static byte[] getPdfForView(int formID) {
        return new ApplicationForm(formID).generateFormPDF();
    }

    private static List<StudentDataShort> getStudentDataList(List<Form> forms) {
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        StudentDataShort stDataShort = null;
        for(Form form : forms) {
            stDataShort = new StudentDataShort();
            stDataShort.setIdForm(form.getIdForm());
            stDataShort.setStudentLastName(form.getLastName());
            stDataShort.setStudentCathedra(form.getCathedra().toString());
            stDataShort.setStudentFaculty(form.getCathedra().getFaculty().toString());
            stDataShort.setStudentInstitute(form.getCathedra().getFaculty().getInstitute().toString());
            stDataShort.setStudentFirstName(form.getFirstName());
            stDataShort.setStudentMiddleName(form.getMiddleName());
            stDataShort.setStudentInstituteCourse(form.getInstituteYear().toString());
            studentList.add(stDataShort);
        }
        return studentList;
    }

    public static List<StudentDataShort> getAllForms() {
        List<Form> allForms = new DAOHRImpl().getAllRegisteredForms();
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }
        return studentList;
    }

    public static List<StudentDataShort> getNonVerificatedForms() {
        List<Form> allForms = new DAOHRImpl().getNonVerificatedForms();
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }
        return studentList;
    }

    public static List<StudentDataShort> getStudentsByInterviewID (int interviewID) {
        List<Form> allForms = new DAOInterviewerImpl().getAllFormsByInterview(interviewID);
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }
        return studentList;
    }

    public static String getStudentMark(int formID, String interviewerUsername) {
        return new DAOInterviewerImpl().getStudentInterviewMark(formID,interviewerUsername);
    }

    public static void setStudentMark(int formID, String hrName, String Mark) {
        new DAOHRImpl().setHRMark(formID, Mark, hrName);
    }

    public static void deleteStudentBlank(int formID) {
        new DAOHRImpl().deleteForm(formID);
    }

    public static void verificateForm(Integer formID) {
        new DAOHRImpl().verificateForm(formID);
    }

    public static List<StudentDataShort> searchStudents(String searchFilter, String value) {
        List<Form> allForms = new DAOInterviewerImpl().search(searchFilter, value);
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }
        return studentList;
    }
    
    /*
     * Anna
     */
    public static List<HRInterview> getInterviewsList() {
        List<HRInterview> intervList = new ArrayList<HRInterview>();
        List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
        for(Interview interview : interviews) {
            HRInterview hrInterview = new HRInterview();
            
            hrInterview.setId(interview.getIdInterview());
            
            Date startDate = interview.getStartDate();
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");      
            String strDate = formatter.format(startDate);
            hrInterview.setDate(strDate);
            
            formatter = new SimpleDateFormat("HH:mm");     
            String strStartTime = formatter.format(startDate);
            hrInterview.setStartTime(strStartTime);
            
            String strEndTime = formatter.format(interview.getEndDate());
            hrInterview.setEndTime(strEndTime);
            
            hrInterview.setInterviewersNum(interview.getInterviwerNumber());
            hrInterview.setPositionNum(interview.getMaxNumber());
            
            List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
            int  amountStudentsToInterview = (forms == null? 0: forms.size()); 
            hrInterview.setRestOfPositions(hrInterview.getPositionNum() - amountStudentsToInterview);
            
            intervList.add(hrInterview);
        }
        return intervList;
    }
    
    public static int getRecommendedStudentsNum(Date start, Date end, int duration, int intervCount) {
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(start);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(end);
        long diff = calEnd.getTimeInMillis() - calStart.getTimeInMillis();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        double num = minutes/duration*intervCount;
        return (int) Math.round(num);
    }
    
    public static void saveNewInterview(Date start, Date end, int intervNum, int maxStudents) {
        Interview interview = new Interview();
        interview.setEndDate(end);
        interview.setStartDate(start);
        interview.setInterviwerNumber(intervNum);
        interview.setMaxNumber(maxStudents);
        new DAOHRImpl().addNewInterview(interview);
    }
    
    public static void deleteInterview(int idInterview) {
        new DAOHRImpl().deleteInterview(idInterview);
    }
    
    public static void editInterview(int id, Date start, Date end, int intervNum, int maxStudents) {
        Interview interview = new Interview();
        interview.setIdInterview(id);
        interview.setEndDate(end);
        interview.setStartDate(start);
        interview.setInterviwerNumber(intervNum);
        interview.setMaxNumber(maxStudents);
        new DAOHRImpl().editInterview(interview);
    }
    
    public static void main(String[] args) {
        System.out.println(getInterviewsList());
    }

}
