/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.metamodel.relational.Loggable;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;

/**
 *
 * @author tanya
 */
public class ReportAmountRegistrationForms implements TypeOfViewReport{
    
    private final static String pathTimesTTF = "src/main/java/times.ttf";
    private static final String path = "C:/Report.pdf";

    public void viewReport(ArrayList dataForView) { 
        
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");
                  
           List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
           ListIterator<Interview> iterator = interviews.listIterator();
        
           Document document = new Document();
           PdfWriter writer = null;  
 
           try { 
                  BaseFont font = BaseFont.createFont(pathTimesTTF, "cp1251", BaseFont.EMBEDDED);   
                  Font bfBold12 = new Font(font, 11, Font.BOLD, new BaseColor(0, 0, 0)); 
                  Font bf12 = new Font(font, 10);  

                  writer = PdfWriter.getInstance(document , new FileOutputStream(path));   
                  document.addCreationDate();
                  document.addProducer();
                  document.addTitle("Статистика зарегестрированных студентов");
                  document.setPageSize(PageSize.A4);
                  document.open();  
  
                  Paragraph paragraph = new Paragraph("");
    
                 PdfPTable table = new PdfPTable(new float[]{1.5f, 1f, 1f, 1f, 1f}); 
                 table.setWidthPercentage(100f);
 
                 insertCell(table, "Время", Element.ALIGN_CENTER, 1, bfBold12);
                 insertCell(table, "Зарегестрировано", Element.ALIGN_CENTER, 1, bfBold12);
                 insertCell(table, "Зарегестрировано, %", Element.ALIGN_CENTER, 1, bfBold12);
                 insertCell(table, "Свободно", Element.ALIGN_CENTER, 1, bfBold12);
                 insertCell(table, "Свободно, %", Element.ALIGN_CENTER, 1, bfBold12);
                 table.setHeaderRows(1); 
                 
                 insertCell(table, "Дата", Element.ALIGN_CENTER, 5, bfBold12);
                //insertCell(table, "Количество", Element.ALIGN_LEFT, 1, bfBold12);
                 
                 while(iterator.hasNext()){
                     
                    Interview interview = iterator.next();
                    int amountForms =  HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview()).size();                 
                    
                                         
                    insertCell(table, dateFormat.format(interview.getStartDate()), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, "" + amountForms, Element.ALIGN_RIGHT, 1, bf12);
                    insertCell(table, "This is Customer Number ABC00" + 1, Element.ALIGN_RIGHT, 1, bf12);
                    insertCell(table, "" + (interview.getMaxNumber() - amountForms), Element.ALIGN_RIGHT, 1, bf12);
                    insertCell(table, "This is Customer Number ABC00" + 1, Element.ALIGN_RIGHT, 1, bf12);


                 }

                //add the PDF table to the paragraph 
                paragraph.add(table);
                // add the paragraph to the document
                document.add(paragraph);

                 }
                 catch (DocumentException dex)
                 {
                  dex.printStackTrace();
                 }
                 catch (Exception ex)
                 {
                  ex.printStackTrace();
                 }
                 finally
                 {
                           if (document != null){
                            //close the document
                            document.close();
                           }
                           if (writer != null){
                            //close the writer
                            writer.close();
                           }
                 }
}
    
    
private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){   
       
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));       
        cell.setHorizontalAlignment(align);        
        cell.setColspan(colspan);      
        if(text.trim().equalsIgnoreCase("")){
         cell.setMinimumHeight(10f);
        }List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
        table.addCell(cell);
   
 } 
    
    public static void main(String arrgs[]) {    
     
        (new ReportAmountRegistrationForms()).viewReport(new ArrayList());
    } 

    
}
