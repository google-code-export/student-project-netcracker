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

    @Override
    public void createReport(MainPage mainPage) {
            
        setLogotip();
                     
        Table table = builder.buildTable();
        table.setWidth("100%");          
        reportsLo.addComponent(table);
        reportsLo.setComponentAlignment(table, Alignment.MIDDLE_CENTER);
        
        
        AbstractSplitPanel panelChart =new HorizontalSplitPanel();
        panelChart.setFirstComponent(new Label("Тип"));
        Embedded emb = builder.buildChart(mainPage); 
        emb.setWidth("100%");
        panelChart.setSecondComponent(emb);

        panelChart.setLocked(true);
        panelChart.setSplitPosition(30, Sizeable.UNITS_PERCENTAGE);
        panelChart.setHeight("500px");

        reportsLo.addComponent(panelChart);  
    }
    
}
