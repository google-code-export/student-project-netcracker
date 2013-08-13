/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports.Elements;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Klitna Tetiana
 */
public class DesignTableFlat extends DesignTable{
    
    public DesignTableFlat(float[] sizeTable) {
         table = new PdfPTable(sizeTable); 
         table.setWidthPercentage(100f); 
    }
    
    @Override
    public void getBody(List body) {
        
        if(body == null){
            return;
        }
        try {
            BaseColor fColor = BaseColor.BLACK;   
            BaseColor borderColor = WebColors.getRGBColor("#999966");           
            BaseColor bColorTableLine1 = WebColors.getRGBColor("#99CCCC");            
            BaseColor bColorTableLine2 = WebColors.getRGBColor("#CCFFCC");
            BaseFont font = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED);
            Font bf12 = new Font(font, 10, Font.ITALIC);              
              
            //Content table  
              ListIterator iterator = body.listIterator();
              for(int i = 1; iterator.hasNext(); i++){     
                  
                       Object[] rowReport = (Object[])iterator.next();
                       
                       BaseColor bColorLine = (i%2 == 0? bColorTableLine1: bColorTableLine2);                          
                       for(int j = 0; j < rowReport.length; j++){         
                           insertCell(rowReport[j].toString(), Element.ALIGN_CENTER, 1, bf12, fColor, bColorLine, borderColor);
                       }               
              }
        } catch (DocumentException ex) {
            Logger.getLogger(DesignTableFlat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DesignTableFlat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
