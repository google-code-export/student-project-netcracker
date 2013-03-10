/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import ua.netcrackerteam.DAO.Form;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;

/**
 *
 * @author tanya
 */
public class ReportAmountRegistrationForms implements TypeOfViewReport{
    
    private static ReportAmountRegistrationForms reportAmountRegistrationForms;
    
    private ReportAmountRegistrationForms(){}
    
    public static synchronized ReportAmountRegistrationForms getInstance(){
        if(reportAmountRegistrationForms == null){
            reportAmountRegistrationForms = new ReportAmountRegistrationForms();}
        
        return reportAmountRegistrationForms;
    }
    
    public byte[] viewReport() {  
    
        Report report = new Report(getReport());        
        ByteArrayOutputStream outputStream = report.createTemplate("Статистика зарегестрированных студентов", new float[]{2f, 1.5f, 1.5f, 1.5f}); 
        
        byte[] bytes = outputStream.toByteArray();
        
        return bytes;
     }
    
     private String[][] getReport(){
         int column = 4;
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");
         
         List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
         if(interviews == null){
             interviews = new ArrayList<Interview>();
         }
         String[][] report = new String[interviews.size() + 2][column];
         
         //Fill header
         report[0][0] = "Дата";     report[0][1] = "Зарегестрировано";
         report[0][2] = "Свободно"; report[0][3] = "Всего";
         
         int summaStudentsSeatsInterviews = 0;
         int summaSeatsInterviews =0;
         ListIterator<Interview> iterator = interviews.listIterator(); 
         for(int i = 1; iterator.hasNext(); i++){ 
             
             Interview interview = iterator.next();
             List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
             int summaForms = (forms == null? 0: forms.size());
             int summa = interview.getMaxNumber();
                     
             report[i][0] = dateFormat.format(interview.getStartDate());
             report[i][1] = "" + summaForms;
             report[i][2] = "" + (summa - summaForms);
             report[i][3] = "" + summa;
             
             summaStudentsSeatsInterviews += summaForms;
             summaSeatsInterviews += summa;
             
         }
         
         // Fill footer
         report[report.length - 1][0] = "Итого"; 
         report[report.length - 1][1] = "" + (summaSeatsInterviews - summaStudentsSeatsInterviews);
         report[report.length - 1][2] = "" + summaStudentsSeatsInterviews;
         report[report.length - 1][3] = "" + summaSeatsInterviews;
         
         return report;
     }
   
     /*private CategoryDataset getCategoryDataset() {
            
        return  DatasetUtilities.createCategoryDataset(new String[]{""}, date, data);         

     }
     
     private String[][] getDataReport(){
         return new String[1][1];
     }
    
    private PdfPTable createTable() throws DocumentException, IOException{        
               
           List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
           if(interviews == null){
               interviews = new ArrayList<Interview>();
           }
           ListIterator<Interview> iterator = interviews.listIterator();  
           
            date = new String[interviews.size()];  
            data = new double[1][interviews.size()];           
                   
           PdfPTable table = new PdfPTable(new float[]{2f, 1.5f, 1.5f, 1.5f}); 
           table.setWidthPercentage(100f);                           
                    
           insertCell(table, "Дата", Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);
           insertCell(table, "Зарегестрировано", Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);       
           insertCell(table, "Свободно", Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);   
           insertCell(table, "Зарегестрировано,%", Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor); 
           table.setHeaderRows(1); 
                 
           int i = 0; 
           int amount = 0;
           int max = 0;
           
           while(iterator.hasNext()){         
                  
                    BaseColor bColorLine = (i%2 == 0? bColorTableLine1: bColorTableLine2);                          
                     
                    Interview interview = iterator.next();
                    List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
                    int amountForms =  (forms == null? 0: forms.size());      
                    
                    // date[i] = dateFormat.format(interview.getStartDate()); 
                    // data [0][i] = (double)amountForms*100/interview.getMaxNumber();
                     
                    insertCell(table, dateFormat.format(interview.getStartDate()), Element.ALIGN_CENTER, 1, bf12, fColor, bColorLine, borderColor);
                    insertCell(table, "" + amountForms, Element.ALIGN_CENTER, 1, bf12, fColor, bColorLine, borderColor);                    
                    insertCell(table, "" + (interview.getMaxNumber() - amountForms), Element.ALIGN_CENTER, 1, bf12, fColor, bColorLine, borderColor);
                    insertCell(table, String.format("%.2f", ((double)amountForms*100/interview.getMaxNumber())), Element.ALIGN_CENTER, 1, bf12, fColor, bColorLine, borderColor);
                    i++;
                    
                    amount = amount + amountForms;                    
                    max = max + interview.getMaxNumber();
           }
          
            insertCell(table, "Итого:", Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);
            insertCell(table, "" + amount, Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);
            insertCell(table, "" + (max - amount), Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);
            insertCell(table, String.format("%.2f", ((double)amount*100/max)), Element.ALIGN_CENTER, 1, bfBold12, fColor, bColor, borderColor);
            
           return  table;
}
    
 private String[][] getInterviews(){  
     
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm"); 
     
           String[][] report = null;
       
           List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
           if(interviews == null)
               return report;
           
           report = new String[interviews.size()][3];
           ListIterator<Interview> iterator = interviews.listIterator();  
           
           while(iterator.hasNext()){ 
              Interview interview = iterator.next();
              List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
              int amountForms =  (forms == null? 0: forms.size());  
           }
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
   
   } */


    public static void main(String arrgs[]) {    
     
        (new ReportAmountRegistrationForms()).viewReport();
    } 

    
}
