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
<h1>Hello User</h1>

<%! String driver = "com.mysql.jdbc.Driver";%>
<%! String url = "jdbc:mysql://localhost:3306/bankingsystem"; %>
<%! String uid = "root"; %>
<%! String psw = ""; %>
<%! String sql = "Select * from customer_registration"; %>


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
out.println("Sorry, No list found");
} 
else
{ 
%>

<table border="2px solid black;">
<tr>
  <th scope="col">CustID</th>
      <th scope="col">Name</th>
      <th scope="col">Details</th>
      
      
</tr>
<tr>
<%
int i = 0;
do{
	%>
	<tbody>
	 <tr>
	 <td><%= rs.getString("cust_id") %></td>
	 <td><%= rs.getString("name") %></td>
     <td><a href="viewUser.jsp?sendingid=<%=rs.getString("cust_id") %>">
          <button type="submit"  class="btn-btn-primary">View Details</button></a>
     </td>
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
catch(Exception e ){	
}
%>

</body>
</html>