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
    private int maxStudents;
    private int interviewersNum;

    public StudentInterview(int studentInterviewId, Date interviewStartDate, Date interviewEndDate, int restOfPositions, int maxStudents, int interviewersNum) {
        this.studentInterviewId = studentInterviewId;
        this.interviewStartDate = interviewStartDate;
        this.interviewEndDate = interviewEndDate;
        this.restOfPositions = restOfPositions;
        this.maxStudents = maxStudents;
        this.interviewersNum = interviewersNum;
    }

    public void setInterviewersNum(int interviewersNum) {
        this.interviewersNum = interviewersNum;
    }

    public int getInterviewersNum() {
        return interviewersNum;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
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
