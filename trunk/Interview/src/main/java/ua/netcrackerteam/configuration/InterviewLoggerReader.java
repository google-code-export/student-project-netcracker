package ua.netcrackerteam.configuration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krygin
 */
public class InterviewLoggerReader {
    protected static InterviewLoggerSingleton logger = InterviewLoggerSingleton.getInstance();

    public static void main(String[] args) {
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader("logs/interview.log"));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            logger.info();
        } catch (FileNotFoundException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }

    }
}
