/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports.TypeReports;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTable;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableFlat;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportTemplateResultOfInterviewsDetail extends ReportTemplateBuilder{
    
    private List reportData;
    
    public ReportTemplateResultOfInterviewsDetail(){
         DAOReport reportDAO = new DAOReport();
         reportData = reportDAO.getResult();
         
         if(reportData == null){
             reportData = new ArrayList();
         }
    }
    
    @Override
    public PdfPCell buildTitle() {
        PdfPCell cell= new PdfPCell();
        try {       
            cell = report.setTitle("Итоги по собеседованиям");
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateAmountStudentInstitute.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportTemplateAmountStudentInstitute.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return cell;
    }

    @Override
    public PdfPCell buildTable() {
                
        String[] header = new String[]{"Дата", "Максимум", "Записалось", "Оценено", "Осталось"}; 
        String[] footer = getFooter();
        
        float[] size = new float[]{2f, 1f, 1f, 1f, 1f}; 
        DesignTable table = new DesignTableFlat(size);
        
        report.setDesignTable(table);
        PdfPCell cell = report.setTable(header, reportData, footer);
    
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
    
    private String[] getFooter(){
        
        Iterator iterator = reportData.iterator();
        
        int max =0;
        int form=0;
        int formWithMatk = 0;
        
        while(iterator.hasNext()){
           
           Object[] row = (Object[])iterator.next();
           max += Integer.parseInt(row[1].toString());
           form += Integer.parseInt(row[2].toString());
           formWithMatk += Integer.parseInt(row[3].toString()); 
        }
        
        return new String[]{"Итого:", "" + max, "" + form, "" + formWithMatk, "" + (form - formWithMatk)};
    }
    
}
