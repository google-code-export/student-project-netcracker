/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import java.util.List;

/**
 *
 * @author Kushnirenko Anna
 */
public interface DAOHR {
    
    public List<Form> search(String category, String value); 
    public void setHRMark(int selectedFormID, String insertedMark, String userNameHR);
    public List<Form> getAllRegisteredForms();
    public List<Form> getNonVerificatedForms();
    public void verificateForm(int formID);
    
} 
