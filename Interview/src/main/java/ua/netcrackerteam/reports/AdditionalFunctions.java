/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.reports;

import java.util.ArrayList;

/**
 *
 * @author tanya
 */
public interface AdditionalFunctions {
    
    /**
     * Search in a report on the set field and value , return result of search
     * @param field
     * @param value
     * @return ArrayList
     */
    public ArrayList search(String field, String value);
    /**
     * Sort in a report on the set field in ascenting or decrease, return result of search
     * @param typeOfSort
     * @return ArrayList
     */
    public ArrayList sort (String field, SortingTypes typeOfSort);
    /**
     * Reflection of information of report
     * @param dataForView 
     */
    public void viewReport(ArrayList dataForView);
    
}
