package ua.netcrackerteam.test.eventBusTest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class SendMail implements EventHandler{
    @Override
    public void handleEvent(Event event) {
        if (event.type == Event.EVENT_TYPE_SEND_PDF) {
            SendMail simpleMail = new SendMail();
            try {
                simpleMail.sendTLS();
            } catch (EmailException e) {
                e.printStackTrace();
            }
            EventBus.getInstance().fireEvent(new Event(Event.EVENT_TYPE_DELETE_PDF));
        }
    }

    public static void sendTLS() throws EmailException {
        try {
            MultiPartEmail email = new MultiPartEmail();
            String emailName = "krygina.ua@gmail.com";
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("NetcrackerTeamOdessaOspu@gmail.com", "12345odessa"));
            email.setTLS(true);
            email.setFrom("NetcrackerTeamOdessaOspu@gmail.com");
            email.setSubject("TestMail");
            email.setMsg("This is a test mail ... :-)");
            email.addTo(emailName);

            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath("src\\"+emailName+"\\FirstPdf.pdf");
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("My Picture");
            attachment.setName("application_form_" + emailName + ".pdf");

            email.attach(attachment);
            email.send();
        } catch (EmailException ex) {
            ex.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        SendMails simpleMail = new SendMails();
        try {
            simpleMail.sendTLS();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }*/
}