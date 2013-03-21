/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

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
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ListIterator;
import org.jfree.chart.JFreeChart;
import ua.netcrackerteam.applicationForm.ClassPath;

/**
 *
 * @author home
 */
public class ReportPDFTemplate {
    
    private String pathTimesTTF = "resources/times.ttf";
    private String pathImage =    "resources/Logotip.png";
    private String path = ClassPath.getInstance().getWebInfPath();
    
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
    
    public PdfPCell setTable(String[] headerTable,
                                List dataReport, 
                                String[] footerTable,
                                float[] sizeTable) throws DocumentException, IOException{
        
          PdfPCell cellTable = new PdfPCell();      
          cellTable.addElement(createTable(headerTable, dataReport, footerTable, sizeTable));  
          cellTable.setColspan(2);
          cellTable.setBorder(Rectangle.NO_BORDER);
          
          return cellTable ;
    } 
    
    public PdfPCell setChart(JFreeChart chart){
        
       PdfPCell cellChart = new PdfPCell();
       cellChart.setColspan(2);
       cellChart .setBorder(Rectangle.NO_BORDER);
       
       return cellChart;
    }
    
    private PdfPTable createTable(String[] header,
                                   List body,
                                   String[] footer,
                                   float[] size) throws DocumentException, IOException{
           
           BaseColor fColor = BaseColor.BLACK;
           BaseColor bColor = WebColors.getRGBColor("#99CC99");
           BaseColor borderColor = WebColors.getRGBColor("#999966");           
           BaseColor bColorTableLine1 = WebColors.getRGBColor("#99CCCC");            
           BaseColor bColorTableLine2 = WebColors.getRGBColor("#CCFFCC");
           
           BaseFont font = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED);   
           Font bfBold12 = new Font(font, 11, Font.BOLDITALIC, new BaseColor(0, 0, 0)); 
           Font bf12 = new Font(font, 10, Font.ITALIC);              
            
           PdfPTable table = new PdfPTable(size); 
           table.setWidthPercentage(100f);                         
           //Header table
           for(int i = 0; i < header.length; i++){
            insertCell(table, header[i], Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);
           }
           table.setHeaderRows(1); 
           //Content table  
           ListIterator iterator = body.listIterator();
           for(int i = 1; iterator.hasNext(); i++){     
               
                    Object[] rowReport = (Object[])iterator.next();
                    
                    BaseColor bColorLine = (i%2 == 0? bColorTableLine1: bColorTableLine2);                          
                    for(int j = 0; j < rowReport.length; j++){         
                        insertCell(table, rowReport[j].toString(), Element.ALIGN_CENTER, 1, bf12, fColor, bColorLine, borderColor);
                    }               
           }
           //Footer table
           for(int j = 0; j < footer.length; j++){
              insertCell(table, footer[j], Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);}
       
           return  table;
    }
   
       private void insertCell(PdfPTable table, String text, int align,
                        int colspan, Font font, 
                        BaseColor foregroudColor, BaseColor backgroundColor, BaseColor borderColor){   
        
        font.setColor(foregroudColor);
        
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font)); 
        
        cell.setBackgroundColor(backgroundColor);
        cell.setHorizontalAlignment(align);        
        cell.setColspan(colspan);   
        cell.setBorderColor(borderColor);
        
        if(text.trim().equalsIgnoreCase("")){
         cell.setMinimumHeight(10f);
        }
        table.addCell(cell);
   
   } 
   
}
