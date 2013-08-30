/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.DAOHRImpl;
import ua.netcrackerteam.DAO.DAOInterviewerImpl;
import ua.netcrackerteam.DAO.Entities.Form;
import ua.netcrackerteam.DAO.Entities.UserList;
import ua.netcrackerteam.applicationForm.ApplicationForm;
import ua.netcrackerteam.applicationForm.CreateLetterWithPDF;
import ua.netcrackerteam.applicationForm.Letter;
import ua.netcrackerteam.applicationForm.LetterReserv;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.controller.bean.StudentDataShort;
import ua.netcrackerteam.util.xls.entity.XlsUserInfo;

import java.util.ArrayList;
import java.util.Iterator;
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

    public static List<XlsUserInfo> getStudentsByInterviewID (int interviewID) {
        DAOHRImpl currDAO = new DAOHRImpl();
        List<XlsUserInfo> studentList = new ArrayList<XlsUserInfo>();
        studentList = currDAO.getXLSStudentInfoByInterviewID(interviewID);
        return studentList;
    }
    
    public static String getStudentMark(int formID, String interviewerUsername) {
        return new DAOInterviewerImpl().getStudentInterviewMark(formID,interviewerUsername);
    }
    
    public static void setStudentMark(int formID, String interviewerName, String Mark) {
        new DAOInterviewerImpl().saveStudentInterviewMark(formID, interviewerName, Mark);
    }

    public void saveInterMarks(XlsUserInfo userInfo) {
        DAOInterviewerImpl currDAO = new DAOInterviewerImpl();
        currDAO.saveStudentInterviewMark(userInfo);
    }
    
    public static List<XlsUserInfo> searchStudents(String searchFilter, String value) {
        /*List<Form> allForms = new DAOInterviewerImpl().search(searchFilter, value);
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }
        return studentList;*/
        List<XlsUserInfo> allForms = new DAOHRImpl().search(searchFilter, value);
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        /*if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }*/
        return allForms;
    }
    
    public static void sendLetterToStudentWithFormToReservInterview(){   
    	
         List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsToReservInterview();
         if(forms == null){
        	 return;}
         Iterator<Form> iterator = forms.iterator();
         while(iterator.hasNext()){
        	 Form form = iterator.next();
        	 UserList user = form.getUser(); 
        	 Letter letter = new LetterReserv(user.getUserName());
        	 CreateLetterWithPDF sendLetter = new CreateLetterWithPDF(user.getUserName(), letter);
        	 sendLetter.sendPDFToStudent();
         }
    }

}
