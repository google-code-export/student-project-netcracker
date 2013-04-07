/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI.Reports;

import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.gwt.client.ui.AlignmentInfo.Bits;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import ua.netcrackerteam.GUI.MainPage;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

/**
 *
 * @author Klitna Tetiana
 */

public class Report {
    
    private ReportTemplateBuilder report;
    
    public Report(ReportTemplateBuilder report){
        this.report = report;
    }
    
    public Label getTitle(String title){           
              
         Label labelTitle = new Label("<div style=\"text-align:center;color:#99CC99;font-weight:bold;font-size:20px;\">" + title  + "</div>", Label.CONTENT_XHTML);
                   
         return labelTitle;
     }
      
    public Label getDateCreate(){
        
         Label currentDate = new Label(new java.util.Date().toString());
         
         return currentDate;
    }
    
    public Table getTable(String[] headerTable){  
        
         List reportData = report.dataReport();
         Table table = fillTable(headerTable,reportData);        
         return table;
    
    }
    
    private Table fillTable(String[] headerTable, List reportData){
         Table table = new Table();
         //header
         for(int i = 0; i < headerTable.length; i++){
            table.addContainerProperty(headerTable[i], String.class, null);
         }
        //body
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
    
    public GridLayout getGridView(String[] headerTable){
        
        List reportData = report.dataReport();
        
        GridLayout grid = new GridLayout(1, 2*reportData.size());
        
        Iterator iterator = reportData.iterator();
        while(iterator.hasNext()){
           Object[] row = (Object[])iterator.next(); 
           
           for(int i = 0; i < row.length - 1; i++){
            grid.addComponent(new Label(row[i].toString()));} 
           
           Table table = fillTable(headerTable,(List)row[row.length - 1]);
           grid.addComponent(table);
           grid.setComponentAlignment(table, new Alignment(Bits.ALIGNMENT_VERTICAL_CENTER | 
                        Bits.ALIGNMENT_HORIZONTAL_CENTER));
        }
        
        return grid;     
        
    }
    
    public Embedded  getChart(MainPage mainPage){ 
         StreamResource img = new StreamResource(new Report.ChartStreamSource(300, 250), "chart.png", mainPage);
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
