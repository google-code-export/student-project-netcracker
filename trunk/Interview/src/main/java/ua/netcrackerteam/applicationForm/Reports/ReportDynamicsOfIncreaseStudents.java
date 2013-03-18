/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.applicationForm.ClassPath;

/**
 *
 * @author tanya
 */
public class ReportDynamicsOfIncreaseStudents implements TypeOfViewReport{
    
    private final  String pathTimesTTF = "resources/times.ttf";
    private String path = ClassPath.getInstance().getWebInfPath();
    
    public byte[] viewReport() {
         DAOReport report = new DAOReport();
         List dataReport = report.getReportDynamicsOfIncreaseStudents();
         
         if(dataReport == null){
             dataReport = new ArrayList();
         }
         
        
      String title = "Динамика регистрации студентов";
        
       ByteArrayOutputStream memory = null;
       Document document = new Document();
       PdfWriter writer = null;  
 
        try {
            
           BaseFont bf = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED); 
           Font fontTitle = new Font(bf, 16, Font.BOLDITALIC);
                    
           memory =  new ByteArrayOutputStream(); 
           
           writer = PdfWriter.getInstance(document , memory);   
           document.addCreationDate();
           document.addProducer();
           document.addTitle(title);
           document.setPageSize(PageSize.A4);
           document.open();  
           
           PdfPTable table = new PdfPTable(2);    
           //title
           PdfPCell cellTitle = new PdfPCell(new Phrase(title, fontTitle));
           cellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
           cellTitle.setBorder(Rectangle.NO_BORDER);
           cellTitle.setColspan(2);
           //table
           PdfPCell cellTable = new PdfPCell();      
           cellTable.addElement(Report.createTable(new String[]{"Дата собеседования", "Всего", "Зарегестрировано", "Свободно"}, dataReport, new String[]{""}, new float[]{2f, 1.5f, 1.5f, 1.5f}));  
           cellTable.setColspan(2);
           cellTable.setBorder(Rectangle.NO_BORDER);
           
           JFreeChart chart = createChart(getDataSet(dataReport),"","Interview","Students, %");
                              
           PdfContentByte cb = writer.getDirectContent();
           float width = PageSize.A4.getWidth()*2/3;
           float height = PageSize.A4.getHeight()/4; 
           PdfTemplate bar = cb.createTemplate(width, height);
           Graphics2D g2d2 = new PdfGraphics2D(bar, width, height); 
           Rectangle2D r2d2 = new Rectangle2D.Double(0, 0, width, height);        
           chart.draw(g2d2, r2d2);       
           g2d2.dispose();                
      
           Image chartImage = Image.getInstance(bar);
                     
           table.addCell(Report.addCreateDate());  
           table.addCell(Report.addLogotip());                                 
           table.addCell(cellTitle);            
           table.addCell(cellTable);
           
           document.add(table); 
           document.add(chartImage);
           
        }catch (DocumentException dex){
           dex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            if (document != null){                          
                document.close();}
            if (writer != null){                          
                writer.close();}
        } 
           
        return memory.toByteArray(); 
   }

      private CategoryDataset getDataSet(List reportData){
                    
         String[] dateInterview = new String[reportData.size()];
         double[][] percent = new double[1][reportData.size()];
         
         ListIterator iterator = reportData.listIterator();
         
         for(int i = 0; iterator.hasNext(); i++){ 
             
             Object[] rowReportData = (Object[])iterator.next();
             
             dateInterview[i] = rowReportData[0].toString();
             
             int maxForms = Integer.parseInt(rowReportData[1].toString());
             int summaForms = Integer.parseInt(rowReportData[2].toString());
             
             percent[0][i] = new BigDecimal((maxForms == 0? 0: 100*summaForms/maxForms)).setScale(2, RoundingMode.UP).doubleValue();
         } 
         
          return DatasetUtilities.createCategoryDataset(new String[]{""}, dateInterview, percent ); 

    }
      
 public JFreeChart createChart(CategoryDataset dataSet,String title, String categoryAsisLabel, String valueAsisLabel) {
       
 
         final JFreeChart chart = ChartFactory.createStackedBarChart3D("График", categoryAsisLabel, valueAsisLabel, 
              dataSet, PlotOrientation.HORIZONTAL, false, false, false);
                
            chart.setBackgroundPaint(Color.WHITE);  
            BarRenderer r = (BarRenderer) chart.getCategoryPlot().getRenderer();  
            r.setSeriesPaint(0, Color.blue); 
             
        return chart;
  }
    
}
