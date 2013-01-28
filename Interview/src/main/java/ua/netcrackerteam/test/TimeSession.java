package ua.netcrackerteam.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TimeSession {

    public static void go(HttpServletResponse resp, HttpServletRequest req, HttpSession session ) {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.write("<br> Creation Time : " + new Date(session.getCreationTime()));
            out.write("<br> Session alive! ");
            out.flush();
            out.close();
        } catch (NullPointerException e) {
//если сессия не существует, то генерируется исключение
            if (out != null)
                out.print("Session disabled!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("i/o failed: " + e);
        }
    }

    public static void go(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    }
}