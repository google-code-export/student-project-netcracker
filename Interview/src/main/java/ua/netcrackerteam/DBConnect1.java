package ua.netcrackerteam;

import ua.netcrackerteam.DAO.DbConnectionSingleton;
import java.io.*;
import java.sql.*;
import java.util.*;

public class DBConnect1
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
        Connection con = DbConnectionSingleton.getInstance().getConn();
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
        
        return list;
    }
}