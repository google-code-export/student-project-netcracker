/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Table;
import ua.netcrackerteam.GUI.MainPage;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

/**
 *
 * @author home
 */
public abstract class ReportBuilder {
    
    protected Report report;
    
    public Report getReport(){
        return report;
    }
    public void createReport(ReportTemplateBuilder typeReport){
        report = new Report(typeReport);
    }
    
    public abstract Label buildTitle();
    public  Label buildDateCreate(){
      return report.getDateCreate();  
    }
    public abstract Table buildTable();    
    public abstract Embedded buildChart(MainPage mainPage);
}
