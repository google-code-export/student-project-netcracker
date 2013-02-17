/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author home
 */
public class InterviewDAOImpl {
       
   //get all interview 
   public List<Interview> getInterview(){
       return new ArrayList<Interview>();
   } 
   
   //get interview 
   public Interview getInterview(Date startInterview){
      return new Interview(); 
   } 
}
