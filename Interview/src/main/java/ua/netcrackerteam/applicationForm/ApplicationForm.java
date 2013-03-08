/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.netcrackerteam.controller.StudentData;
import ua.netcrackerteam.controller.StudentPage;

/**
 *
 * @author tanya
 */
public class ApplicationForm{   
  
    private final  String pathPDFTemplate = "resources/Template.pdf";
    private final  String pathTimesTTF = "resources/times.ttf";
       
    private StudentData studentData;
    
    public ApplicationForm(int idForm){
        studentData = StudentPage.getStudentDataByIdForm(idForm);
    }
    
    public ApplicationForm(String userName){
      studentData = StudentPage.getStudentDataByUserName(userName);
    }
  
    /**
     * Generate pdf with pdf-template and write it to binary stream
     * @param OutputStream memory
     */
    public void generateFormPDF(OutputStream memory) {
             
        try {
            String path = ClassPath.getInstance().getWebInfPath();
            BaseFont font = BaseFont.createFont(path + pathTimesTTF, "cp1251", BaseFont.EMBEDDED);            
            PdfReader reader = new PdfReader(path + pathPDFTemplate);
            PdfStamper stamper = new PdfStamper(reader, memory);
            AcroFields form = stamper.getAcroFields();
            form.addSubstitutionFont(font);
            fillFormData(form);          
            PdfContentByte content = stamper.getOverContent(1);
            Image image = Image.getInstance(studentData.getPhoto());
            image.scaleToFit(150, 140);  
            image.setAbsolutePosition(60f, 625f);
            content.addImage(image);        
            stamper.close();
            
        } catch (DocumentException ex) {
            Logger.getLogger(ApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
              
      
    }
    
    /**
     * Fill pdf template with data from Form
     * @param form 
     * @throws IOException
     * @throws DocumentException      
     */
     private void fillFormData(AcroFields fields) throws IOException, DocumentException{
        
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);
             
        fields.setField("info1", studentData.getStudentLastName());
        fields.setField("info2", studentData.getStudentFirstName());
        fields.setField("info3", studentData.getStudentMiddleName());
        fields.setField("info4", String.valueOf(studentData.getStudentInstitute()));
        fields.setField("info5", String.valueOf(studentData.getStudentInstituteCourse()));
        fields.setField("info6", String.valueOf(studentData.getStudentFaculty()));
        fields.setField("info7", String.valueOf(studentData.getStudentCathedra()));
        fields.setField("info8", String.valueOf(studentData.getStudentInstituteGradYear()));
        fields.setField("year1", String.valueOf(currentYear));
        fields.setField("year2", String.valueOf(currentYear));
        fields.setField("id1", String.valueOf(studentData.getIdForm()));
        fields.setField("id2", String.valueOf(studentData.getIdForm()));
        fields.setField("email 1", studentData.getStudentEmailFirst());
        fields.setField("email 2", studentData.getStudentEmailSecond());
        fields.setField("interestDevelopment", studentData.getStudentInterestDevelopment());
        fields.setField("interestOther", String.valueOf(studentData.getStudentInterestOther()));//-
        fields.setField("interestStudy", studentData.getStudentInterestStudy());
        fields.setField("interestWork", studentData.getStudentInterestWork());
        fields.setField("tel", studentData.getStudentTelephone());
        fields.setField("otherContacts", studentData.getStudentOtherContact());
        fields.setField("typeWorkDifferent", studentData.getStudentWorkTypeVarious());
        fields.setField("typeWorkLead", studentData.getStudentWorkTypeManagement());
        fields.setField("typeWorkOther", String.valueOf(studentData.getStudentWorkTypeOther()));//--
        fields.setField("typeWorkSales", studentData.getStudentWorkTypeSale());
        fields.setField("typeWorkSpeciality", studentData.getStudentWorkTypeDeepSpec());
        fields.setField("technology1", String.valueOf(studentData.getStudentKnowledgeNetwork()));
        fields.setField("technology2", String.valueOf(studentData.getStudentKnowledgeEfficientAlgorithms()));
        fields.setField("technology3", String.valueOf(studentData.getStudentKnowledgeOOP()));
        fields.setField("technology4", String.valueOf(studentData.getStudentKnowledgeDB()));
        fields.setField("technology5", String.valueOf(studentData.getStudentKnowledgeWeb()));
        fields.setField("technology6", String.valueOf(studentData.getStudentKnowledgeGUI()));
        fields.setField("technology9", String.valueOf(studentData.getStudentKnowledgeProgramDesign()));
        fields.setField("technology8_1", studentData.getStudentKnowledgeOther1() + " " + studentData.getStudentKnowledgeOther1Mark());
        fields.setField("technology8_2", studentData.getStudentKnowledgeOther2() + " " + studentData.getStudentKnowledgeOther2Mark());
        fields.setField("technology8_3", studentData.getStudentKnowledgeOther3() + " " + studentData.getStudentKnowledgeOther3Mark());
        fields.setField("technology7", String.valueOf(studentData.getStudentKnowledgeNetworkProgramming()));
        fields.setField("language1", studentData.getStudentLanguage1());
        fields.setField("language2", studentData.getStudentLanguage2());
        fields.setField("language3", studentData.getStudentLanguage3());
        fields.setField("mark1", String.valueOf(studentData.getStudentCPlusPlusMark()));
        fields.setField("mark2", String.valueOf(studentData.getStudentJavaMark()));
        fields.setField("mark3", String.valueOf(studentData.getStudentLanguage1Mark()));
        fields.setField("mark4", String.valueOf(studentData.getStudentLanguage2Mark()));
        fields.setField("mark5", String.valueOf(studentData.getStudentLanguage3Mark()));
        fields.setField("project", studentData.getStudentExperienceProjects());
        fields.setField("promises", studentData.getStudentReasonOffer());
        fields.setField("english1", String.valueOf(studentData.getStudentEnglishWriteMark()));
        fields.setField("english2", String.valueOf(studentData.getStudentEnglishReadMark()));
        fields.setField("english3", String.valueOf(studentData.getStudentEnglishSpeakMark()));
        //fields.setField("aboutCenter", studentData.getStudentHowHearAboutCentre());
        fields.setField("additional", studentData.getStudentSelfAdditionalInformation());               
    }
        
    /**
     * Get photo student for add to the Form
     * @return Image
     * @throws BadElementException
     * @throws MalformedURLException
     * @throws IOException 
     */
    /*public Image reciveImage() throws BadElementException, MalformedURLException, IOException{
        
        Image img = Image.getInstance(pathImg);
        img.setAbsolutePosition(70f, 615f);
        img.scaleToFit(150, 140);        
        return img; 
    }*/
    
    public byte[] pdfForView(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generateFormPDF(outputStream);
        byte[] bytes = outputStream.toByteArray();
        
        return bytes;
    }
       
}
