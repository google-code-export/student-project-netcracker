package main.java.ua.netcrackerteam.DAO;

import java.io.*;
import java.sql.*;
import java.util.*;
import ua.netcrackerteam.DAO.*;

/**
 * Test debugging class for testing methods from DBStandartQuery class
 * @author krygin, maksym
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
     * Test debugging method for testing methods from DBStandartQuery class
     * @return list - query result in ArrayList collection
     */
    public static List GetFIOFromForm() {
        List list = new ArrayList();
        String[] fields = {"first_name, middle_name, last_name"};
        String[] tables = {"form"};
        String[] whereCondition = {"first_name = 'Дмитрий'"};
        ResultSet rz = DBStandartQuery.SelectQuery(fields, tables, whereCondition);
        try {
            while(rz.next())
            {
                list.add(rz.getString(1));
                list.add(rz.getString(2));
                list.add(rz.getString(3));
            }
        } catch (SQLException er) {
            System.out.println("SELECT is wrong!");
            System.out.println(er.getMessage());
        }
        return list;
    }
}