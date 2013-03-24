/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.DAOInterviewerImpl;
import ua.netcrackerteam.DAO.Entities.Form;
import ua.netcrackerteam.applicationForm.ApplicationForm;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Anna Kushnirenko
 */
public class InterviewerPage {
    
    /**
     * Get student form in PDF format
     * @return byte array 
     */
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
    
    public static List<StudentDataShort> getAllStudents() {
        List<Form> allForms = new DAOInterviewerImpl().getAllBasicForms();
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
    
    public static void setStudentMark(int formID, String interviewerName, String Mark) {
        new DAOInterviewerImpl().saveStudentInterviewMark(formID, interviewerName, Mark);
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
