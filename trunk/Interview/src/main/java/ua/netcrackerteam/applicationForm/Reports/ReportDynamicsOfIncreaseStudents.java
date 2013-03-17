/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import ua.netcrackerteam.applicationForm.Reports.TypeOfViewReport;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import ua.netcrackerteam.DAO.DAOReport;

/**
 *
 * @author tanya
 */
public class ReportDynamicsOfIncreaseStudents implements TypeOfViewReport{

    public byte[] viewReport() {
         DAOReport report = new DAOReport();
         List dataReport = report.getReportDynamicsOfIncreaseStudents();
         
         if(dataReport == null){
             dataReport = new ArrayList();
         }
         
        JFreeChart chart = (new Chart(getDataSet(dataReport))).createChart("","interviews","registration student, %");
        Report reportDocument = new Report(dataReport,new String[]{"Дата собеседования, Зарегестрировано, Свободно, Всего"}, chart ); 
        ByteArrayOutputStream outputStream = reportDocument.createTemplate("Статистика зарегестрированных студентов", new float[]{2f, 1.5f, 1.5f, 1.5f}); 
        
        return outputStream.toByteArray();
    }

      private CategoryDataset getDataSet(List reportData){
                    
         String[] dateInterview = new String[reportData.size()];
         double[][] percent = new double[1][reportData.size()];
         
         ListIterator iterator = reportData.listIterator();
         
         for(int i = 0; iterator.hasNext(); i++){ 
             
             String[] rowReportData = (String[])iterator.next();
             
             dateInterview[i] = rowReportData[0];
             
             int maxForms = Integer.parseInt(rowReportData[1]);
             int summaForms = Integer.parseInt(rowReportData[2]);
             
             percent[0][i] = new BigDecimal((maxForms == 0? 0: 100*summaForms/maxForms)).setScale(2, RoundingMode.UP).doubleValue();
         } 
         
          return DatasetUtilities.createCategoryDataset(new String[]{""}, dateInterview, percent ); 

    }
    
}
