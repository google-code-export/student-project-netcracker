/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports.TypeReports;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTable;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableWithGroups;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportTemplateAmountStudentInstitute extends ReportTemplateBuilder{

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
        String[] header = new String[]{"№", "Фамилия", "Имя", "Телефон"};       
        
         float[] size = new float[]{0.5f, 1.5f, 1.5f, 1.5f};
         DesignTable table = new DesignTableWithGroups(size);
     
         report.setDesignTable(table);
         PdfPCell cell = report.setTable(header, dataReport(), null);
        
  
        return cell;
    }

    @Override
    public PdfPCell buildChart() {
       throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List dataReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte[] getChart(int widht, int height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    
}
