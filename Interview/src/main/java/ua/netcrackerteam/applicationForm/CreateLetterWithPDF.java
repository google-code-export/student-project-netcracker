/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
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
import org.apache.commons.mail.ByteArrayDataSource;
import org.apache.commons.mail.DefaultAuthenticator;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;

/**
 *
 * @author home
 */
public class CreateLetterWithPDF {
    
    private final  String pathMailToStudent = "G:/Проект1/interview/Interview/src/main/java/NetCrackerHTML.html";
    private String userName;
    
    public CreateLetterWithPDF(String userName){
        this.userName = userName;
    }
    
    /**
     * Read html file, generated for create letter to send student
     * @return String
     */
    private String readHTMLContent(){
        
        StringBuilder builder = new StringBuilder();
        
        BufferedReader reader= null;
        
        try{           
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathMailToStudent), "UTF-8"));
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
    public void sendPDFToStudent(){
        
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
        
        } catch (IOException ex) {
            Logger.getLogger(ApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
        private MimeBodyPart getPDFBodyPart() throws IOException, MessagingException{
        
       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       
       (new ApplicationForm(userName)).generateFormPDF(outputStream);
       
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
    
    
}
