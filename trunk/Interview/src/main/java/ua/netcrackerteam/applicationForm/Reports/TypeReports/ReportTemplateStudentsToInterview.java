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
import ua.netcrackerteam.DAO.Entities.Form;
import ua.netcrackerteam.DAO.Entities.Interview;
import ua.netcrackerteam.applicationForm.Reports.ReportTemplateBuilder;

import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.controller.StudentData;
import ua.netcrackerteam.controller.StudentPage;

/**
 *
 * @author Klitna Tetiana
 */
public class ReportTemplateStudentsToInterview extends ReportTemplateBuilder{  
  
    private List<Interview> interviews;
    
    private List reportForView = new LinkedList();;
    private List reportForPrint = new LinkedList();

    public ReportTemplateStudentsToInterview(int idInterview){   
      Interview interviewById = HibernateFactory.getInstance().getDAOInterview().getInterview(idInterview);
      
      interviews = new ArrayList<Interview>();
      if(interviewById != null){
        interviews.add(interviewById);
      }
      
      getReport();
     }
    
    public ReportTemplateStudentsToInterview(){
        interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
        
        if(interviews == null){
            interviews = new ArrayList<Interview>();
        }
        getReport();
    }

   
    @Override
    public PdfPCell buildTitle() {
    
        PdfPCell cell = new PdfPCell();
        try {
            cell = report.setTitle("Cписки абитуриентов на собеседование");
        } catch (IOException ex) {
            Logger.getLogger(ReportTemplateStudentsToInterview.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportTemplateStudentsToInterview.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cell;
    }

    @Override
    public PdfPCell buildTable() {
        
        String[] header = new String[]{"№", "Фамилия", "Имя", "ВУЗ", "Телефон"};     
        float[] size = new float[]{0.5f, 1f, 1f, 2f, 1f};       
        
        PdfPCell cell = report.setTable(header, reportForPrint, null, size, true);
    
        return cell;
    }

    @Override
    public PdfPCell buildChart() {
        return new PdfPCell();
    }

    @Override
    public List dataReport() {      
        return reportForView;
    }

    @Override
    public byte[] getChart(int widht, int height) {
        return null;
    }
    
    private void getReport(){
            
        Iterator<Interview> iterator = interviews.iterator();
        while(iterator.hasNext()){
          Interview interview = iterator.next();
          
          if(interview.getIdInterview() == 0){ continue;} //reserve time for hr
          
          List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
          
          Iterator<Form> iteratorForm = forms.iterator();
          while(iteratorForm.hasNext()){
              StudentData studentData = StudentPage.getStudentDataByIdForm(iteratorForm.next().getIdForm());
              String[] data = {"" + studentData.getIdForm(), studentData.getStudentFirstName(),
                                    studentData.getStudentLastName(), studentData.getStudentInstitute().getName(),
                                    studentData.getStudentTelephone()};
              
              reportForView.add(data);
              reportForPrint.add(new Object[]{interview, data});
          }
        }
        
    }
    
}
