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

<%! String driver = "com.mysql.cj.jdbc.Driver";%>
<%! String url = "jdbc:mysql://localhost:3306/bankingsystem"; %>
<%! String uid = "root"; %>
<%! String psw = ""; %>
<%! String sql = "Select * from users inner join application_status  on users.id = application_status.user_id  and application_status='onReview'"; %>

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
  <th scope="col">ID</th>
      <th scope="col">Name</th>
      <th scope="col">View User Details</th>
</tr>
<tr>
<%
int i = 0;
do{
	%>
	<tbody>
	 <tr>
	 <td><%= rs.getString("id") %></td>
	 <td><%= rs.getString("name") %></td>
	 <td><a href="test.jsp?sendingid=<%=rs.getString("id")%>"><button type="submit" class="btn btn-primary">View Details </button></a></td>
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