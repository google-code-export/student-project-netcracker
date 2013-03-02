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
       
   //Получает все интервью 
   public List<Interview> getInterview(){
       
       List<Interview> interwievs = new  ArrayList<Interview>();
        Interview interwiev = new Interview();
        interwiev.setStartDate(new Date(2012, 9, 28, 17, 30));
        interwiev.setEndDate(new Date(2012, 9, 28, 18, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
         interwiev.setStartDate(new Date(2012, 9, 28, 18, 30));
        interwiev.setEndDate(new Date(2012, 9, 28, 19, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
        interwiev.setStartDate(new Date(2012, 9, 29, 17, 30));
        interwiev.setEndDate(new Date(2012, 9, 29, 18, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
        interwiev.setStartDate(new Date(2012, 9, 29, 18, 30));
        interwiev.setEndDate(new Date(2012, 9, 29, 19, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
        interwiev.setStartDate(new Date(2012, 9, 30, 17, 30));
        interwiev.setEndDate(new Date(2012, 9, 30, 18, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
        interwiev.setStartDate(new Date(2012, 9, 30, 18, 30));
        interwiev.setEndDate(new Date(2012, 9, 30, 19, 30));
        interwiev.setMaxNumber(40);
        interwievs.add(interwiev);
      
       return interwievs;
   } 
   
   //Получает интервью по конкретной дате 
   public Interview getInterview(Date startInterview){
      return new Interview(); 
   } 
   
   
}
