/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.applicationForm.ClassPath;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportAmountRegistrationForms implements TypeOfViewReport{
    
    private final  String pathTimesTTF = "resources/times.ttf";
    private String path = ClassPath.getInstance().getWebInfPath();
    
        
    public byte[] viewReport() {  
        
         DAOReport report = new DAOReport();
         List dataReport = report.getReportAmountRegistrationForms();
         
         if(dataReport == null){
             dataReport = new ArrayList();
         }
        
       String title = "Статистика зарегестрированных студентов";
        
       ByteArrayOutputStream memory = null;
       Document document = new Document();
       PdfWriter writer = null;  
 
        try {
            
           BaseFont bf = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED); 
           Font fontTitle = new Font(bf, 16, Font.BOLDITALIC);
                    
           memory =  new ByteArrayOutputStream(); 
           
           writer = PdfWriter.getInstance(document , memory);   
           document.addCreationDate();
           document.addProducer();
           document.addTitle(title);
           document.setPageSize(PageSize.A4);
           document.open();  
           
           PdfPTable table = new PdfPTable(2);    
           //title
           PdfPCell cellTitle = new PdfPCell(new Phrase(title, fontTitle));
           cellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
           cellTitle.setBorder(Rectangle.NO_BORDER);
           cellTitle.setColspan(2);
           //table
           PdfPCell cellTable = new PdfPCell();      
           cellTable.addElement(Report.createTable(new String[]{""}, dataReport, new String[]{""}, new float[]{2.0f}));  
           cellTable.setColspan(2);
           cellTable.setBorder(Rectangle.NO_BORDER);
                     
           table.addCell(Report.addCreateDate());  
           table.addCell(Report.addLogotip());                                 
           table.addCell(cellTitle);            
           table.addCell(cellTable);
           
           document.add(table);        
           
        }catch (DocumentException dex){
           dex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            if (document != null){                          
                document.close();}
            if (writer != null){                          
                writer.close();}
        } 
           
        return memory.toByteArray();
     }  

   public List getDataFoReport(){
         DAOReport report = new DAOReport();
         List dataReport = report.getReportAmountRegistrationForms();
         
         if(dataReport == null){
             dataReport = new ArrayList();
         }
         
         return dataReport;
   }

    public List dataReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Image getChart() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte[] getChart(int widht, int height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
