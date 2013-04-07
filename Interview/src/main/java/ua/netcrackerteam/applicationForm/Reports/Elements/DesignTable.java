/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports.Elements;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.netcrackerteam.applicationForm.ClassPath;

/**
 *
 * @author Klitna Tetiana
 */
public abstract class DesignTable {
    
     protected PdfPTable table;
     
     protected String pathTimesTTF = "resources/times.ttf";
     protected String path = ClassPath.getInstance().getWebInfPath();
     
   
     public PdfPTable getFillTable(){
         return table;
     }
    public void  getHeader(String[] header){ 
        
        if(header == null){
            return;
        }
        
        try {            
            BaseFont font = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED);   
            Font bfBold12 = new Font(font, 11, Font.BOLDITALIC, new BaseColor(0, 0, 0)); 
            BaseColor fColor = BaseColor.BLACK;
            BaseColor bColor = WebColors.getRGBColor("#99CC99");
            BaseColor borderColor = WebColors.getRGBColor("#999966");
            
             //Header table
            for(int i = 0; i < header.length; i++){             
              insertCell(header[i], Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);
            }
            table.setHeaderRows(1);
        } catch (DocumentException ex) {
            Logger.getLogger(DesignTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DesignTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract void getBody(List body);
    
    public void getFooter(String[] footer){
        
        if(footer == null){
            return;
        }
        
        try {
            BaseFont font = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED);   
            Font bfBold12 = new Font(font, 11, Font.BOLDITALIC, new BaseColor(0, 0, 0)); 
            BaseColor fColor = BaseColor.BLACK;
            BaseColor bColor = WebColors.getRGBColor("#99CC99");
            BaseColor borderColor = WebColors.getRGBColor("#999966");
           //Footer table          
            for(int j = 0; j < footer.length; j++){
               insertCell(footer[j].toString(), Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);}
        } catch (DocumentException ex) {
            Logger.getLogger(DesignTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DesignTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    protected void insertCell(String text, int align,
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
