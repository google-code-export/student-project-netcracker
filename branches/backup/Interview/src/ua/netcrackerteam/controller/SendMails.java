package ua.netcrackerteam.controller;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import ua.netcrackerteam.configuration.Logable;

public class SendMails implements Logable{

    public static void sendMailToUserAfterReg(String userEmail, String userName, String userPassword) throws EmailException {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            //email.setSmtpPort(587);
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("NetcrackerTeamOdessaOspu@gmail.com", "12345odessa"));
            email.setTLS(true);
            //email.setSSLOnConnect(true);

            email.setFrom("NetcrackerTeamOdessaOspu@gmail.com");
            email.setSubject("Registration successful");
            email.setMsg("Hello " + userName + ", we glad to see you in our Training Center !!!\n" +
                    "Your login information :\n" +
                    "login - " + userName + "\n" +
                    "password - " + userPassword + "");
            email.addTo(userEmail);
            email.send();
        } catch (EmailException ex) {
            ex.printStackTrace();
            logger.getLog().error(ex);
        }
    }

    public static void main(String[] args){
        try {
            sendMailToUserAfterReg("krygina.ua@gmail.com", "dggdfg", "sff");
        } catch (EmailException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}