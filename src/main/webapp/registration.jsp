<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
						
						<form action="Registration" method="post" enctype="multipart/form-data">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" />
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="fathername" id="name" placeholder="Your Fathers Name" />
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="mothername" id="name" placeholder="Your Mothers Name" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="date" name="dob" id="pass" placeholder="DOB" />
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="contact" id="contact"
									placeholder="Contact no" />
							</div>
							
							<div class="form-group">
							    <label for="password" ><i
									class="zmdi zmdi-account material-icons-name"></i></label>
							    <input type="password" name="pwd" id="password" placeholder="enter your password">
							</div>
							
							<div class="form-group">
							    <label for="password" ><i
									class="zmdi zmdi-account material-icons-name"></i></label>
							    <input type="password" name="confirmpwd" id="password" placeholder="enter your password">
							</div>
							
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="address" id="contact"
									placeholder="Current Address" />
							</div>
							
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="peraddr" id="contact"
									placeholder="Permanent Address" />
							</div>
							<!-- image -->
					         <div class="form-group">
					                <label for="img" class="form-label">Upload Pan </label><br><br>
					               <input type="file" accept="image/*" class="form-control" name="pan">
					         </div>
					         
							<!-- image -->
					         <div class="form-group">
					                <label for="img" class="form-label">Upload Aadhar </label><br><br>
					               <input type="file" accept="image/*" class="form-control" name="aadhar">
					         </div>
					         
					         <div class="form-group">
					                <label for="img" class="form-label">Upload Photo </label><br><br>
					               <input type="file" accept="image/*" class="form-control" name="photo">
					         </div>
					             
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="login.jsp" class="signup-image-link">I am already
							member</a>
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
</html>