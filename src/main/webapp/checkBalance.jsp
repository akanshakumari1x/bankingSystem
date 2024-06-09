<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<% String id = request.getParameter("sendingId"); %>

<h3>Hi <%=id %>, check balance.</h3>

<%! String driver = "com.mysql.cj.jdbc.Driver";%>
<%! String url = "jdbc:mysql://localhost:3306/bankingsystem"; %>
<%! String uid = "root"; %>
<%! String psw = ""; %>
<%  String sql = "Select * from user_wallet where user_id = '" + id +"'" ;%>

<%
Connection con = null;
PreparedStatement ps;
ResultSet rs ;

try{

Class.forName(driver);
con = DriverManager.getConnection(url,uid,psw);
ps = con.prepareStatement(sql);
rs = ps.executeQuery();


if(!rs.next()) {
out.println("Not any application is under review!!");
} 
else
{ 
%>

<table border="2px solid black;">
<tr>
  <th scope="col">Wallet ID</th>
      <th scope="col">User ID</th>
      <th scope="col">Amount</th>
</tr>
<tr>
<%
int i = 0;
do{
	%>
	<tbody>
	 <tr>
	 <td><%= rs.getString("wallet_id") %></td>
	 <td><%= rs.getString("user_id") %></td>
	 <td><%= rs.getString("amount") %></td>
	 </tr>
	<%
	i++;
} while(rs.next());
	
	%>
	</tbody>
</table>
<%	
}
}
catch(Exception e){	
}
%>
<br>
<a href ="home.jsp">
<button type="submit" class="btn btn-primary">back to home</button>
</a>
</body>
</html>