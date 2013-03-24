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
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.general.DefaultPieDataset;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.applicationForm.Reports.Chart;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

/**
 *
 * @author home
 */
public class ReportTemplateAdvertisingEfficiency extends ReportTemplateBuilder{
    
    private List reportData;
    
    public ReportTemplateAdvertisingEfficiency(){
        DAOReport reportDAO = new DAOReport();
         reportData = reportDAO.getReportAdvertisingEfficiency();
         
         if(reportData == null){
             reportData = new ArrayList();
         } 
    }
    
    @Override
    public PdfPCell buildTitle() {

         PdfPCell cell= new PdfPCell();
        try {       
            cell = report.setTitle("Эффективность видов рекламы");
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return cell;
    }

    @Override
    public PdfPCell buildTable() {
        
         String[] header = new String[]{"Категория рекламы", "Выбрано (кол-во анкет)", " Выбрано (%)"};       
         String[] footer = new String[]{"Итого", "" + getAllForms(), ""};
        
         float[] size = new float[]{2.5f, 1.5f, 1.5f};
       
        
         PdfPCell cell = new PdfPCell();
            try {        
                cell = report.setTable(header, dataReport(), footer, size);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        return cell;
    }

    @Override
    public PdfPCell buildChart() {
        
        Chart chartTemplate = new Chart(getDefaultPieDataset(),"Анализ эффективности рекламы");
        chartTemplate.createChartPie();
            
        PdfPCell cell = new PdfPCell(); 
        try {
            cell = report.setChart(chartTemplate);
        } catch (BadElementException ex) {
            Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cell;
    }

    @Override
    public List dataReport() {
        
        Object[] row;
        int allforms = getAllForms();
        
        List temp = new LinkedList();
        
        ListIterator iterator = reportData.listIterator();
        while(iterator.hasNext()){
            row = (Object[])iterator.next();
            double percent = getPercent(Integer.parseInt(row[1].toString()), allforms);
           
            temp.add(new Object[]{row[0], row[1], percent});
        }
        return temp;
    }

    @Override
    public byte[] getChart(int widht, int height) {
        
        Chart chartTemplate = new Chart(getDefaultPieDataset(), "Анализ эффективности рекламы");
        chartTemplate.createChartPie();
        
        return chartTemplate.getByteChart(500, 400);
    }

    private DefaultPieDataset getDefaultPieDataset(){
        
        DefaultPieDataset dataset = new DefaultPieDataset();      
        int allforms = getAllForms();
                
        ListIterator iterator = reportData.listIterator();
        
        while(iterator.hasNext()){            
            Object[] row = (Object[])iterator.next();
            int forms = Integer.parseInt(row[1].toString());
           
            double percent = getPercent(forms, allforms);
            dataset.setValue(row[0].toString(), percent);       
        }    
        return dataset;
    }
    
    private int getAllForms(){
          
         int allforms = 0;
         ListIterator iterator = reportData.listIterator();
        
         while(iterator.hasNext()){
             
             Object[] row = (Object[])iterator.next();                           
             allforms += Integer.parseInt(row[1].toString());            
         }
         
         return allforms;
    }
    
    private double getPercent(int forms, int allforms){
        double percent = new BigDecimal((allforms == 0? 0: 100*(double)forms/allforms)).setScale(2, RoundingMode.UP).doubleValue();
        return percent;
    }

  
       
}
