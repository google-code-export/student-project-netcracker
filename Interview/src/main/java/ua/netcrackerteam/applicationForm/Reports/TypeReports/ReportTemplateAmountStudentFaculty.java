/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports.TypeReports;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.DAO.Entities.Institute;
import ua.netcrackerteam.DAO.Entities.Interview;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTable;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableFlat;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableWithGroups;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportTemplateAmountStudentFaculty extends ReportTemplateBuilder{

    private  List reportData = new LinkedList();
    
    public ReportTemplateAmountStudentFaculty(){
        
        DAOReport reportDAO = new DAOReport();
        List<Institute> institutes = reportDAO.getUnit(0, 0, 0);
        
        if(institutes == null){
            institutes = new ArrayList();
        }
        
        Iterator<Institute> iterator = institutes.iterator();
        while(iterator.hasNext()){
            
          Institute institute = iterator.next();                           
          List faculties = (new DAOReport()).getAmountByFaculty(institute.getInstituteId());                
          
          if(!faculties.isEmpty()){
            reportData.add(new Object[]{institute.getName(), faculties});}
                  
        }
        
    }
    
    @Override
    public PdfPCell buildTitle() {
        PdfPCell cell= new PdfPCell();
        try {       
            cell = report.setTitle("Количество абитуриентов по факультетам");
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateAmountStudentFaculty.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportTemplateAmountStudentFaculty.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return cell;
    }

    @Override
    public PdfPCell buildTable() {
       String[] header = new String[]{"Факультет", "Пришедшие", "Не пришедшие", "Всего"};       
        
         float[] size = new float[]{2f, 1f, 1f, 1f};
         DesignTable table = new DesignTableWithGroups(size);
     
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
