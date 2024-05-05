<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Transfer money</h1>

<% String id = request.getParameter("sendingId");%>
<h1>HI  <%= id %></h1>
<form action="TransferMoney" method="post" name="myform" onsubmit="return valiData();">
<input type="hidden" name="senderid" value="<%= id %>">
<input type="text" placeholder="enter phone number" name="mobile" required>
<input type="text" placeholder="enter amount" name="amount" id="amount" required><br><br>
<button type="submit">Submit</button>

</form>

<script>
function valiData(){
	
 var amount = document.getElementById("amount").value;
 var val = 0;
 
 if(val<amount){
	 alert("sending money");
	 return true;
 }else if(val==amount){
	alert("amount should be greater than zero"); 
	return false;
 }
 alert("kindly enter positive number");
 return false;
}

</script>

</body>
</html>