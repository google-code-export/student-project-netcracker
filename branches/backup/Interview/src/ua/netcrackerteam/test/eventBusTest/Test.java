package ua.netcrackerteam.test.eventBusTest;

import ua.netcrackerteam.DAO.DAOCoreObject;
import ua.netcrackerteam.controller.StudentData;
import ua.netcrackerteam.controller.StudentPage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test extends DAOCoreObject {
    public static void main(String[] args) throws IOException {
        StudentData studentData = StudentPage.getStudentDataByIdForm(80);
        File f = new File("d://1_new.jpg");
        FileOutputStream fos = new FileOutputStream(f);
        byte[] buffer = studentData.getPhoto();
        fos.write(buffer);
        fos.close();
    }
}
