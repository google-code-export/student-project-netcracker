/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import ua.netcrackerteam.controller.StudentData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author tanya
 */
public class ApplicationForm implements Observer{
    
    /**
     * Unique identifier for form
     */
    public int idForm;
    /**
     * interview
     */
    public Observable interview;
    
    /**
     * Called when the interview was changed by hr
     */
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Checking the filling of fields
     * @return true/false
     */
    public boolean checkFields(){
        return true;
    }
    /**    
     * Check size photo (max size 300 kb)
     * @return true/false
     */
    public boolean checkSizePhoto(){
        return true;
    }
    
    /**
     * Create from (pdf-format)
     */
    public static void generateFormPDF() throws DocumentException, IOException {
        BaseFont font = BaseFont.createFont("src\\main\\java\\times.ttf", "cp1251", BaseFont.EMBEDDED);
        PdfReader reader = new PdfReader("src\\main\\java\\Template.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("src\\Form.pdf"));
        AcroFields form = stamper.getAcroFields();
        form.addSubstitutionFont(font);

        StudentData studentData = new StudentData();

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);

        form.setField("info1", studentData.getStudentLastName());
        form.setField("info2", studentData.getStudentFirstName());
        form.setField("info3", studentData.getStudentMiddleName());
        form.setField("info4", studentData.getStudentInstitute());
        form.setField("info5", String.valueOf(studentData.getStudentInstituteCourse()));
        form.setField("info6", studentData.getStudentFaculty());
        form.setField("info7", studentData.getStudentCathedra());
        form.setField("info8", String.valueOf(studentData.getStudentInstituteGradYear()));
        form.setField("year1", String.valueOf(currentYear));
        form.setField("year2", String.valueOf(currentYear));
        form.setField("id1", String.valueOf(studentData.getIdForm()));
        form.setField("id2", String.valueOf(studentData.getIdForm()));
        form.setField("email 1", "sdfsd");

        PdfContentByte content = stamper.getOverContent(1);
        Image img = Image.getInstance("src\\main\\java\\1.jpg");
        img.setAbsolutePosition(70f, 615f);
        img.scaleAbsolute(150, 140);

        content.addImage(img);
        stamper.close();
    }

    public static void main(String[] args){
        try {
            generateFormPDF();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
