/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.netcrackerteam.interview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author AlexK
 */
public class delete {
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
            Statement stm = con.createStatement();
            Date start = new Date();
            for (int i = 1; i <= 5000; i++) {
                query = "delete from ncteam where team_id = "+i+"";
                stm.execute(query);
            }
            Date end = new Date();
            System.out.println("Total time execute is " + String.valueOf(end.getTime() - start.getTime()) + " ms.");
            stm.close();
        } catch (SQLException er) {
            System.out.println("SQL ERROR!");
            System.out.println(er.getMessage());
        }
    }
}
