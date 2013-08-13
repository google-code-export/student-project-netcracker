/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportsTemplateCreator {
    
    private ReportTemplateBuilder builder;
    
    public void setReportTemplateBuilder(ReportTemplateBuilder builder){
        this.builder = builder;
    }
    
    public byte[] viewReport() {
        
       ByteArrayOutputStream memory = null;
       Document document = new Document();
       PdfWriter writer = null;  
       try {  
           memory =  new ByteArrayOutputStream();          
           writer = PdfWriter.getInstance(document , memory);
        
           document.addCreationDate();
           document.addProducer();         
           document.setPageSize(PageSize.A4);
           document.open();  
           
           PdfPTable table = new PdfPTable(2);    
           table.addCell(builder.buildCreateDate());  
         
           table.addCell(builder.buildLogotip());                                 
           table.addCell(builder.buildTitle()); 
           
           table.addCell(builder.buildTable());        
           //table.addCell(builder.buildChart());
           
           document.add(table); 
           //document.add(builder.buildChart());
        } catch (DocumentException ex) {
            Logger.getLogger(ReportsTemplateCreator.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
                Logger.getLogger(ReportsTemplateCreator.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (document != null){                          
                document.close();}
            if (writer != null){                          
                writer.close();}
        }
           
        return memory.toByteArray(); 
    }

  

}
