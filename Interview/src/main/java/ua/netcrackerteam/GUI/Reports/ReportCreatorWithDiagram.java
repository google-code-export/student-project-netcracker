/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbstractSplitPanel;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import ua.netcrackerteam.GUI.MainPage;

/**
 *
 * @author home
 */
public class ReportCreatorWithDiagram extends ReportsCreator{
    
    private boolean withChart;
    
    public ReportCreatorWithDiagram(boolean withChart){
       this.withChart = withChart; 
    }

    @Override
    public void createReport() {
            
        setLogotip();
                     
        Table table = builder.buildTable();
        table.setWidth("100%");          
        reportsLo.addComponent(table);
        reportsLo.setComponentAlignment(table, Alignment.MIDDLE_CENTER);
       
        if(withChart){
            Embedded emb = builder.buildChart(); 
            emb.setWidth("100%");
            reportsLo.addComponent(emb);
            reportsLo.setComponentAlignment(emb, Alignment.MIDDLE_CENTER);
        }
 
    }
    
}
