/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports;

import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbstractSplitPanel;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.Runo;
import org.jfree.chart.block.EmptyBlock;
import ua.netcrackerteam.GUI.MainPage;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderAdvertisingEfficiency;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderDynamicsOfIncreaseStudents;
import ua.netcrackerteam.GUI.Reports.TypeReports.ReportBuilderStudentsToInterview;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateAdvertisingEfficiency;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateDynamicsOfIncreaseStudents;
import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateStudentsToInterview;

/**
 *
 * @author Klitna Tetiana
 */
public abstract class ReportsCreator {
            
    protected ReportBuilder builder;  
    protected VerticalLayout reportsLo;
     
    public void setReportBuilder(ReportBuilder builder){
        this.builder = builder;
    }
       
       
    public void setVerticalLayout(VerticalLayout reportsLo){
        this.reportsLo = reportsLo;
    }
    
    protected void setLogotip(){
        
        reportsLo.setSizeFull();
        
        //add title
        HorizontalLayout titleBar = new HorizontalLayout(); 

        titleBar.setHeight("40px");        
        titleBar.setWidth("100%");  
        
        Label labelTitle = builder.buildTitle();  
        titleBar.addComponent(labelTitle); 
        titleBar.setComponentAlignment(labelTitle, Alignment.MIDDLE_CENTER);
        reportsLo.addComponent(titleBar);
        
        //date create
        Label currentDate = builder.buildDateCreate();          
        reportsLo.addComponent(currentDate);       
        reportsLo.setComponentAlignment(titleBar, Alignment.BOTTOM_LEFT);
               
    }
    
    public abstract void createReport(MainPage mainPage);  
    
    
   
}
