<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String id = request.getParameter("sendingid");
%>

<form action="Accept" method="post">
<input type="hidden" value=<%= id %> name="sendingid">
<h1>Are you sure you want to accept this application.</h1>
<button type="submit"> Yes</button>
</form>





</body>
</html>