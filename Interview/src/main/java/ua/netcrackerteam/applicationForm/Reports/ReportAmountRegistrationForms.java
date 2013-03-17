/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm.Reports;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import ua.netcrackerteam.DAO.DAOReport;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportAmountRegistrationForms implements TypeOfViewReport{
    
    public byte[] viewReport() {  
         
         DAOReport report = new DAOReport();
         List dataReport = report.getReportAmountRegistrationForms();
         
         if(dataReport == null){
             dataReport = new ArrayList();
         }

        Report reportDocument = new Report(dataReport, new String[]{"Зарегестрировано на сайте","Заполнено анкет","Зарегестрировано на собеседования"});       
        ByteArrayOutputStream outputStream = reportDocument.createTemplate("Статистика зарегестрированных студентов", new float[]{2f, 2f, 2f} );
        
        return outputStream.toByteArray();
     }
    
      
}
