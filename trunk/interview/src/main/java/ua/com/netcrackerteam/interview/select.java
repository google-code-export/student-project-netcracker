/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.netcrackerteam.interview;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import javax.management.Query;

public class select {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String userid = "root";
        String userp = "123";
        String query = new String("");
        String driverName = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driverName);
            System.out.print("JDBC-Driver is OK!\n");
        } catch (Exception e) {
            System.out.print("JDBC-Driver is wrong!\n");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
        try {
            Connection con = DriverManager.getConnection(url, userid, userp);
            Statement stmt = con.createStatement();
            query = "SELECT team_id, field_1, field_2,field_3,field_4,field_5,"
                    + "field_6,field_7,field_8,field_9 FROM ncteam";
            ResultSet result = stmt.executeQuery(query);
            Date start = new Date();
            while (result.next()) {
                String rezult = "id = " 
                        + result.getString(1) + " | " 
                        + result.getString(2) + " | " 
                        + result.getString(3) + " | "
                        + result.getString(4) + " | " 
                        + result.getString(5) + " | " 
                        + result.getString(6) + " | " 
                        + result.getString(7) + " | " 
                        + result.getString(8) + " | " 
                        + result.getString(9) + " | " 
                        + result.getString(10);
                System.out.println(rezult);
            }
            Date end = new Date();
            System.out.println("Total time execute is " + String.valueOf(end.getTime() - start.getTime()) + " ms.");
            stmt.close();
            con.close();
        } catch (SQLException er) {
            System.out.println("SQL ERROR!");
            System.out.println(er.getMessage());
        }
    }
}
