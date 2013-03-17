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
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.applicationForm.ClassPath;

/**
 *
 * @author tanya
 */
public class ReportAdvertisingEfficiency implements TypeOfViewReport {
    
    private final  String pathTimesTTF = "resources/times.ttf";
    private String path = ClassPath.getInstance().getWebInfPath();

     public byte[] viewReport() {
         
       DAOReport report = new DAOReport();
       List dataReportCategory = report.getReportAdvertisingEfficiency();
         
       if(dataReportCategory == null){
             dataReportCategory = new ArrayList();
       }       
    
       List dataReportCategoryOther = report.getReportAdvertisingEfficiencyOTher();
         
       if(dataReportCategoryOther == null){
             dataReportCategoryOther = new ArrayList();
       }
         
       String title = "Эффективность видов рекламы";
        
       ByteArrayOutputStream memory = null;
       Document document = new Document();
       PdfWriter writer = null;  
 
        try {
            
           BaseFont bf = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED); 
           Font fontTitle = new Font(bf, 16, Font.BOLDITALIC);
           Font fontTitleOther = new Font(bf, 12, Font.BOLDITALIC);
                    
           memory =  new ByteArrayOutputStream(); 
           
           writer = PdfWriter.getInstance(document , memory);   
           document.addCreationDate();
           document.addProducer();
           document.addTitle(title);
           document.setPageSize(PageSize.A4);
           document.open();  
           
           PdfPTable table = new PdfPTable(2);    
           //title for table category
           PdfPCell cellTitle = new PdfPCell(new Phrase(title, fontTitle));
           cellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
           cellTitle.setBorder(Rectangle.NO_BORDER);
           cellTitle.setColspan(2);
           //table category
           PdfPCell cellTableCategory = new PdfPCell();       
           PdfPTable tableReportCategory = Report.createTable(new String[]{"Тип рекламы", "Кол-во анкет"},
                                                dataReportCategory,
                                                new String[]{},
                                                new float[]{2.0f, 2.0f});
           cellTableCategory.addElement(tableReportCategory);  
           cellTableCategory.setColspan(2);
           cellTableCategory.setBorder(Rectangle.NO_BORDER);           
           //title for table other
           PdfPCell cellTitleOther = new PdfPCell(new Phrase("Расшифровка тип рекламы \"Другое(уточните)\":", fontTitleOther));
           cellTitleOther.setHorizontalAlignment(Element.ALIGN_CENTER);
           cellTitleOther.setBorder(Rectangle.NO_BORDER);
           cellTitleOther.setColspan(2);
           //table category other
           PdfPCell cellTableCategoryOther = new PdfPCell();       
           PdfPTable tableReportCategoryOther = Report.createTable(new String[]{"Другое(уточните)", "Кол-во анкет"},
                                                dataReportCategoryOther,
                                                new String[]{},
                                                new float[]{2.0f, 2.0f});
           cellTableCategoryOther.addElement(tableReportCategoryOther);  
           cellTableCategoryOther.setColspan(2);
           cellTableCategoryOther.setBorder(Rectangle.NO_BORDER);
           //chart
           JFreeChart chart = createChart(getDataSet(dataReportCategory));                              
           PdfContentByte cb = writer.getDirectContent();
           float width = PageSize.A4.getWidth();
           float height = PageSize.A4.getHeight()/4; 
           PdfTemplate pie = cb.createTemplate(width, height);
           Graphics2D g2d2 = new PdfGraphics2D(pie, width, height); 
           Rectangle2D r2d2 = new Rectangle2D.Double(0, 0, width, height);        
           chart.draw(g2d2, r2d2);       
           g2d2.dispose();              
           Image chartImage = Image.getInstance(pie);
                     
           table.addCell(Report.addCreateDate());  
           table.addCell(Report.addLogotip());                                 
           table.addCell(cellTitle);            
           table.addCell(cellTableCategory);
           table.addCell(cellTitleOther);
           table.addCell(cellTableCategoryOther);        
           
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
     
   
private PieDataset getDataSet(List reportData) throws UnsupportedEncodingException{
                    
     final DefaultPieDataset result = new DefaultPieDataset();
        String oldString = new String("Другое");
        result.setValue(new String(oldString.getBytes("Cp1251"), "UTF-8"), new Double(43.2));
        result.setValue("Visual Basic", new Double(10.0));
        result.setValue("C/C++", new Double(17.5));
        result.setValue("PHP", new Double(32.5));
        result.setValue("Perl", new Double(1.0));
        return result;

    }
      
 public JFreeChart createChart(PieDataset dataSet) {
              
            final JFreeChart chart = ChartFactory.createPieChart3D(
            "Pie Chart 3D Demo 1",  // chart title
            dataSet,                // data
            true,                   // include legend
            true,
            false
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        return chart;
  
  }

       
}
