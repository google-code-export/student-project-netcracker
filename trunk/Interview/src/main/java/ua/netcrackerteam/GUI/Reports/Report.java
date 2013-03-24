/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports;

import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;
import ua.netcrackerteam.GUI.MainPage;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

/**
 *
 * @author home
 */

public class Report {
    
    private ReportTemplateBuilder report;
    
    public Report(ReportTemplateBuilder report){
        this.report = report;
    }
    
    public Label getTitle(String title){            
         
         Label labelTitle = new Label("<div style=\"text-align:center;color:#99CC99;font-weight:bold;font-size:30px;\">" + title  + "</div>", Label.CONTENT_XHTML);
         labelTitle.setHeight("40px");       
         
         return labelTitle;
     }
      
    public Label getDateCreate(){
        
         Label currentDate = new Label(new java.util.Date().toString());
         
         return currentDate;
    }
    
    public Table getTable(String[] headerTable){  
        
         List reportData = report.dataReport();
             
         final Table table = new Table();
         
         for(int i = 0; i < headerTable.length; i++){
            table.addContainerProperty(headerTable[i], String.class, null);
         }
  
        ListIterator iterator = reportData.listIterator();
        for(int i = 0; iterator.hasNext(); i++){
            table.addItem((Object[])iterator.next(), i);
        }

        table.setPageLength(table.size());

        table.setSelectable(false);
        table.setNullSelectionAllowed(false);
        table.setImmediate(true);
        
        return table;
    
    }
    
    public Embedded  getChart(MainPage mainPage){ 
         StreamResource img = new StreamResource(new Report.ChartStreamSource(400, 400), "chart.png", mainPage);
         Embedded emb = new Embedded("", img); 
         
         return emb;
    }
    
    private class ChartStreamSource  implements StreamResource.StreamSource {
        
        private int weidth, height;
        
        public ChartStreamSource (int weidth, int height){
            this.weidth = weidth;
            this.height = height;
        }
        
        public InputStream getStream() {      
            return new ByteArrayInputStream(report.getChart(weidth, height));
        }
        
    }
}
