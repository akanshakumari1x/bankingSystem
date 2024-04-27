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
<% String sql = "Select * from users where id = '" + id +"'" ;%>
<% String sql1 = "Select * from user_proofs where user_id = '" + id +"'" ;%>
<%
Connection con = null;
PreparedStatement ps;
ResultSet rs;



try{

Class.forName(driver);
con = DriverManager.getConnection(url,uid,pwd);
ps = con.prepareStatement(sql);
rs = ps.executeQuery();
 %>

<table border="2px solid black;">
<tr>
<th scope="col">ID</th>
    <th scope="col">Name</th>
    <th scope="col">FatherName</th>
    <th scope="col">MotherName</th> 
    <th scope="col">DOB</th>
    </tr>
   
<% 
if(!rs.next()) {
out.println("Sorry, No list found");
} 
else
{ 
%>
<tbody>
<tr>

	 <td><%= rs.getString("id") %></td>
	 <td><%= rs.getString("name") %></td>
	 <td><%= rs.getString("fatherName") %></td>
	 <td><%= rs.getString("motherName") %></td>
	 <td><%= rs.getString("DOB") %></td>
	 </tr>
	 </tbody>
	 </table><br><br>
	 
	 <%
}
}

catch(Exception e){
out.print(e);	
}

%>
<%
try{

Connection con1 = null;
PreparedStatement ps1;
ResultSet rs1;

Class.forName(driver);
con = DriverManager.getConnection(url,uid,pwd);
ps1 = con.prepareStatement(sql1);
rs1 = ps1.executeQuery();
 %>

<table border="2px solid black;">
<tr>
      <th scope="col">pan</th>
      <th scope="col">aadhar</th>

</tr> 
<% 
if(!rs1.next()) {
out.println("Sorry, No list found");
} 
else
{ 
%>

<%
String imagename = rs1.getString("doc_type");
String id11 = rs1.getString("id");
String path = id+"/"+ imagename;
String finalpath = "C:/Java/BankingSystem/images/" + path;
int i = 0;
do{
	%>
	<tbody>
	 <tr>
	 <td><%= rs1.getString("doc_type") %></td>
	 <td><%= rs1.getString("doc_name") %></td>
	 <img src="IMG-20200130-WA0119.jpg"  alt="Flowers in Chania" width="200px" height="305px">
	 
     <a href="accept.jsp?sendingid=<%= rs1.getString("user_id") %>" class="btn btn-primary">
        <button type="button" class="btn btn-primary">Accept</button>
    </a>
    <a href="reject.jsp?rejectId=<%= rs1.getString("user_id") %>" class="btn btn-primary">
        <button type="button" class="btn btn-primary">Reject</button>
    </a>
    
	 </tr>
	<%
	i++;
} while(rs1.next());
	

	%>
	</tbody>
</table><br><br>

<%
}
}
catch(Exception e){
out.print(e);	
}
%>





</body>
</html>