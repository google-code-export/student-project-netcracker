/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.IOException;
import java.util.List;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author home
 */
public abstract class ReportTemplateBuilder {
    protected ReportPDFTemplate report;
    
    public ReportPDFTemplate getReportPDFTemplate(){
        return report;
    }
    public void createReportPDFTemplate(){
        report = new ReportPDFTemplate();
    }
    
    public PdfPCell buildLogotip() throws IOException, BadElementException{
        return report.setLogotip();
    }
    public PdfPCell buildCreateDate() throws DocumentException, IOException{
        return report.setCreateDate();
    }
    public abstract PdfPCell buildTitle();
    public abstract PdfPCell buildTable();
    public abstract PdfPCell buildChart();
    public abstract List    dataReport();
    public abstract byte[]   getChart(int widht, int height);
}
