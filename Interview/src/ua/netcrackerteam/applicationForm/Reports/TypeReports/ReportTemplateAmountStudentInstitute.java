/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports.TypeReports;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.DAO.Entities.Institute;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTable;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableWithGroups;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableFlat;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportTemplateAmountStudentInstitute extends ReportTemplateBuilder{
     
    private List reportData;
            
    public ReportTemplateAmountStudentInstitute(){
        DAOReport reportDAO = new DAOReport();
        reportData = reportDAO.getAmountByInstitute();
        
        if(reportData == null){
            reportData = new ArrayList();
        }
        
        
      }
    
    @Override
    public PdfPCell buildTitle() {
        PdfPCell cell= new PdfPCell();
        try {       
            cell = report.setTitle("Количество абитуриентов по институтам");
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateAmountStudentInstitute.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportTemplateAmountStudentInstitute.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return cell;
    }

    @Override
    public PdfPCell buildTable() {
         String[] header = new String[]{"Институт", "Пришедшие", "Не пришедшие", "Всего"};       
        
         float[] size = new float[]{2f, 1f, 1f, 1f};
         DesignTable table = new DesignTableFlat(size);
     
         report.setDesignTable(table);
         PdfPCell cell = report.setTable(header, reportData, null);
        
  
        return cell;
    }

    @Override
    public PdfPCell buildChart() {
       return new PdfPCell();
    }

    @Override
    public List dataReport() {
        return reportData;
    }

    @Override
    public byte[] getChart(int widht, int height) {
       return null;
    }

     
}
