package ua.netcrackerteam.test.eventBusTest;

import java.io.File;

/**
 *
 */
public class DeleteFolders implements EventHandler{

    @Override
    public void handleEvent(Event event) {
        if (event.type == Event.EVENT_TYPE_SEND_PDF) {
            String emailName = "krygina.ua@gmail.com";
            File dir = new File("src\\"+emailName+"\\");
            DeleteFolders.deletePDFDirectory(dir);
        }
    }

    public static void deletePDFDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                File f = new File(dir, children[i]);
                deletePDFDirectory(f);
            }
            dir.delete();
        } else dir.delete();
    }

    /*public static void main(String[] args) {

    }*/
}
