<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Hello transaction</h1>

<% String id = request.getParameter("sendingId");%>
<h1>Hello user <%= id %></h1>

<%! String driver = "com.mysql.cj.jdbc.Driver";%>
<%! String url = "jdbc:mysql://localhost:3306/bankingsystem"; %>
<%! String uid = "root"; %>
<%! String psw = ""; %>
<% String sql = "Select * from user_wallet where id = '" + id +"'" ; %>

<h1>HEloo </h1>
</body>
</html>