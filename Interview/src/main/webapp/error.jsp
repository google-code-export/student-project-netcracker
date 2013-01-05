<%@ page isErrorPage="true" import="java.io.*" contentType="text/html"%>
<head >
    <title>Error page</title>
</head>
<html>
<body bgcolor="#e6e6fa">
<center>
<input type="button" value="Back to Index Page" onclick="location.href='index.jsp'" />
</center>
<br>
<p><h2 style="color: #00008b">Exception Message:
<p><h3 style="color: #dc143c"><%=exception.getMessage()%>
<br>
<p><h2 style="color: #00008b">StackTrace:</h2>
<p><h3 style="color: #dc143c">
<%
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	exception.printStackTrace(printWriter);
	out.println(stringWriter);
	printWriter.close();
	stringWriter.close();
	System.out.println("");
%>
</body>
</html>
