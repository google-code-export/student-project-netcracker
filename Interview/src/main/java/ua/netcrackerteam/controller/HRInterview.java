/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

/**
 *
 * @author Kushnirenko Anna
 */
public class HRInterview {

    private String date;
    private String startTime;
    private String endTime;
    private int interviewersNum;
    private int positionNum;
    private int restOfPositions;

    public String getDate() {
        return date;
    }
    
    public int getInterviewersNum() {
        return interviewersNum;
    }

    public int getPositionNum() {
        return positionNum;
    }

    public int getRestOfPositions() {
        return restOfPositions;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInterviewersNum(int interviewersNum) {
        this.interviewersNum = interviewersNum;
    }

    public void setPositionNum(int positionNum) {
        this.positionNum = positionNum;
    }

    public void setRestOfPositions(int restOfPositions) {
        this.restOfPositions = restOfPositions;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    
}
