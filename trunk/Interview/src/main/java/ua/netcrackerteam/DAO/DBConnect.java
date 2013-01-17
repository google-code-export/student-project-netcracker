package main.java.ua.netcrackerteam.DAO;

import java.io.*;
import java.sql.*;
import java.util.*;
import ua.netcrackerteam.DAO.*;

public class DBConnect
{
    public static void main(String args[])  throws IOException
    {
        List list = DBConnect.GetEmp();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    public static List GetEmp() {
        List list = new LinkedList();
        ResultSet rz = DBStandartQuery.SelectQuery("form", "first_name, middle_name, last_name");
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