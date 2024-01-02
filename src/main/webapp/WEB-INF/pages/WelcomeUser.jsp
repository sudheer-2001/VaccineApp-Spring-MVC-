<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="expires" content="0">
<meta http-equiv="pragma" content="no-cache">
<title>User</title>
<link rel="stylesheet" href="resources/css/WelcomeUser.css">
<script>
	function go() {
		window.location
				.replace(
						"LogoutPage.jsp",
						'window',
						'toolbar=1,location=1,directories=1,status=1,menubar=1,scrollbars=1,resizable=1');
		self.close()
	}
</script>
</head>
<body>
	<header id="header">
		<a href="getAddMemberPage" class="addmember-button"><button>Add
				Member</button></a>
		<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0
		response.setHeader("Expires", "0"); //Proxies
		%>
		<div class="for-right">
			
			<%
			if (session.getAttribute("userEmail") == null || session.getAttribute("userEmail").equals("")) {
			%>
			<a href="userLogin"><b><button type="button"
						class="btn-logout">Login</button></b></a>
			<%		
			} else {
			%>
			<b>Hi, ${userEmail} </b>
			<a href="logout"><button type="button" class="logout-button"
					onclick="go()">Logout</button></a>
			<%
			}
			%>
		</div>
	</header>
	<marquee behavior="scroll">Get your &#x1F489; vaccination to protect against &#x1F9A0;!</marquee>
	<div id="div1">
		<h1>${welcome}</h1>
	</div>
	<div class="card-container">
		<div class="card">
			<div id="free">
				<img class="img1" alt="Image" src="resources/images/family.jpg">
			</div>
			<div class="card-content">
				<h3>Registration count</h3>
				<div class="heading">
					${memCount}
					<div class="author">
						<span class="name"> Family members</span>
					</div>
				</div>
			</div>
		</div>
		<div class="card">
			<div id="free">
				<img class="img2" alt="Image" src="resources/images/employee.png">
			</div>
			<div class="card-content">
				<h3>Employee details</h3>
				<div class="heading">${userName}
					<div class="author">
						<span class="name"> Full Stack Developer</span>
					</div>
				</div>
			</div>
		</div>
		<div class="card">
			<div id="free">
				<img class="img3" alt="Image" src="resources/images/slogan.avif">
			</div>
			<div class="card-content">
				<h3 class="slogan">Slogan</h3>
				<div class="heading matter">
					Stay healthy and wash your hands often! &#x1F637; &#x1F489; &#x1F9A0;
					<div class="author1">
						 <span class="name">By Tap Academy!</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer id="footer"></footer>
</body>

</html>