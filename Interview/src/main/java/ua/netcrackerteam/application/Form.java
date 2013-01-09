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
    
    private int id_form;
    
    /**
     * Вызывается Interview при редактировании времени собеседований HR
     * @param 
     */
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Проверка заполнения полей анкеты
     * @return boolean check
     */
    public boolean checkFields(){
        return true;
    }
 
    
}
