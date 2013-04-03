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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.netcrackerteam.DAO.DAOReport;
import ua.netcrackerteam.DAO.Entities.Institute;
import ua.netcrackerteam.applicationForm.Reports.Elements.Chart;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTable;
import ua.netcrackerteam.applicationForm.Reports.Elements.DesignTableWithGroups;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportTemplateAmountStudentCourse extends ReportTemplateBuilder{
    
    private List<Integer> courses;    
    List reportData = new LinkedList();
    
    public ReportTemplateAmountStudentCourse(){
        DAOReport reportDAO = new DAOReport();
        courses = reportDAO.getCourses();
        
        if(courses == null){
            courses = new ArrayList<Integer>();
        }
        getReport();
    }
    
    @Override
    public PdfPCell buildTitle() {
        PdfPCell cell= new PdfPCell();
        try {       
            cell = report.setTitle("Количество абитуриентов по курсам");
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateAmountStudentCourse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportTemplateAmountStudentCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return cell;
    }

    @Override
    public PdfPCell buildTable() {
        String[] header = new String[]{"№", "Фамилия", "Имя", "Email", "Телефон"};  
                
         float[] size = new float[]{0.5f, 1.5f, 1.5f, 1.5f, 1.5f};
         DesignTable table = new DesignTableWithGroups(size);
     
         report.setDesignTable(table);
         PdfPCell cell = report.setTable(header, reportData, null);
        
  
        return cell;
    }
  

    @Override
    public List dataReport() {
       return reportData;
    }
    
    @Override
    public PdfPCell buildChart() {
        return new PdfPCell();
    }
      
    @Override
    public byte[] getChart(int widht, int height) {
        return null;
    }
    
     private void getReport(){
                   
        Iterator<Integer> iterator = courses.iterator();
        while(iterator.hasNext()){
            
          Integer course = iterator.next();                           
          List forms = (new DAOReport()).getFormByCourse(course);           
                   
          reportData.add(new Object[]{"" + course, forms});
                  
        }
      }
    
}
