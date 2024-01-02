<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Colorful Vaccination Drive</title>
<link rel="stylesheet" href="resources/index.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" href="resources/css/index.css">
<!-- Add Font Awesome for icons -->
</head>
<body>
	<header id="header">
		<img src="resources/images/Tap.png" alt="Logo" class="academy-image">
		<div id="headDiv">
			<a href="getRegisterPage">Register</a> <a href="userLogin">Login</a>
		</div>
	</header>

	<marquee class="scroll">
		<img id="scrollImg" src="resources/images/Tap.png" alt="Tap Academy"
			style="height: 60px; width: 100px;">'s Vaccination Drive
		Portal
	</marquee>
	<p>
		To see the Statistic of Covid - 19 <a
			href="https://www.who.int/health-topics/coronavirus#tab=tab_1"
			target="_blank">click here</a>
	</p>
	<div id="div">
		<h1>${resetPasswordResponse}</h1>
	</div>

	<div class="container">
		<h1>
			<span class="TA">TAP ACADEMY</span> prioritizes health & safety with
			a <span class="highlight">colorful vaccination drive!</span>
		</h1>
		<div class="highlights-bar">
			<ul>
				<li><i class="fa fa-syringe"></i> On-site vaccination camp</li>
				<li><i class="fa fa-hospital-o"></i> Partnership with
					healthcare authorities</li>
				<li><i class="fa fa-heart"></i> Protecting our TAP ACADEMY
					community</li>
			</ul>
		</div>
		<div class="action-box">
			<h2>Join the fight! Register today!</h2>
			<p>Make a proactive step towards safeguarding your health and the
				well-being of those around you.</p>
			<a href="getRegisterPage" class="button primary">Register Now</a>
		</div>
		<div class="leader-quotes">
			<img src="resources/images/somanna.jpeg" alt="Somanna MG"
				class="leader-image">
			<p class="quote">
				"Together, we can create a brighter and healthier future for all." -
				<strong style="color: navy;">Somanna MG</strong>, Director
			</p>
			<img src="resources/images/rohit_ravinder.jpeg" alt="Rohit Ravinder"
				class="leader-image">
			<p class="quote">
				"This initiative is a step towards fostering a resilient and caring
				community." - <strong style="color: navy;">Rohit Ravinder</strong>,
				CEO
			</p>
		</div>
	</div>


	<footer id="footer"></footer>
</body>
</html>
