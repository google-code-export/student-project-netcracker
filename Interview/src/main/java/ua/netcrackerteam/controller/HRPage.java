package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.DAOHRImpl;
import ua.netcrackerteam.DAO.DAOInterviewerImpl;
import ua.netcrackerteam.DAO.Form;
import ua.netcrackerteam.applicationForm.ApplicationForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 12.03.13
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
public class HRPage {

    public static byte[] getPdfForView(int formID) {
        return new ApplicationForm(formID).pdfForView();
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

}
