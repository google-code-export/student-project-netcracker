package ua.netcrackerteam.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException {
            performTask(req, resp);
    }

    private void performTask(HttpServletRequest req,HttpServletResponse resp) throws ServletException {
        SessionLogic.printToBrowser(resp, req);
    }
}