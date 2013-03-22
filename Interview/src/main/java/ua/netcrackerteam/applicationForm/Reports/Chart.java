/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author home
 */
public class Chart{
    
      
        public  JFreeChart createChartBar3D(DefaultCategoryDataset dataSet, String title) {
         
           JFreeChart chart = ChartFactory.createBarChart(
            title,
            null,
            null,
            dataSet,
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
        return chart;
  }
    
    public  JFreeChart createChartPie3D(DefaultPieDataset dataSet, String title){
        
        JFreeChart chart = ChartFactory.createPieChart3D(
                title,
                dataSet, 
                false, 
                false, 
                false
        );
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setForegroundAlpha(0.6f);
        plot.setCircular(true);
        
        return chart;
    }
    

}
