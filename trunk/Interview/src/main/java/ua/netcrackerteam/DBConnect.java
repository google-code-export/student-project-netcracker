package ua.netcrackerteam;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBConnect
{
    public static void main(String args[])  throws IOException
    {
        List list = DBConnect.GetEmp();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    public static List GetEmp(){
        List<String> list = new ArrayList<String>();
        Locale.setDefault(Locale.ENGLISH);
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.print("JDBC-Driver is OK!\n");
        }
        catch(java.lang.ClassNotFoundException er)
        {
            System.out.print("JDBC-Driver is wrong!\n");
            System.out.println(er.getMessage());
            er.printStackTrace();
            System.exit(0);
        }
        String url = new String("");
        try
        {
            url="jdbc:oracle:thin:@85.238.104.112:1521:XE";
            String userid="netcracker";
            String userp="oracle";
            Connection con=DriverManager.getConnection(
                    url,userid,userp);
            System.out.println("Connection with URL=" + url + " is OK!");
            try
            {
                Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rz=stm.executeQuery("select * from form");
                while(rz.next())
                {
                    list.add(rz.getString(1));
                    list.add(rz.getString(2));
                    list.add(rz.getString(3));
                    list.add(rz.getString(4));
                }
            }
            catch(SQLException er)
            {
                System.out.println("SELECT is wrong!");
                System.out.println(er.getMessage());
            }
            con.close();
        }
        catch(SQLException er)
        {
            System.out.println("Connection  " + url + " is wrong!");
            System.out.println(er.getMessage());
        }
        return list;
    }
}