/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author home
 */
public class Chart {
    
    CategoryDataset dataSet;
    
    public Chart(CategoryDataset dataSet){
        this.dataSet = dataSet;
    }
             
    public JFreeChart createChart(String title, String categoryAsisLabel, String valueAsisLabel) {
      
        final JFreeChart chart = ChartFactory.createStackedBarChart3D(title, categoryAsisLabel, valueAsisLabel, 
              dataSet, PlotOrientation.HORIZONTAL, false, false, false);
            chart.setBackgroundPaint(Color.WHITE);     
            BarRenderer r = (BarRenderer) chart.getCategoryPlot().getRenderer();  
            r.setSeriesPaint(0, Color.blue); 
             
        return chart;
  }
}
