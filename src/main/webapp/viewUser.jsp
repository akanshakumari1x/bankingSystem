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


<% String id = request.getParameter("sendingid");%>


<%! String driver = "com.mysql.cj.jdbc.Driver";%>
<%! String url = "jdbc:mysql://localhost:3306/bankingsystem"; %>
<%! String uid="root";  %>
<%! String pwd =""; %>
<% String sql = "Select * from users inner join user_proofs on users.id = user_proofs.user_id  where user_proofs.user_id = '" + id +"'" ;%>
<%
Connection con = null;
PreparedStatement ps;
ResultSet rs;

try{

Class.forName(driver);
con = DriverManager.getConnection(url,uid,pwd);
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
  <th scope="col">ID</th>
      <th scope="col">Name</th>
      <th scope="col">FatherName</th>
      <th scope="col">MotherName</th> 
      <th scope="col">DOB</th>
      <th scope="col">mobileNo</th>
      <th scope="col">Current Address</th>
      <th scope="col">Permanent Address</th>
      <th scope="col">Pan</th>
      <th scope="col">Aadhar</th>
      <th scope="col">Photos</th>
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
	 <td><%= rs.getString("fatherName") %></td>
	 <td><%= rs.getString("motherName") %></td>
	 <td><%= rs.getString("DOB") %></td>
	 <td><%= rs.getString("mobileNo") %></td>
	 <td><%= rs.getString("currentAddress") %></td>
	 <td><%= rs.getString("permanentAddress") %></td>
	 <td><%= rs.getString("doc_type") %></td>
	 <td><%= rs.getString("doc_name") %></td>
	 <td><a href="viewUser.jsp?sendingid=<%=rs.getString("id")%>"><button type="submit" class="btn btn-primary">View Details </button></a></td>
	 </tr>
	<%
	i++;
} while(rs.next());
	
	%>
	</tbody>
</table>

<!-- 	<td><a href="showAllUsers.jsp"><button type="submit" class="btn btn-primary">Accept </button></a></td> -->
<%
}
}
catch(Exception e){
out.print(e);	
}

%>







</body>
</html>