package ua.netcrackerteam.test.eventBusTest;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class MailBus {
    private static List<EmailSending> listOfHandler = new LinkedList<EmailSending>();

    public static void main(String[] args){
        FirstPDF pdf = new FirstPDF();
        SendMail mail = new SendMail();
        DeleteFolders deleteFolder = new DeleteFolders();
        for(EmailSending es: listOfHandler){
            es.pdfSend();
        }
    }

    public static void addHandler(EmailSending es){
        listOfHandler.add(es);
    }
}
