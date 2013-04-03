/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import ua.netcrackerteam.applicationForm.Reports.Elements.Chart;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import ua.netcrackerteam.applicationForm.ClassPath;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTable;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableFlat;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableWithGroups;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportPDFTemplate {
    
    private String pathTimesTTF = "resources/times.ttf";
    private String pathImage =    "resources/Logotip.png";
    private String path = ClassPath.getInstance().getWebInfPath();
    
    private DesignTable table;
    
    public  void setDesignTable(DesignTable table){
        this.table = table;
    }
    
    public PdfPCell setLogotip() throws IOException, BadElementException{ 
        
           PdfPCell cellImage = new PdfPCell(Image.getInstance(path + pathImage)); 
           cellImage.setBorder(Rectangle.NO_BORDER);
           cellImage.setHorizontalAlignment(Element.ALIGN_RIGHT); 
           
           return cellImage;
    }
    
    public  PdfPCell setCreateDate() throws DocumentException, IOException{
          
           BaseFont bf = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED); 
           Font fontCurrentDate = new Font(bf, 12, Font.BOLDITALIC);
         
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");
           String currentDate = dateFormat.format(java.util.Calendar.getInstance().getTime());
           PdfPCell cellDateCreate = new PdfPCell(new Phrase(currentDate, fontCurrentDate));
           cellDateCreate.setBorder(Rectangle.NO_BORDER);
           cellDateCreate.setVerticalAlignment(Element.ALIGN_TOP);
           
           return cellDateCreate;
    }
        
    public PdfPCell setTitle(String title) throws IOException, DocumentException{
          BaseFont bf = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED); 
          Font fontTitle = new Font(bf, 16, Font.BOLDITALIC);
          
           PdfPCell cellTitle = new PdfPCell(new Phrase(title, fontTitle));
           cellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
           cellTitle.setBorder(Rectangle.NO_BORDER);
           cellTitle.setColspan(2);
           
           return cellTitle;
    }
    
    public PdfPCell setTable(String[] header,
                                   List body,
                                   String[] footer){
        
           PdfPCell cellTable = new PdfPCell();           
           
           table.getHeader(header);
           table.getBody(body);
           table.getFooter(footer);
           
           cellTable.addElement(table.getFillTable());  
           cellTable.setColspan(2);
           cellTable.setBorder(Rectangle.NO_BORDER);
          
          return cellTable ;
          
    } 
    
    public PdfPCell setChart(Chart chart) throws BadElementException, IOException{
        
       PdfPCell cellChart = new PdfPCell();
       Image chartImage = chart.getImageChart(300, 320);
       cellChart.setImage(chartImage);      
       cellChart.setColspan(2);
       cellChart .setBorder(Rectangle.NO_BORDER);
       
       return cellChart;
    }
        

   
   
   
}
