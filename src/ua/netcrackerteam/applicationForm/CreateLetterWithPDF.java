/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.applicationForm;

import org.apache.commons.mail.ByteArrayDataSource;

import ua.netcrackerteam.DAO.Entities.Interview;
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
 * @author Klitna Tetiana
 */
public class CreateLetterWithPDF {
    
    private String pathPropertiesMail = "resources/mail.properties";
    private String pathPropertiesAuthentification = "resources/Authentification.properties";
    
    private Letter letter;        
    private String userName;
    
    public CreateLetterWithPDF(String userName, Letter letter){
        this.userName = userName;
        this.letter = letter;
    }
    
    /**
     * Send mail to the student with attachment pdf file for interview
     * @param userName 
     */   
    public void sendPDFToStudent(){
        
        try {
          
            Properties propertiesMail = new Properties();
            propertiesMail.load(new FileInputStream(ClassPath.getInstance().getWebInfPath() + pathPropertiesMail));
            
            Properties propertiesAuthentification = new Properties();
            propertiesAuthentification.load(new FileInputStream(ClassPath.getInstance().getWebInfPath()+ pathPropertiesAuthentification));
                                                               
            String sender = propertiesAuthentification.getProperty("mail");
            String senderPassword = propertiesAuthentification.getProperty("password");
            String recipient = HibernateFactory.getInstance().getStudentDAO().getEmailByUserName(userName); 
           
            String subject = "Учебный Центр NetCracker при ОНПУ"; 	         	                    
                         
            Session session = Session.getDefaultInstance(propertiesMail, new DefaultAuthenticator(sender, senderPassword));                              
                                                               
            MimeMultipart mimeMultipart = letter.getLetter();	             
            
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
    

 
}
