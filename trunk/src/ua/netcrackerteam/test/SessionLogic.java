package ua.netcrackerteam.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionLogic {
    public static void printToBrowser(HttpServletResponse resp, HttpServletRequest req) {
        try {
/* возвращается ссылка на сессию для текущего пользователя (если сессия еще не существует, то она при этом создается) */
            HttpSession session = req.getSession(true);
            PrintWriter out = resp.getWriter();
            StringBuffer url = req.getRequestURL();
            session.setAttribute("URL", url);
            out.write("My session counter: ");
/* количество запросов, которые были сделаны к данному сервлету текущим пользователем в
рамках текущей пользовательской сессии (следует приводить значение к строковому виду для корректного
отображения в результате) */
            out.write(String.valueOf(prepareSessionCounter(session)));
            out.write("<br> Creation Time : " + new Date(session.getCreationTime()));
            out.write("<br> Time of last access : " + new Date(session.getLastAccessedTime()));
            out.write("<br> session ID : " + session.getId());
            out.write("<br> Your URL: " + url);
            int timeLive = 60 * 30;
            session.setMaxInactiveInterval(timeLive);
            out.write("<br>Set max inactive interval : " + timeLive + "sec");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed : " + e);
        }
    }

/* увеличивает счетчик обращений к текущему сервлету и кладет его в сессию */
    private static int prepareSessionCounter(HttpSession session) {
        Integer counter = (Integer)session.getAttribute("counter");
        if (counter == null) {
            session.setAttribute("counter", 1);
            return 1;
        } else {
            counter++;
            session.setAttribute("counter", counter);
            return counter;
        }
    }
}