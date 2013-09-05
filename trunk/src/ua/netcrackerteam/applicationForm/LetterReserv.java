package ua.netcrackerteam.applicationForm;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;


public class LetterReserv extends Letter{
	
	private String userName;
	
	public LetterReserv(String userName, String template){
		super(ClassPath.getInstance().getWebInfPath().concat(template));	
		this.userName = userName;
	}

	@Override
	MimeMultipart getLetter() {
		
		 MimeMultipart mimeMultipart = new MimeMultipart();
		   
	        try {
				mimeMultipart.addBodyPart(getHTMLBodyPart());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();		
			}
	           
	           return mimeMultipart;
	}

    private MimeBodyPart getHTMLBodyPart() throws MessagingException{
    
       MimeBodyPart messageBodyPart = new MimeBodyPart();
                               
       readHTMLContent();
       htmlText = htmlText.replace("[userName]",  userName);
       messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
            
       return messageBodyPart;
    }
}


