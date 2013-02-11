package ua.netcrackerteam.test;

import ua.netcrackerteam.controller.StudentData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class TimeSessionServlet extends HttpServlet {
    boolean flag = true;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        response.setContentType("text/html; charset=windows-1251");
        java.io.PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(10);
        Integer count = (Integer) session.getAttribute("count");
        if (count == null) {
            count = new Integer(1);
        } else {
            count = new Integer(count.intValue() + 1);
        }
        session.setAttribute("count", count);
        out.println("<html><head><title>Test</title></head>");
        out.println("<body><h1>Session Details</h1>");
        out.println("You've visited this page " + count + ((count.intValue()== 1) ? " time." : " times.") + "<br/>");
        out.println("<h3>Details of this session:</h3>");
        out.println("Session id: " + session.getId() + "<br/>");
        out.println("New session: " + session.isNew() + "<br/>");
        out.println("Timeout: " + session.getMaxInactiveInterval() + "<br/>");
        out.println("Creation time: " + new Date(session.getCreationTime()) + "<br/>");
        out.println("Last access time: " + new Date(session.getLastAccessedTime()) + "<br/>");

        out.println("<br>");

        out.println("<table border = 1>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<td>Name</td>");
        out.println("<td>Last Name</td>");
        out.println("</tr>");
        out.println("</thead>");
        StudentData studentPerson = new StudentData();
        /*try {
            Set<StudentData> studentPersonSet = studentPerson.setFIO();
            for (StudentData studenPersonIterator : studentPersonSet){
                out.println("<tbody>");
                out.println("<tr>");
                out.print("<td>");
                out.println(studenPersonIterator.getName());
                out.print("</td>");
                out.print("<td>");
                out.println(studenPersonIterator.getSurname());
                out.print("</td>");
                out.println("</tr>");
                out.println("</tbody>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        out.println("</table>");
        out.println("</body></html>");

        //TimeSession.go(request, response, session);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doGet(request, response);
    }

    /*private void performTask(HttpServletRequest req,HttpServletResponse resp) throws ServletException {
        HttpSession session = null;
        if (flag) {
//создание сессии и установка времени инвалидации
            MainPage mp = new MainPage();
            mp.init();
            session = req.getSession();
            int timeLive = 10; //десять секунд!
            session.setMaxInactiveInterval(timeLive);
            flag = false;
        } else {
//если сессия не существует, то ссылка на нее не будет получена
            session = req.getSession(false);
        }
        //TimeSession.go(resp, req, session);
    }*/
}