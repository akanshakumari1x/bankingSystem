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
String id = request.getParameter("rejectId");
%>

<form action="Reject" method="post">
<input type="hidden" value=<%= id %> name="rejectId">
<h1>kindly provide reason to reject this application.</h1>
 <textarea name="reason" rows="4" cols="30" required ></textarea><br>
<button type="submit"> Submit </button>
</form>



</body>
</html>