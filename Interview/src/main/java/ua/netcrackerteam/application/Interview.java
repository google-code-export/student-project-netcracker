/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.application;

import java.util.Date;
import java.util.Observable;

/**
 * Класс Interview отслеживает наблюдателей - Анкеты 
 * @author tanya
 */

public class Interview extends Observable{
    
 
    private Date dateTimeStart;
    private Date dateTimeEnd;
    private int maxStudents;
    
     /**
     * Получение времени начала собеседования
     * @return Date date_time_start
     */
    public Date getDateTimeStart() {
        return dateTimeStart;
    }
    /**
     * Получение времени окончания собеседования
     * @return Date date_time_end
     */
    public Date getDateTimeEnd() {
        return dateTimeEnd;
    }
    
    /**
     * Получение максимального количества студентов на период времени
     * @return max_students
     */
    public int getMaxStudents() {
        return maxStudents;
    }
    
    /**
     * Установка времени начала собеседования 
     * @param date_time_start 
     */
    public void setDateTimeStart(Date dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }
    
     /**
     * Установка времени окончания собеседования 
     * @param date_time_start 
     */
    public void setDateTimeEnd(Date dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }
    
    /**
     * Установка максимального количества студентов на период времени
     * @param maxStudents 
     */
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }
    
    
    
    
}