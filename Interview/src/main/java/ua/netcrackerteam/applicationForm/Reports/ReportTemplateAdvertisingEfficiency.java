/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import ua.netcrackerteam.DAO.DAOReport;

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
                cell = report.setTable(header, reportData, footer, size);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        return cell;
    }

    @Override
    public PdfPCell buildChart() {
        
        Chart chartTemplate = new Chart();
        JFreeChart chart = chartTemplate.createChartPie3D(getDefaultPieDataset(),"Анализ эффективности рекламы");
        PdfPCell cell = report.setChart(chart);
        
        return cell;
    }

    @Override
    public List dataReport() {
        return (new ArrayList());
    }

    @Override
    public byte[] getChart(int widht, int height) {
        
        Chart chartTemplate = new Chart();
        JFreeChart chart = chartTemplate.createChartPie3D(getDefaultPieDataset(), "Анализ эффективности рекламы");
        BufferedImage objBufferedImage=chart.createBufferedImage(widht,height);
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {        
            ImageIO.write(objBufferedImage, "png", bas);
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
        }
      

       byte[] byteArray=bas.toByteArray();
       
       return byteArray;
    }

    private DefaultPieDataset getDefaultPieDataset(){
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        String other = "Другое (уточните)";
        int allforms = getAllForms();
        int formsOther = 0;
        
        ListIterator iterator = reportData.listIterator();
        
        while(iterator.hasNext()){
            
            Object[] row = (Object[])iterator.next();
            int forms = Integer.parseInt(row[2].toString());
            
            if(row[0].equals(other)){
                formsOther+= forms;
                continue;
            }
            double percent = getPercent(forms, allforms);
            dataset.setValue(row[0].toString(), percent);
       
        }
        dataset.setValue(other, getPercent(formsOther, allforms));
        return dataset;
    }
    
    private int getAllForms(){
   
         String other = "Другое (уточните)";
         int allforms = 0;
         ListIterator iterator = reportData.listIterator();
        
         while(iterator.hasNext()){
             
             Object[] row = (Object[])iterator.next();
             
              if(row[0].equals(other)){                
                continue;}
              
              allforms += Integer.parseInt(row[2].toString());
            
         }
         
         return allforms;
    }
    
    private double getPercent(int forms, int allforms){
        double percent = new BigDecimal((allforms == 0? 0: 100*forms/allforms)).setScale(2, RoundingMode.UP).doubleValue();
        return percent;
    }
    
}
