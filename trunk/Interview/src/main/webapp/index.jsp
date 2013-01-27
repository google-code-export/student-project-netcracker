<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="ua.netcrackerteam.DAO.*" %>
<%@ page import="ua.netcrackerteam.configuration.HibernateFactory" %>
<head >
    <title>DB Connection</title>
</head>
<center>Test Table !!!!! and one more !!!</center>
<form method="post" name="form">
    <center><input type="button" value="Back to Index Page" onclick="location.href='index.jsp'" /></center>
    <table border="1" align="center" background-color="090341" >
        <thead>
        <tr>
        <tr>
            <td>Name</td>
            <td>Middle Name</td>
            <td>Last Name</td>
            <td>Contact Category</td>
            <td>Contact</td>
        </tr>
        </thead>
        <tbody>
        <%
            List<Object[]> listOfForms = HibernateFactory.getInstance().getFormDAO().GetNamesAndContacts();
            for (Object[] currForm : listOfForms) {
                TableContact contactObjects = (TableContact) currForm[0];
                TableContactCategory contactCategoryObjects = (TableContactCategory) currForm[1];
                TableForm formObjects = (TableForm) currForm[2];
        %>
                <tr>
                    <td><%=formObjects.getFirstName()%></td>
                    <td><%=formObjects.getMiddleName()%></td>
                    <td><%=formObjects.getLastName()%></td>
                    <td><%=contactCategoryObjects.getCategory()%></td>
                    <td><%=contactObjects.getInfo()%></td>
                </tr>
        <%
                }
        %>
        </tbody>
    </table>
</form>