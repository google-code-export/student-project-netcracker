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
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
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
import ua.netcrackerteam.DAO.Form;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;

/**
 *
 * @author tanya
 */
public class ReportAmountRegistrationForms implements TypeOfViewReport{
    
    private final static String pathTimesTTF = "G:/Проект1/interview/Interview/src/main/webapp/WEB-INF/resources/times.ttf";
    private static final String path = "C:/Report.pdf";
    private static final String pathImage = "G:/Проект1/interview/Interview/src/main/webapp/WEB-INF/resources/Logotip.png";

    public void viewReport(ArrayList dataForView) {
        
       Document document = new Document();
       PdfWriter writer = null;  
 
        try {
           
           BaseFont bf = BaseFont.createFont(pathTimesTTF, "cp1251", BaseFont.EMBEDDED); 
           Font fontTitle = new Font(bf, 16, Font.BOLDITALIC);
           Font fontCurrentDate = new Font(bf, 12, Font.BOLDITALIC);
           
           writer = PdfWriter.getInstance(document , new FileOutputStream(path));   
           document.addCreationDate();
           document.addProducer();
           document.addTitle("Статистика зарегестрированных студентов");
           document.setPageSize(PageSize.A4);
           document.open();  
           
           PdfPTable table = new PdfPTable(2);    
           //Image
           PdfPCell cellImage = new PdfPCell(Image.getInstance(pathImage)); 
           cellImage.setBorder(Rectangle.NO_BORDER);
           cellImage.setHorizontalAlignment(Element.ALIGN_RIGHT);
           //Create data
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");
           String currentDate = dateFormat.format(java.util.Calendar.getInstance().getTime());
           PdfPCell cellDateCreate = new PdfPCell(new Phrase(currentDate, fontCurrentDate));
           cellDateCreate.setBorder(Rectangle.NO_BORDER);
           cellDateCreate.setVerticalAlignment(Element.ALIGN_TOP);
           //title
           PdfPCell cellTitle = new PdfPCell(new Phrase("Статистика зарегестрированных студентов", fontTitle));
           cellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
           cellTitle.setBorder(Rectangle.NO_BORDER);
           cellTitle.setColspan(2);
           //table
           PdfPCell cellTable = new PdfPCell();      
           cellTable.addElement(createTable());  
           cellTable.setColspan(2);
           cellTable.setBorder(Rectangle.NO_BORDER);
           
           table.addCell(cellDateCreate);  
           table.addCell(cellImage);                                 
           table.addCell(cellTitle);            
           table.addCell(cellTable);              
       
           document.add(table);
           
        }catch (DocumentException dex)
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
                               document.close();
                           }
                           if (writer != null){                          
                            writer.close();
                           }
                 }
}

    
    private PdfPTable createTable() throws DocumentException, IOException{ 
        
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");                  
           List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
           ListIterator<Interview> iterator = interviews.listIterator();      
                  
           BaseFont font = BaseFont.createFont(pathTimesTTF, "cp1251", BaseFont.EMBEDDED);   
           Font bfBold12 = new Font(font, 11, Font.BOLDITALIC, new BaseColor(0, 0, 0)); 
           Font bf12 = new Font(font, 10, Font.ITALIC);  
          
           PdfPTable table = new PdfPTable(new float[]{2f, 1.5f, 1.5f, 1.5f}); 
           table.setWidthPercentage(100f);
                           
           BaseColor fColor = BaseColor.BLACK;
           BaseColor bColor = WebColors.getRGBColor("#99CC99");
           
           insertCell(table, "Время", Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor);
           insertCell(table, "Зарегестрировано", Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor);       
           insertCell(table, "Свободно", Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor);   
           insertCell(table, "Зарегестрировано,%", Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor); 
           table.setHeaderRows(1); 
                 
           int i = 0;              
           while(iterator.hasNext()){
                    if(i%2 == 0) {
                   bColor = WebColors.getRGBColor("#99CCCC"); } 
                    else {
                   bColor = WebColors.getRGBColor("#CCFFCC");}                      
                     
                    Interview interview = iterator.next();
                    List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
                    int amountForms =  (forms == null? 0: forms.size());              
                                                             
                    insertCell(table, dateFormat.format(interview.getStartDate()), Element.ALIGN_CENTER, 1, bf12, fColor, bColor);
                    insertCell(table, "" + amountForms, Element.ALIGN_CENTER, 1, bf12, fColor, bColor);                    
                    insertCell(table, "" + (interview.getMaxNumber() - amountForms), Element.ALIGN_CENTER, 1, bf12, fColor, bColor);
                    insertCell(table, String.format("%.2f", ((double)amountForms*100/interview.getMaxNumber())), Element.ALIGN_CENTER, 1, bf12, fColor, bColor);
                    i++;
           }   

           return  table;
}

                 
    
    
private void insertCell(PdfPTable table, String text, int align, int colspan, Font font, BaseColor fColor, BaseColor bColor){   
        
        font.setColor(fColor);
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font)); 
        cell.setBackgroundColor(bColor);
        cell.setHorizontalAlignment(align);        
        cell.setColspan(colspan);   
        cell.setBorderColor(WebColors.getRGBColor("#999966"));
        if(text.trim().equalsIgnoreCase("")){
         cell.setMinimumHeight(10f);
        }
        table.addCell(cell);
   
   } 
    
    public static void main(String arrgs[]) {    
     
        (new ReportAmountRegistrationForms()).viewReport(new ArrayList());
    } 

    
}
