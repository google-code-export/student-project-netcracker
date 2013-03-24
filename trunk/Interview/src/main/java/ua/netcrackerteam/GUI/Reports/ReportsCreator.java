/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import ua.netcrackerteam.GUI.MainPage;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportsCreator {
    
    private ReportBuilder builder;  
    private VerticalLayout reportsLo;
    
    public void setReportBuilder(ReportBuilder builder){
        this.builder = builder;
    }
       
    public void setVerticalLayout(VerticalLayout reportsLo){
        this.reportsLo = reportsLo;
    }
      
    public void createReport(MainPage mainPage){        
        
         Label labelTitle = builder.buildTitle();
         Label currentDate = builder.buildDateCreate();
         Table table = builder.buildTable();
         Embedded emb = builder.buildChart(mainPage);        
       
         reportsLo.addComponent(labelTitle);  
         reportsLo.addComponent(currentDate);
         reportsLo.addComponent(table);         
         reportsLo.addComponent(emb);
            
         reportsLo.setComponentAlignment(labelTitle, Alignment.BOTTOM_CENTER);         
         reportsLo.setComponentAlignment(currentDate, Alignment.MIDDLE_RIGHT);
         reportsLo.setComponentAlignment(table, Alignment.MIDDLE_CENTER);
         reportsLo.setComponentAlignment(emb, Alignment.MIDDLE_CENTER);
    }
}
