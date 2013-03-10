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
    
    List<Form> search(String category, String value); 
    void setHRMark(int selectedFormID, String insertedMark, String userNameHR);
}
