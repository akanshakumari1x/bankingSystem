<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<!-- 
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="index.php">Home</a>
  <a class="navbar-brand mr-30" href="register.php">logout</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
</nav> -->

<% String username = (String) session.getAttribute("userID"); %>
<input type="text" value="${username }"  >

<p>Username: ${username}</p>

<nav class="navbar navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand">Home</a>
    <a class="navbar-brand" href="logout.jsp">logout</a>
  </div>
</nav>


<br><br><br>
 <div class=" container-fluid"> 
       <div class="row">
          <div class="col-lg-6">
          
<a href="checkBalance.jsp?" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">check Balance</a>
<a href="#" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">Withdraw money</a>
<a href="#" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Transaction  history </a>
            </div>
            </div><br><br>
           <div> 
            <div class="col-lg-6">
          
<a href="#" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Transfer money </a>
<a href="#" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">User Profile</a>
<a href="#" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Request to close account</a>
            </div>
        </div> 
 </div>
 

</body>
</html>