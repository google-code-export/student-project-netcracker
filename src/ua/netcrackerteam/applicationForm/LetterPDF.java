package ua.netcrackerteam.applicationForm;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.ByteArrayDataSource;

import ua.netcrackerteam.DAO.Entities.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;

public class LetterPDF extends Letter{
	
	private String userName;

	public LetterPDF(String userName) {		
		super(ClassPath.getInstance().getWebInfPath().concat("resources/NetCrackerHTML.html"));	
		this.userName = userName;
	}

	@Override
	public MimeMultipart getLetter() {
		
		   MimeMultipart mimeMultipart = new MimeMultipart();
		   
        try {
			mimeMultipart.addBodyPart(getHTMLBodyPart());
			mimeMultipart.addBodyPart(getPDFBodyPart());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
           return mimeMultipart;
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
                               
       readHTMLContent();
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
       
    	 String[] months = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};
 	    
         DateFormatSymbols dfs = new DateFormatSymbols(new Locale("ru"));
         dfs.setMonths(months);
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM", dfs);    
         return dateFormat.format(date);
    } 
	
	

}
