/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.application;

import java.util.Date;
import java.util.Observable;

/**
 * @author tanya
 */

public class Interview extends Observable{
    
    
    private Date dateTimeStart;
    private Date dateTimeEnd;
    private int maxStudents;
    
     /**
     * Get date and time start of the interview
     * @return Date dateTimeStart
     */
    public Date getDateTimeStart() {
        return dateTimeStart;
    }
    /**
     * Get date and time end of the interview
     * @return Date dateTimeEnd
     */
    public Date getDateTimeEnd() {
        return dateTimeEnd;
    }
    
    /**
     * Get max students of the interview
     * @return maxStudents
     */
    public int getMaxStudents() {
        return maxStudents;
    }
    
    /**
     * Set date and time start of the interview 
     * @param dateTimeStart 
     */
    public void setDateTimeStart(Date dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }
    
     /**
     * Set date and time end of the interview
     * @param dateTimeEnd
     */
    public void setDateTimeEnd(Date dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }
    
    /**
     * Set max students of the interview
     * @param maxStudents 
     */
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }
    
    
    
    
}