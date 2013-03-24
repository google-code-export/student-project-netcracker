/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import ua.netcrackerteam.applicationForm.Reports.TypeReports.ReportTemplateAdvertisingEfficiency;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Klitna Tetiana
 */
public class Chart{
    
       private AbstractDataset dataSet;
       private String title;
       
       JFreeChart chart;
       
       public Chart(AbstractDataset dataset, String title) {
           this.dataSet = dataset;
           this.title   = title;                
       } 
      
        public  void createChartBar3D() {
         
           chart = ChartFactory.createBarChart(
            title,
            null,
            null,
            (DefaultCategoryDataset)dataSet,
            PlotOrientation.VERTICAL,
            false,
            false,
            false
        );
 
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        
        chart.setBackgroundPaint(Color.WHITE);  
        BarRenderer r = (BarRenderer) chart.getCategoryPlot().getRenderer();  
        r.setSeriesPaint(0, Color.BLUE); 
   }
    
    public  void createChartPie(){
        
            chart = ChartFactory.createPieChart(
            title,  
            (DefaultPieDataset)dataSet,           
            true,             
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        
    }
    
    public byte[] getByteChart(int widht, int height){
        
        BufferedImage objBufferedImage=chart.createBufferedImage(widht,height);
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {        
            ImageIO.write(objBufferedImage, "png", bas);
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateAdvertisingEfficiency.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       return bas.toByteArray();
    }
    
    public Image getImageChart(int widht, int height) throws IOException, BadElementException{
        
       BufferedImage objBufferedImage=chart.createBufferedImage(widht,height);
       ByteArrayOutputStream bas = new ByteArrayOutputStream();
            
       ImageIO.write(objBufferedImage, "png", bas);         
       Image chartImage = Image.getInstance(bas.toByteArray());
       
       return chartImage;
    }

}
