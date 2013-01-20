package ua.netcrackerteam.DAO;

import ua.netcrackerteam.configuration.InterviewLoggerSingleton;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Test debugging class for testing methods from DBStandartQuery class
 * @author krygin, maksym, Filipenko
 */
public class DBConnect
{
    protected static InterviewLoggerSingleton logger = InterviewLoggerSingleton.getInstance();
    /**
     * Main debugging method
     * @param args
     * @throws IOException
     */
    public static void main(String args[])  throws IOException
    {
        List list = GetFIOFromForm();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        logger.info();
    }

    /**
     * Test debugging method for using methods from DBStandartQuery class
     * @return list - query result in ArrayList collection                                             `
     */
    public static List GetFIOFromForm() {
        List list = null;
        try {
            list = DBStandartQuery.SelectQuery("f.first_name, f.middle_name, f.last_name, con.info",
                    "form f, contact con",
                    "con.id_form = f.id_form");
            logger.info();
        } catch (NullPointerException er) {
            logger.error(er);
        }
        return list;
    }
}