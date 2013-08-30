/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller.bean;

import sunw.io.Serializable;

import java.util.Date;

/**
 * Class-controller for Student Entity with necessary information for use in GUI
 *
 * @author Anna Kushnirenko
 */
public class StudentInterview implements Serializable{

    private static final long serialVersionUID = 1L;

    private int studentInterviewId;
    private String interviewStartDay;
    private Date interviewStartDate;
    private String startTime;
    private String endTime;
    private int restOfPositions;

    public StudentInterview(int studentInterviewId, int restOfPositions) {
        this.setStudentInterviewId(studentInterviewId);
        this.setRestOfPositions(restOfPositions);
    }

    public int getStudentInterviewId() {
        return studentInterviewId;
    }

    public void setStudentInterviewId(int studentInterviewId) {
        this.studentInterviewId = studentInterviewId;
    }

    public int getRestOfPositions() {
        return restOfPositions;
    }

    public void setRestOfPositions(int restOfPositions) {
        this.restOfPositions = restOfPositions;
    }



    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return getInterviewStartDay();
    }

    public String getInterviewStartDay() {
        return interviewStartDay;
    }

    public void setInterviewStartDay(String interviewStartDay) {
        this.interviewStartDay = interviewStartDay;
    }

    public Date getInterviewStartDate() {
        return interviewStartDate;
    }

    public void setInterviewStartDate(Date interviewStartDate) {
        this.interviewStartDate = interviewStartDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentInterview)) return false;

        StudentInterview interview = (StudentInterview) o;
        if (studentInterviewId != interview.getStudentInterviewId()) return false;
        return true;
    }
}
