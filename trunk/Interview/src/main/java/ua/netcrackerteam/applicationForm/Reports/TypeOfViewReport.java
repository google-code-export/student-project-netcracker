/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import java.awt.Image;
import java.util.List;

/**
 *
 * @author tanya
 */
public interface TypeOfViewReport {
     /**
     * Reflection of information of report
     * @param dataForView 
     */
    public byte[]  viewReport();
    public List    dataReport();
    public byte[]   getChart(int widht, int height);
}
