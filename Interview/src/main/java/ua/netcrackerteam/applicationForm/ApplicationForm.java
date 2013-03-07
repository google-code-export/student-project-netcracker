/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import org.apache.commons.mail.ByteArrayDataSource;
import org.apache.commons.mail.DefaultAuthenticator;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.controller.StudentData;


/**
 *
 * @author tanya
 */
public class ApplicationForm{  
    
   
    private final  String pathMailToStudent = "G:/Проект1/interview/Interview/src/main/java/NetCrackerHTML.html";
    private final  String pathPDFTemplate = "G:/Проект1/interview/Interview/src/main/java/Template.pdf";
    private final  String pathTimesTTF = "G:/Проект1/interview/Interview/src/main/java/times.ttf";
    
    /**
     * Generate pdf with pdf-template and write it to binary stream
     * @param OutputStream memory
     */
    public void generateFormPDF(OutputStream memory) {
             
        try {
                     
            BaseFont font = BaseFont.createFont(pathTimesTTF, "cp1251", BaseFont.EMBEDDED);
            PdfReader reader = new PdfReader(pathPDFTemplate);
            PdfStamper stamper = new PdfStamper(reader, memory);
            AcroFields form = stamper.getAcroFields();
            form.addSubstitutionFont(font);
            fillFormData(form);          
            PdfContentByte content = stamper.getOverContent(1);
            content.addImage(reciveImage());        
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
    public void fillFormData(AcroFields form) throws IOException, DocumentException{
        
        StudentData studentData = new StudentData();

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);
        
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
        //form.setField("aboutCenter", studentData.getStudentHowHearAboutCentre());
        form.setField("additional", studentData.getStudentSelfAdditionalInformation());
               
    }
    
    /**
     * Get photo student for add to the Form
     * @return Image
     * @throws BadElementException
     * @throws MalformedURLException
     * @throws IOException 
     */
    public Image reciveImage() throws BadElementException, MalformedURLException, IOException{
        
        Image img = Image.getInstance("G:/Проект1/interview/Interview/src/main/java/1.jpg");
        img.setAbsolutePosition(70f, 615f);
        img.scaleToFit(150, 140);
        
        return img;
    }
    
    /**
     * Read html file, generated for create letter to send student
     * @return String
     */
    private String readHTMLContent(){
        
        StringBuilder builder = new StringBuilder();
        
        BufferedReader reader= null;
        
        try{
            reader = new BufferedReader(new FileReader(pathMailToStudent));
            String currentStr = "";
            while((currentStr =reader.readLine()) != null){
                builder.append(currentStr);
            }
        } catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(ApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return  builder.toString();
    }
    
    /**
     * Send mail to the student with attachment pdf file for interview
     * @param userName 
     */   
    public void sendPDFToStudent(String userName){
        
        try {
            Properties propertiesMail = new Properties();
            propertiesMail.setProperty("mail.debug","false");
            propertiesMail.setProperty("mail.smtp.port","465");
            propertiesMail.setProperty("mail.smtp.socketFactory.port","465");
            propertiesMail.setProperty("mail.transport.protocol","smtp");
            propertiesMail.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            propertiesMail.setProperty("mail.smtp.auth","true");
            propertiesMail.setProperty("mail.smtp.host","smtp.gmail.com");
            propertiesMail.setProperty("mail.smtp.ssl.enable","true");

            
            Properties propertiesAuthentification = new Properties();
            propertiesAuthentification.setProperty("mail", "NetcrackerTeamOdessaOspu@gmail.com");
            propertiesAuthentification.setProperty("password", "12345odessa");
                                                               
            String sender = propertiesAuthentification.getProperty("mail");
            String senderPassword = propertiesAuthentification.getProperty("password");
            String recipient = HibernateFactory.getInstance().getStudentDAO().getEmailByUserName(userName); 
           
            String subject = "Учебный Центр NetCracker при ОНПУ"; 	         	                    
                         
            Session session = Session.getDefaultInstance(propertiesMail, new DefaultAuthenticator(sender, senderPassword));                              
                                                               
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(getHTMLBodyPart(userName));	       
            mimeMultipart.addBodyPart(getPDFBodyPart());	             
            
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(recipient);	             
            
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);           
            mimeMessage.setContent(mimeMultipart);                  	             
            
            Transport.send(mimeMessage);
           /*Transport transport = session.getTransport();
           transport.connect();
           transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
           transport.close();*/
        } catch (IOException ex) {
            Logger.getLogger(ApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    /**
     * Insert to the letter for student - name, date start and end interview
     * @param userName
     * @return MimeBodyPart
     * @throws MessagingException 
     */
    private MimeBodyPart getHTMLBodyPart(String userName) throws MessagingException{
        
        Interview interview = (HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName)).getInterview();
    
        MimeBodyPart messageBodyPart = new MimeBodyPart();
                               
       String htmlText = readHTMLContent();
       htmlText = htmlText.replace("[userName]",  userName);
       htmlText = htmlText.replace("[dateStart]", getDate(interview.getStartDate()));
       htmlText = htmlText.replace("[timeStart]", getTime(interview.getStartDate()));   
       messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
            
       return messageBodyPart;
    }
    
    /**
     * Attachment to the letter pdf from binary stream
     * @return MimeBodyPart
     * @throws IOException
     * @throws MessagingException 
     */
    private MimeBodyPart getPDFBodyPart() throws IOException, MessagingException{
        
       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       generateFormPDF(outputStream);
       byte[] bytes = outputStream.toByteArray();	             
       DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
       MimeBodyPart pdfBodyPart = new MimeBodyPart();
       pdfBodyPart.setDataHandler(new DataHandler(dataSource));
       pdfBodyPart.setFileName("FormForInterview.pdf"); 
       
       return pdfBodyPart;
    }
    
    /**
     * Get time from date for add to letter
     * @param date
     * @return String
     */
    private  String getTime(Date date){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }
    
       /**
     * Get day and mounth from date for add to letter
     * @param date
     * @return String
     */
    private  String getDate(Date date){
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        return dateFormat.format(date);
    }
    public byte[] pdfForView(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generateFormPDF(outputStream);
        byte[] bytes = outputStream.toByteArray();
        
        return bytes;
    }
       
        public static void main(String[] args) {

          (new ApplicationForm()).sendPDFToStudent("Tresh");

       }
    
}
