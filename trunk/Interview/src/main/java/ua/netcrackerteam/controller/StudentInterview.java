/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

import java.util.Date;

/**
 * Class-controller for Student Entity with necessary information for use in GUI
 * @author Anna Kushnirenko
 */
public class StudentInterview {
    
    private int studentInterviewId;
    private Date interviewStartDate;
    private Date interviewEndDate;
    private int restOfPositions;

    public StudentInterview(int studentInterviewId, Date interviewStartDate, Date interviewEndDate, int restOfPositions) {
        this.studentInterviewId = studentInterviewId;
        this.interviewStartDate = interviewStartDate;
        this.interviewEndDate = interviewEndDate;
        this.restOfPositions = restOfPositions;
    }

    public int getStudentInterviewId() {
        return studentInterviewId;
    }

    public Date getInterviewStartDate() {
        return interviewStartDate;
    }

    public Date getInterviewEndDate() {
        return interviewEndDate;
    }

    public int getRestOfPositions() {
        return restOfPositions;
    }

    public void setStudentInterviewId(int studentInterviewId) {
        this.studentInterviewId = studentInterviewId;
    }

    public void setInterviewStartDate(Date interviewStartDate) {
        this.interviewStartDate = interviewStartDate;
    }

    public void setInterviewEndDate(Date interviewEndDate) {
        this.interviewEndDate = interviewEndDate;
    }

    public void setRestOfPositions(int restOfPositions) {
        this.restOfPositions = restOfPositions;
    }
    
    
    
}
