/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.application;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author tanya
 */
public class Form implements Observer{
    
    /**
     * Уникальный индификатор анкеты
     */
    private int id_form;
    
    /**
     * Вызывается Interview 
     */
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Проверка заполнения полей анкеты
     * @return true/false
     */
    public boolean checkFields(){
        return true;
    }
    /**
     * Проверка фотографии на ограничение по размеру
     * @return true/false
     */
    public boolean checkSizePhoto(){
        return true;
    }
    
    /**
     * Формирование анкеты в формате pdf
     */
    public void generateFormPDF(){
        
    }
 
    
}
