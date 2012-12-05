/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.netcrackerteam.interview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author AlexK
 */
public class insert {    
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String userid = "root";
        String userp = "123";
        String query = new String("");
        String driverName = "com.mysql.jdbc.Driver";
        int k = 0;
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
            PreparedStatement pstmt;
            String field_1 = "f1value-";
            String field_2 = "f2value-";
            String field_3 = "f3value-";
            String field_4 = "f4value-";
            String field_5 = "f5value-";
            String field_6 = "f6value-";
            String field_7 = "f7value-";
            String field_8 = "f8value-";
            String field_9 = "f9value-";
            Date start = new Date();
            query = "insert into ncteam VALUES(?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            for (int i = 1; i <= 5000; i++) {
                pstmt.setString(1, i + "");
                pstmt.setString(2, field_1 + i);
                pstmt.setString(3, field_2 + i);
                pstmt.setString(4, field_3 + i);
                pstmt.setString(5, field_4 + i);
                pstmt.setString(6, field_5 + i);
                pstmt.setString(7, field_6 + i);
                pstmt.setString(8, field_7 + i);
                pstmt.setString(9, field_8 + i);
                pstmt.setString(10, field_9 + i);
                pstmt.execute();
            }
            Date end = new Date();
            System.out.println("Total time execute is " + String.valueOf(end.getTime() - start.getTime()) + " ms.");
            pstmt.close();
        } catch (SQLException er) {
            System.out.println("SQL ERROR!");
            System.out.println(er.getMessage());
        }
    }
}
