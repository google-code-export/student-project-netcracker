/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

import java.util.ArrayList;
import java.util.List;
import ua.netcrackerteam.DAO.*;
import ua.netcrackerteam.applicationForm.ApplicationForm;
/**
 *
 * @author akush_000
 */
public class InterviewerPage {
    
    /**
     * Get student form in PDF format
     * @return byte array 
     */
    public static byte[] getPdfForView(int formID) {
        return new ApplicationForm(formID).pdfForView();
    }
    
    public static List<StudentDataShort> getAllStudents() {
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        StudentDataShort test2 = new StudentDataShort();
        Form form2 = new DAOStudentImpl().getFormByUserName("Anna");
        test2.setIdForm(form2.getIdForm());
        test2.setStudentLastName(form2.getLastName());
        test2.setStudentCathedra(form2.getCathedra().toString());
        test2.setStudentFaculty(form2.getCathedra().getFaculty().toString());
        test2.setStudentInstitute(form2.getCathedra().getFaculty().getInstitute().toString());
        test2.setStudentFirstName(form2.getFirstName());
        test2.setStudentMiddleName(form2.getMiddleName());
        test2.setStudentInstituteCourse(form2.getInstituteYear().toString());
        studentList.add(test2);
        return studentList;
    }
    
    public static List<StudentDataShort> getStudentsByInterviewID (int interviewID) {
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        return studentList;
    }
    
    public static String getStudentMark(int formID, String interviewerUsername) {
        return "";
        //return new DAOInterviewerImpl().getStudentInterviewMark(formID,interviewerUsername);
    }
    
    public static void setStudentMark(int formID, String interviewerName, String Mark) {
        new DAOInterviewerImpl().saveStudentInterviewMark(formID, interviewerName, Mark);
    }
    
    public static List<StudentDataShort> searchStudents(String searchFilter, String value) {
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        return studentList;
    }
    
    public static void main(String[] args) {
        
    }
}
