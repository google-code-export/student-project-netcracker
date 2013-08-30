/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import ua.netcrackerteam.DAO.Entities.Form;
import ua.netcrackerteam.DAO.Entities.Interview;
import ua.netcrackerteam.DAO.Entities.InterviewRes;
import ua.netcrackerteam.util.xls.entity.XlsUserInfo;

import java.util.List;

/**
 *
 * @author Kushnirenko Anna
 */
public interface DAOHR {
    /*
     * Search of student forms by selected category and value
     */
    public List<XlsUserInfo> search(String category, String value);
    /*
     * Sets general mark to student form
     */
    public void setHRMark(int selectedFormID, String insertedMark, String userNameHR);
    /*
     * Gets all forms with status "registered"
     */
    public List<Form> getAllRegisteredForms();
    /*
     * Gets all edited forms 
     */
    public List<Form> getNonVerificatedForms();
    /*
     * Set form status to "registered"
     */
    public void verificateForm(int formID);
    public void deleteForm(int formID);
    /*
     * Set status of student attendance to true or false
     * @param statusID = 4 if student was at the interview
     * and statusID = 2 if he wasn't.
     */
    public void setStudentAttendStatus(int statusID, int formID);
    /*
     * Gets list of all interviewers marks for selected student
     */
    public List<InterviewRes> getInterviewersMarks(int selectedFormID);
    /*
     * Gets username of interviewer by his id
     */
    public String getInterviewerNameByID(int userID);
    public void addNewInterview(Interview newInterview);
    public void deleteInterview(int interviewId);
    public void editInterview(Interview interview);
} 
