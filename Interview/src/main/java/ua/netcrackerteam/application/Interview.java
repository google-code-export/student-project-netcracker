/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.application;

import java.util.Date;
import java.util.Observable;

/**
 * Класс Interview  
 * Функции:
 *      Записаться на собеседование
 *      Изменить время собеседования
 *      Редактирование времени собеседований HR
 * @author tanya
 */

public class Interview extends Observable{
    
    private Date date_time_start;
    private Date date_time_end;
    
    private int max_students;
    
    
}