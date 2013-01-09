<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="ua.netcrackerteam.*" %>
<head >
    <title>DB Connection</title>
</head>
<center>Test Table !!!!! and one more !</center>
<form method="post" name="form">
    <center><input type="button" value="Back to Index Page" onclick="location.href='index.jsp'" /></center>
    <table border="1" align="center" background-color="090341" >
        <thead>
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Surname</td>
            <td>Name RU </td>
        </tr>
        </thead>
        <tbody>
        <%
            List list = DBConnect.GetEmp();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                out.print("<tr>");
                for (int i = 0; i < 4; i++) {
                    out.print("<td>");
                    out.print(it.next());
                    out.print("</td>");
                }
                out.print("</tr>");
            }
        %>
        </tbody>
    </table>
    
    
    <center>Table using class DBConnecitonSingleton</center>
    <table border="1" align="center" background-color="090341" >
        <thead>
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Surname</td>
            <td>Name RU </td>
        </tr>
        </thead>
        <tbody>
        <%
            List list1 = DBConnect.GetEmp();
            Iterator<String> it1 = list1.iterator();
            while (it1.hasNext()) {
                out.print("<tr>");
                for (int i = 0; i < 4; i++) {
                    out.print("<td>");
                    out.print(it1.next());
                    out.print("</td>");
                }
                out.print("</tr>");
            }
        %>
        </tbody>
    </table>
</form>