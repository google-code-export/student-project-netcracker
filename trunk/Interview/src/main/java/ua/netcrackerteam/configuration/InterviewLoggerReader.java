package ua.netcrackerteam.configuration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krygin
 */
public class InterviewLoggerReader {

    public static void main(String[] args) {
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader("interview.log"));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
