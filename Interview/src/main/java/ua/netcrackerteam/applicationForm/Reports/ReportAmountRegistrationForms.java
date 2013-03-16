/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import ua.netcrackerteam.applicationForm.Reports.TypeOfViewReport;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
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
        
         List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
         if(interviews == null){
             interviews = new ArrayList<Interview>();
         }
         
        JFreeChart chart = (new Chart(getDataSet(interviews))).createChart("","interviews","registration student, %");
        Report report = new Report(getReport(interviews), chart);        
        ByteArrayOutputStream outputStream = report.createTemplate("Статистика зарегестрированных студентов", new float[]{2f, 1.5f, 1.5f, 1.5f}); 
        
        byte[] bytes = outputStream.toByteArray();
        
        return bytes;
     }
    
     private String[][] getReport(List<Interview> interviews){
         
         int column = 4;
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");
         
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
     
    
    private CategoryDataset getDataSet(List<Interview> interviews){
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");
               
         String[] dateInterview = new String[interviews.size()];
         double[][] percent = new double[1][interviews.size()];
         
         ListIterator<Interview> iterator = interviews.listIterator();
         
         for(int i = 0; iterator.hasNext(); i++){ 
             
             Interview interview = iterator.next();
             
             dateInterview[i] = dateFormat.format(interview.getStartDate());
             
             int maxForms = interview.getMaxNumber();
             List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
             int summaForms = (forms == null? 0: forms.size());
             percent[0][i] = new BigDecimal((maxForms == 0? 0: 100*summaForms/maxForms)).setScale(2, RoundingMode.UP).doubleValue();
         } 
         
          return DatasetUtilities.createCategoryDataset(new String[]{""}, dateInterview, percent ); 

    }
    
}
