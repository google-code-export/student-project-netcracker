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
     * Unique identifier for form
     */
    public int idForm;
    /**
     * interview
     */
    public Observable interview;
    
    /**
     * Called when the interview was changed by hr
     */
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Checking the filling of fields
     * @return true/false
     */
    public boolean checkFields(){
        return true;
    }
    /**    
     * Check size photo (max size 300 kb)
     * @return true/false
     */
    public boolean checkSizePhoto(){
        return true;
    }
    
    /**
     * Create from (pdf-format)
     */
    public void generateFormPDF(){
        
    }
 
    
}
