package ua.netcrackerteam.DAO;

import java.io.IOException;
import java.util.List;

/**
 * Test debugging class for testing methods from DBStandartQuery class
 * @author krygin, maksym, Filipenko
 */
public class DBConnect
{
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
        } catch (NullPointerException er) {
            System.out.println("Error occurred in method GetFIOFromForm");
            System.out.println(er.getMessage());
        }
        return list;
    }
}