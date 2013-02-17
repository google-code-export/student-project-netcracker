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

        Map map = form.getFields();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            System.out.println(str);
        }

        form.setField("info1", studentData.getStudentLastName());
        form.setField("info2", studentData.getStudentFirstName());
        form.setField("info3", studentData.getStudentMiddleName());
        form.setField("info4", String.valueOf(studentData.getStudentInstitute()));
        form.setField("info5", String.valueOf(studentData.getStudentInstituteCourse()));
        form.setField("info6", String.valueOf(studentData.getStudentFaculty()));
        form.setField("info7", String.valueOf(studentData.getStudentCathedra()));
        form.setField("info8", String.valueOf(studentData.getStudentInstituteGradYear()));
        form.setField("year1", String.valueOf(currentYear));
        form.setField("year2", String.valueOf(currentYear));
        form.setField("id1", String.valueOf(studentData.getIdForm()));
        form.setField("id2", String.valueOf(studentData.getIdForm()));
        form.setField("email 1", studentData.getStudentEmailFirst());
        form.setField("email 2", studentData.getStudentEmailSecond());
        form.setField("interestDevelopment", studentData.getStudentInterestDevelopment());
        form.setField("interestOther", studentData.getStudentInterestOther());
        form.setField("interestStudy", studentData.getStudentInterestStudy());
        form.setField("interestWork", studentData.getStudentInterestWork());
        form.setField("tel", studentData.getStudentTelephone());
        form.setField("otherContacts", studentData.getStudentOtherContact());
        form.setField("typeWorkDifferent", studentData.getStudentWorkTypeVarious());
        form.setField("typeWorkLead", studentData.getStudentWorkTypeManagement());
        form.setField("typeWorkOther", studentData.getStudentWorkTypeOther());
        form.setField("typeWorkSales", studentData.getStudentWorkTypeSale());
        form.setField("typeWorkSpeciality", studentData.getStudentWorkTypeDeepSpec());
        form.setField("technology1", String.valueOf(studentData.getStudentKnowledgeNetwork()));
        form.setField("technology2", String.valueOf(studentData.getStudentKnowledgeEfficientAlgorithms()));
        form.setField("technology3", String.valueOf(studentData.getStudentKnowledgeOOP()));
        form.setField("technology4", String.valueOf(studentData.getStudentKnowledgeDB()));
        form.setField("technology5", String.valueOf(studentData.getStudentKnowledgeWeb()));
        form.setField("technology6", String.valueOf(studentData.getStudentKnowledgeGUI()));
        form.setField("technology9", String.valueOf(studentData.getStudentKnowledgeProgramDesign()));
        form.setField("technology8_1", studentData.getStudentKnowledgeOther1() + " " + studentData.getStudentKnowledgeOther1Mark());
        form.setField("technology8_2", studentData.getStudentKnowledgeOther2() + " " + studentData.getStudentKnowledgeOther2Mark());
        form.setField("technology8_3", studentData.getStudentKnowledgeOther3() + " " + studentData.getStudentKnowledgeOther3Mark());
        form.setField("technology7", String.valueOf(studentData.getStudentKnowledgeNetworkProgramming()));
        form.setField("language1", studentData.getStudentLanguage1());
        form.setField("language2", studentData.getStudentLanguage2());
        form.setField("language3", studentData.getStudentLanguage3());
        form.setField("mark1", String.valueOf(studentData.getStudentCPlusPlusMark()));
        form.setField("mark2", String.valueOf(studentData.getStudentJavaMark()));
        form.setField("mark3", String.valueOf(studentData.getStudentLanguage1Mark()));
        form.setField("mark4", String.valueOf(studentData.getStudentLanguage2Mark()));
        form.setField("mark5", String.valueOf(studentData.getStudentLanguage3Mark()));
        form.setField("project", studentData.getStudentExperienceProjects());
        form.setField("promises", studentData.getStudentReasonOffer());
        form.setField("english1", String.valueOf(studentData.getStudentEnglishWriteMark()));
        form.setField("english2", String.valueOf(studentData.getStudentEnglishReadMark()));
        form.setField("english3", String.valueOf(studentData.getStudentEnglishSpeakMark()));
        form.setField("aboutCenter", studentData.getStudentHowHearAboutCentre());
        form.setField("additional", studentData.getStudentSelfAdditionalInformation());

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
