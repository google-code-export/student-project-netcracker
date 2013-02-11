package ua.netcrackerteam.controller;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import ua.netcrackerteam.configuration.Logable;

public class SendMails implements Logable{

    public static void sendMailToUserAfterReg(String userEmail, String userName, String userPassword) throws EmailException {
        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("NetcrackerTeamOdessaOspu@gmail.com", "12345odessa"));
            email.setTLS(true);
            email.setFrom("NetcrackerTeamOdessaOspu@gmail.com");
            email.setSubject("Registration successful");
            email.setMsg("Hello " + userName + ", we glad to see you in our Trining Center !!!\n" +
                    "Your login information :\n" +
                    "login - " + userName + "\n" +
                    "password - " + userPassword + "");
            email.addTo(userEmail);
            email.send();
            logger.info();
        } catch (EmailException ex) {
            ex.printStackTrace();
            logger.getLog().error(ex);
        }
    }
}