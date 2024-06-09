<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
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
<%! String url = "jdbc:mysql://localhost:3306/bankingsystem";%>
<%! String uid ="root"; %>
<%! String psw=""; %>
<%String sql = "Select * from users where id = '"+ id +"'" ;%>

<%
Connection con= null;
PreparedStatement ps;
ResultSet rs;

try{
	Class.forName(driver);
	con= DriverManager.getConnection(url,uid,psw);
	ps= con.prepareStatement(sql);
	rs = ps.executeQuery();
	
	
	
	if(!rs.next()){
		out.println("not found any result");
	}
	else{
	%>
	
	<table border="2px solid black;">
	<tr>
	  <th scope="col">id</th>
	  <th scope="col">name</th>
	  <th scope="col">fatherName</th>
	  <th scope="col">MotherName</th>
	  <th scope="col">DOB</th>
	  <th scope="col">mobileNo</th>
	  <th scope="col">password</th>
	  <th scope="col">currentAddress</th>
	  <th scope="col">permanentAddress</th>
	     
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
		     <td><%= rs.getString("password") %></td>
		     <td><%= rs.getString("currentAddress") %></td>
		     <td><%= rs.getString("permanentAddress") %></td>    
		     
		  </tr>
		  <%
		  i++;
	}
	while(rs.next());
		  %>
		
		</tbody>
		</table>
<% 
	}
}
catch(Exception e){
	}
	%><br>
	
<a href ="home.jsp">
<button type="submit" class="btn btn-primary">back to home</button>
</a>

</body>
</html>