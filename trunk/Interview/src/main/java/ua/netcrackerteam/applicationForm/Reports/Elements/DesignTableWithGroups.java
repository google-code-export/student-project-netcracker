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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.netcrackerteam.DAO.Entities.Interview;

/**
 *
 * @author Klitna Tetiana
 */
public class DesignTableWithGroups extends DesignTable{
    
    public DesignTableWithGroups(float[] sizeTable) {
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
              Interview interviewPrevious = new Interview();
              ListIterator iterator = body.listIterator();
              while(iterator.hasNext()){     
                  
                       Object[] rowReport = (Object[])iterator.next();
                       
                       Interview interview = (Interview)rowReport[0];
                       
                       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");
                       String date = dateFormat.format(interview.getStartDate());
                       
                       if(!interviewPrevious.equals(interview )) {
                        insertCell(date, Element.ALIGN_CENTER, 6, bf12, fColor, bColorTableLine2, borderColor);
                        interviewPrevious = interview;
                       }
                       
                       String[] row = (String[])rowReport[1];
                       for(int j = 0; j < row.length; j++){         
                           insertCell(row[j].toString(), Element.ALIGN_CENTER, 1, bf12, fColor, bColorTableLine1, borderColor);
                       }               
              }
        } catch (DocumentException ex) {
            Logger.getLogger(DesignTableWithGroups.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DesignTableWithGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
