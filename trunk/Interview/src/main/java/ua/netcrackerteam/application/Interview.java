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
    
 
    private Date date_time_start;
    private Date date_time_end;
    private int max_students;
    
     /**
     * Получение времени начала собеседования
     * @return Date date_time_start
     */
    public Date getDate_time_start() {
        return date_time_start;
    }
    /**
     * Получение времени окончания собеседования
     * @return Date date_time_end
     */
    public Date getDate_time_end() {
        return date_time_end;
    }
    
    /**
     * Получение максимального количества студентов на период времени
     * @return max_students
     */
    public int getMax_students() {
        return max_students;
    }
    
    /**
     * Установка времени начала собеседования 
     * @param date_time_start 
     */
    public void setDate_time_start(Date date_time_start) {
        this.date_time_start = date_time_start;
    }
    
     /**
     * Установка времени окончания собеседования 
     * @param date_time_start 
     */
    public void setDate_time_end(Date date_time_end) {
        this.date_time_end = date_time_end;
    }
    
    /**
     * Установка максимального количества студентов на период времени
     * @param max_students 
     */
    public void setMax_students(int max_students) {
        this.max_students = max_students;
    }
    
    
    
    
}