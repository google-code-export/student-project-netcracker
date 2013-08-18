package ua.netcrackerteam.applicationForm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.internet.MimeMultipart;

public abstract class Letter {
	
	private String pathMailToStudent;
	protected String htmlText;
	
	public Letter(String pathMailToStudent){
		this.pathMailToStudent = pathMailToStudent;
	}
	
	protected void readHTMLContent(){
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
	        
	        
	        htmlText = builder.toString();
	}
	
	abstract MimeMultipart  getLetter();
}
