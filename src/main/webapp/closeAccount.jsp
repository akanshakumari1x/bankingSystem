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
String id = request.getParameter("sendingId");
%>
  <form action="CloseAccount" method="post" class="mx-auto" style="max-width: 400px;">
     <input type="hidden" name="senderid" value="<%= id %>">
    <div class="form-group">
      <label for="username">Give reason to close account:</label>
      <input type="text" name="reason" class="form-control" placeholder="type here" required>
    </div>
    <div class="form-group text-center">
      <button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
    </div>
  </form>

</body>
</html>