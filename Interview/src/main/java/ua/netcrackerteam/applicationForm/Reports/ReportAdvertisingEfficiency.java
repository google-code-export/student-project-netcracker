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
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.applicationForm.ClassPath;

/**
 *
 * @author tanya
 */
public class ReportAdvertisingEfficiency implements TypeOfViewReport {
    
    private final  String pathTimesTTF = "resources/times.ttf";
    private String path = ClassPath.getInstance().getWebInfPath();

     public byte[] viewReport() {
         
       DAOReport report = new DAOReport();
       List dataReportCategory = report.getReportAdvertisingEfficiency();
         
       if(dataReportCategory == null){
             dataReportCategory = new ArrayList();
       }       
    
       List dataReportCategoryOther = report.getReportAdvertisingEfficiencyOTher();
         
       if(dataReportCategoryOther == null){
             dataReportCategoryOther = new ArrayList();
       }
         
       String title = "Эффективность видов рекламы";
        
       ByteArrayOutputStream memory = null;
       Document document = new Document();
       PdfWriter writer = null;  
 
        try {
            
           BaseFont bf = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED); 
           Font fontTitle = new Font(bf, 16, Font.BOLDITALIC);
           Font fontTitleOther = new Font(bf, 12, Font.BOLDITALIC);
                    
           memory =  new ByteArrayOutputStream(); 
           
           writer = PdfWriter.getInstance(document , memory);   
           document.addCreationDate();
           document.addProducer();
           document.addTitle(title);
           document.setPageSize(PageSize.A4);
           document.open();  
           
           PdfPTable table = new PdfPTable(2);    
           //title for table category
           PdfPCell cellTitle = new PdfPCell(new Phrase(title, fontTitle));
           cellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
           cellTitle.setBorder(Rectangle.NO_BORDER);
           cellTitle.setColspan(2);
           //table category
           PdfPCell cellTableCategory = new PdfPCell();       
           PdfPTable tableReportCategory = Report.createTable(new String[]{"Тип рекламы", "Кол-во анкет"},
                                                dataReportCategory,
                                                new String[]{},
                                                new float[]{2.0f, 2.0f});
           cellTableCategory.addElement(tableReportCategory);  
           cellTableCategory.setColspan(2);
           cellTableCategory.setBorder(Rectangle.NO_BORDER);           
           //title for table other
           PdfPCell cellTitleOther = new PdfPCell(new Phrase("Расшифровка тип рекламы \"Другое(уточните)\":", fontTitleOther));
           cellTitleOther.setHorizontalAlignment(Element.ALIGN_CENTER);
           cellTitleOther.setBorder(Rectangle.NO_BORDER);
           cellTitleOther.setColspan(2);
           //table category other
           PdfPCell cellTableCategoryOther = new PdfPCell();       
           PdfPTable tableReportCategoryOther = Report.createTable(new String[]{"Другое(уточните)", "Кол-во анкет"},
                                                dataReportCategoryOther,
                                                new String[]{},
                                                new float[]{2.0f, 2.0f});
           cellTableCategoryOther.addElement(tableReportCategoryOther);  
           cellTableCategoryOther.setColspan(2);
           cellTableCategoryOther.setBorder(Rectangle.NO_BORDER);
              
           table.addCell(Report.addCreateDate());  
           table.addCell(Report.addLogotip());                                 
           table.addCell(cellTitle);            
           table.addCell(cellTableCategory);
           table.addCell(cellTitleOther);
           table.addCell(cellTableCategoryOther);        
           
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
       
}
