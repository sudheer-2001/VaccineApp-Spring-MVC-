<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/Register.css">
<title>Registration form</title>
<style>
	body {
	display: flex;
	flex-direction: column;
	background: #000;
	margin: 0;
	min-height: 100vh;	
	background-image: url('resources/images/register.jpg');
	background-size: cover;
}
</style>
<script>
	function validate() {
		const uname = document.getElementById("uname").value;
		const email = document.getElementById("email").value;
		const password = document.getElementById("password").value;
		const confirmPassword = document.getElementById("confirmPassword").value;
		const phone = document.getElementById("phone").value;
		const gender = document.querySelector('input[name="gender"]:checked');
		const dob = document.getElementById("dob");
		const usernameRegex = /^[a-zA-Z]{3,15}$/;
		const phoneRegex = /^[6-9]{1}[0-9]{9}$/;

		if (uname === "") {
			alert("Please enter a valid username (3-15 alphabet characters).");
			return false;
		} else if (!usernameRegex.test(uname)) {
			alert("Please enter a valid username (3-15 alphanumeric characters).");
			return false;
		} else if (!/(?=.*[a-z])/.test(uname)) {
			alert("Please enter at least one lowercase alphabet in the username.");
			return false;
		} else if (!/(?=.*[A-Z])/.test(uname)) {
			alert("Please choose at least one uppercase alphabet in the username.");
			return false;
		} 

		if (email === "") {
			alert("Please enter valid Email...");
			return false;
		}

		if (password.length < 8) {
			alert("Password should be at least 8 characters.");
			return false;
		} else if (!/(?=.*[a-z])/.test(password)) {
			alert("Please enter at least one lowercase alphabet in the password.");
			return false;
		} else if (!/(?=.*[A-Z])/.test(password)) {
			alert("Please choose at least one uppercase alphabet in the password.");
			return false;
		} else if (!/(?=.*\d)/.test(password)) {
			alert("Please maintain at least one digit in the password.");
			return false;
		}

		if (password !== confirmPassword) {
			alert("Passwords do not match.");
			return false;
		}

		if (!phoneRegex.test(phone)) {
			alert("Please enter a valid 10-digit phone number starting with 6-9.");
			return false;
		}

		if (!gender) {
			alert("Please select a gender.");
			return false;
		}

		if (!dob.value) {
			alert("Please select a date of birth.");
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	%>
	<header id="header">
		<button id="home">
			<a href="getIndexPage">HomePage</a>
		</button>
	</header>
	<div id="div">
		<div class="wrapper">
			<form onsubmit="return validate()" action="saveUserData">
				<h1>
					Registration
					<!--&#x270D;-->
				</h1>
				<div class="input-box">
					<div class="input-field">
						<input id="uname" type="text" name="userName"
							placeholder="Username"> <i>&#128378;</i>
					</div>
					<div class="input-field">
						<input id="email" type="email" name="email" placeholder="Email">
						<i>&#128386;</i>
					</div>
				</div>
				<div class="input-box">
					<div class="input-field">
						<input id="password" type="password" name="password"
							placeholder="Password"> <i>&#xe033;</i>

					</div>
					<div class="input-field">
						<input id="confirmPassword" type="password" name="confirmPassword"
							placeholder="Confirm Password"> <i>&#xe033;</i>
					</div>
				</div>
				<div class="input-box">
					<div class="input-field">
						<input id="phone" type="tel" name="mobileNumber"
							placeholder="Mobile Number"> <i>&#x260F;</i>
					</div>
					<div class="input-field">
						<input type="date" name="dob">
					</div>
				</div>
				<div class="input-box">
					<div class="input-field remember">
						<label><input type="checkbox">I hereby declare
							that the above information provided is true</label>
					</div>
					<div class="gender">
						&nbsp;&nbsp;<span>Gender:</span> <input id="dob" type="radio"
							name="gender" value="M"><label>Male</label> <input
							id="dob" type="radio" name="gender" value="F"><label>Female</label>
						<input id="dob" type="radio" name="gender" value="others"><label>Others</label>
					</div>
				</div>

				<button type="submit" class="btn">Register</button>
				<div class="login-link">
					<p>
						Already have an account? <a href="userLogin">Login </a>
					</p>
				</div>
			</form>
			<span id="span1"></span>
			<span id="span2"></span>
			<span id="span3"></span>
			<span id="span4"></span>
		</div>
	</div>
	<div id="result">
		<h1>${result}</h1>
	</div>
	<footer id="footer"></footer>
</body>
</html>