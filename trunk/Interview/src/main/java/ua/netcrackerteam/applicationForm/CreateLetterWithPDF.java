/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

import org.apache.commons.mail.ByteArrayDataSource;
import ua.netcrackerteam.DAO.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;

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
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;

/**
 *
 * @author Klitna
 */
public class CreateLetterWithPDF {
    
    private String pathMailToStudent = "resources/NetCrackerHTML.html";
    private String pathPropertiesMail = "resources/mail.properties";
    private String pathPropertiesAuthentification = "resources/Authentification.properties";
    private String path = ClassPath.getInstance().getWebInfPath();
    
            
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
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path + pathMailToStudent), "UTF-8"));
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
            propertiesMail.load(new FileInputStream(path + pathPropertiesMail));
            
            Properties propertiesAuthentification = new Properties();
            propertiesAuthentification.load(new FileInputStream(path+ pathPropertiesAuthentification));
                                                               
            String sender = propertiesAuthentification.getProperty("mail");
            String senderPassword = propertiesAuthentification.getProperty("password");
            String recipient = HibernateFactory.getInstance().getStudentDAO().getEmailByUserName(userName); 
           
            String subject = "Учебный Центр NetCracker при ОНПУ"; 	         	                    
                         
            Session session = Session.getDefaultInstance(propertiesMail, new DefaultAuthenticator(sender, senderPassword));                              
                                                               
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(getHTMLBodyPart());	       
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
    
    /**
     * Insert to the letter for student - name, date start and end interview
     * @param userName
     * @return MimeBodyPart
     * @throws MessagingException 
     */
    private MimeBodyPart getHTMLBodyPart() throws MessagingException{
        
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
      
       ApplicationForm form = new ApplicationForm(userName);
       byte[] bytes = form.generateFormPDF();	             
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
}
