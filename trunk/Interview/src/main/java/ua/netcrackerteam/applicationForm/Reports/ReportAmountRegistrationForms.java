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
import ua.netcrackerteam.DAO.Reports.DAOReport;
import ua.netcrackerteam.DAO.Reports.DAOReportAmountRegistrationForms;
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
         
         DAOReport report = new DAOReportAmountRegistrationForms();
         List dataReport = report.getReportFoView();
         
         if(dataReport == null){
             dataReport = new ArrayList();
         }
         
        JFreeChart chart = (new Chart(getDataSet(dataReport))).createChart("","interviews","registration student, %");
        Report reportDocument = new Report(getReport(dataReport), chart);        
        ByteArrayOutputStream outputStream = reportDocument.createTemplate("Статистика зарегестрированных студентов", new float[]{2f, 1.5f, 1.5f, 1.5f}); 
        
        return outputStream.toByteArray();
     }
    
     private String[][] getReport(List reportData){
         
         int column = 4;
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");
         
         String[][] report = new String[reportData.size() + 2][column];
         
         //Fill header
         report[0][0] = "Дата";     report[0][1] = "Зарегестрировано";
         report[0][2] = "Свободно"; report[0][3] = "Всего";
         
         int summaStudentsSeatsInterviews = 0;
         int summaSeatsInterviews = 0;
         ListIterator iterator = reportData.listIterator(); 
         for(int i = 1; iterator.hasNext(); i++){ 
             
             Object[] rowReportData = (Object[])iterator.next();
                     
             report[i][0] = dateFormat.format(rowReportData[0]);
             report[i][1] = "" + rowReportData[1];
             report[i][2] = "" + rowReportData[2];
             report[i][3] = "" + rowReportData[3];
             
             summaStudentsSeatsInterviews += Integer.parseInt(rowReportData[2].toString());
             summaSeatsInterviews += Integer.parseInt(rowReportData[1].toString());
             
         }
         
         // Fill footer
         report[report.length - 1][0] = "Итого"; 
         report[report.length - 1][1] = "" + (summaSeatsInterviews - summaStudentsSeatsInterviews);
         report[report.length - 1][2] = "" + summaStudentsSeatsInterviews;
         report[report.length - 1][3] = "" + summaSeatsInterviews;
         
         return report;
     }
     
    
    private CategoryDataset getDataSet(List reportData){
        
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy  HH:mm");
               
         String[] dateInterview = new String[reportData.size()];
         double[][] percent = new double[1][reportData.size()];
         
         ListIterator iterator = reportData.listIterator();
         
         for(int i = 0; iterator.hasNext(); i++){ 
             
             Object[] rowReportData = (Object[])iterator.next();
             
             dateInterview[i] = dateFormat.format(rowReportData[0]);
             
             int maxForms = Integer.parseInt(rowReportData[1].toString());
             int summaForms = Integer.parseInt(rowReportData[2].toString());
             
             percent[0][i] = new BigDecimal((maxForms == 0? 0: 100*summaForms/maxForms)).setScale(2, RoundingMode.UP).doubleValue();
         } 
         
          return DatasetUtilities.createCategoryDataset(new String[]{""}, dateInterview, percent ); 

    }
    
}
