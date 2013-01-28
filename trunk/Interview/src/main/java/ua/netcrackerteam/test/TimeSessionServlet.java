package ua.netcrackerteam.test;

import ua.netcrackerteam.GUI.MainPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;

public class TimeSessionServlet extends HttpServlet {
    boolean flag = true;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Date creationTime = new Date(session.getCreationTime());
        Date lastAccessed = new Date(session.getLastAccessedTime());
        Date now = new Date();
        DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Displaying the Session Creation and Last-Accessed Time</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Session Creation and Last-Accessed Time</h2>");
        out.println("The time and date now is: " + formatter.format(now) + "<br><br>");
        out.println("The session creation time: HttpSession.getCreationTime( ): " + formatter.format(creationTime) + "<br><br>");
        out.println("The last time the session was accessed: HttpSession.getLastAccessedTime( ): " + formatter.format(lastAccessed));
        out.println("</body>");
        out.println("</html>");
        session.setMaxInactiveInterval(10);
        Integer count = (Integer) session.getAttribute("count");

        if (count == null) {
            count = new Integer(1);
        } else {
            count = new Integer(count.intValue() + 1);
        }

        session.setAttribute("count", count);
        out.println("<html><head><title>SessionSnoop</title></head>");
        out.println("<body><h1>Session Details</h1>");
        out.println("You've visited this page " + count + ((count.intValue()== 1) ? " time." : " times.") + "<br/>");
        out.println("<h3>Details of this session:</h3>");
        out.println("Session id: " + session.getId() + "<br/>");
        out.println("New session: " + session.isNew() + "<br/>");
        out.println("Timeout: " + session.getMaxInactiveInterval() + "<br/>");
        out.println("Creation time: " + new Date(session.getCreationTime()) + "<br/>");
        out.println("Last access time: " + new Date(session.getLastAccessedTime()) + "<br/>");
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