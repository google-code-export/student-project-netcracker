/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports.TypeReports;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.category.DefaultCategoryDataset;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.applicationForm.Reports.Elements.Chart;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTable;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableFlat;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportTemplateDynamicsOfIncreaseStudents extends ReportTemplateBuilder{
    
    private List reportData;
    private Chart chartTemplate;
    
    public ReportTemplateDynamicsOfIncreaseStudents() {
        
         DAOReport reportDAO = new DAOReport();
         reportData = reportDAO.getReportDynamicsOfIncreaseStudents();
         
         if(reportData == null){
             reportData = new ArrayList();
         }
    }
    
    @Override
    public PdfPCell buildTitle() {
        PdfPCell cell= new PdfPCell();
        try {
            cell = report.setTitle("Статистика увеличения записанных студентов на собеседования");
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateDynamicsOfIncreaseStudents.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportTemplateDynamicsOfIncreaseStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cell;
    }

    @Override
    public PdfPCell buildTable() {
        
        String[] header = new String[]{"Дата собеседования", "Всего", "Зарегистрировано", "Свободно"};       
        String[] footer = getFooter(reportData);    
        
        float[] size = new float[]{2f, 1.5f, 1.5f, 1.5f}; 
        DesignTable table = new DesignTableFlat(size);
        
        report.setDesignTable(table);
        PdfPCell cell = report.setTable(header, reportData, footer);
     
        return cell;
        
    }

    @Override
    public PdfPCell buildChart() {
          
        PdfPCell cell = new PdfPCell();
        try {
            cell = report.setChart(chartTemplate);
        } catch (BadElementException ex) {
            Logger.getLogger(ReportTemplateDynamicsOfIncreaseStudents.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateDynamicsOfIncreaseStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cell;
    }
        
        private DefaultCategoryDataset getCategoryDataSet(){
          
          DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
          ListIterator iterator = reportData.listIterator();         
          for(int i = 0; iterator.hasNext(); i++){ 
             
             Object[] rowReportData = (Object[])iterator.next();
            
             int maxForms = Integer.parseInt(rowReportData[1].toString());
             int summaForms = Integer.parseInt(rowReportData[2].toString());
             
             double percent = new BigDecimal((maxForms == 0? 0: 100*summaForms/maxForms)).setScale(2, RoundingMode.UP).doubleValue();
             dataSet.addValue(percent, "%", rowReportData[0].toString());
             
           } 
           return dataSet;             
        }
  
    
   private String[] getFooter(List dataReport){
       
       int amount = 0;
       int free = 0;
       
       ListIterator iterator = dataReport.listIterator();
       while(iterator.hasNext()){
           
           Object[] row = (Object[])iterator.next();
           amount += Integer.parseInt(row[1].toString());
           free += Integer.parseInt(row[3].toString());
       }
      return new String[]{"Итого:", "" + amount, "" + (amount - free), "" + free};
    
   }

    @Override
    public List dataReport() {
        return reportData;
    }

    @Override
    public byte[] getChart(int widht, int height) {
        
        chartTemplate = new Chart(getCategoryDataSet());
        chartTemplate.createChartBar();
        
        return chartTemplate.getByteChart(widht, height);
    }

   
    }
