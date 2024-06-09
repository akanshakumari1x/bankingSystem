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

<% String id = request.getParameter("sendingId");%>
<h1>Hello user <%= id %></h1>

<%! String driver = "com.mysql.cj.jdbc.Driver";%>
<%! String url = "jdbc:mysql://localhost:3306/bankingsystem"; %>
<%! String uid = "root"; %>
<%! String psw = ""; %>
<% String sql = "Select * from transaction_table where receiver_id = '" + id +"'" ; %>


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
<table border="2px solid black">
<tr>
  <th scope="col">SenderID</th>
      <th scope="col">ReceiverId</th>
      <th scope="col">Amount</th>
</tr>
<tr>
<%
int i = 0;
do{
	%>
	<tbody>
	 <tr>
	 <td><%= rs.getString("sender_id") %></td>
	 <td><%= rs.getString("receiver_id") %></td>
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

</body>
</html>