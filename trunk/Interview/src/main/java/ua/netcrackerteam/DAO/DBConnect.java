package ua.netcrackerteam.DAO;

import java.io.*;
import java.sql.*;
import java.util.*;

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
        List list = DBConnect.GetFIOFromForm();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    /**
     * Test debugging method for using methods from DBStandartQuery class
     * @return list - query result in ArrayList collection
     */
    public static List GetFIOFromForm() {
        List list = null;
        ResultSet rz = null;
        try {
            rz = DBStandartQuery.SelectQuery("f.first_name, f.middle_name, f.last_name, con.info",
                    "form form f, contact con",
                    "con.id_form = f.id_form");
            list = DBStandartQuery.ResultSetHandler(rz);
        } catch (NullPointerException er) {
            System.out.println("Error occurred in method GetFIOFromForm");
            System.out.println(er.getMessage());
        }
        return list;
    }
}