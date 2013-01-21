/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.application;

import java.util.ArrayList;

/**
 *
 * @author tanya
 */
public class Report {

     private TypeOfViewReport report;
     
    /**
     * Search in a report on the set field and value , return result of search
     * @param field
     * @param value
     * @return ArrayList
     */
    public ArrayList search(String field, String value){
        return new ArrayList();
    }
    /**
     * Sort in a report on the set field in ascenting or decrease, return result of search
     * @param typeOfSort
     * @return ArrayList
     */
    public ArrayList sort (String field, SortingTypes typeOfSort){
        return new ArrayList();
    }
    /**
     * To print a report 
     */
    public void print(){
        
    }
      /**
     * To export a report 
     */
    public void export(){
        
    }
}
