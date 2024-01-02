<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login Page</title>
<link rel="stylesheet" href="resources/css/Login.css">
<style>
		body{
		 	display: flex;
			flex-direction: column;
			min-height: 100vh;
		 	align-items: center;
		 	justify-content: center;
		 	background-image: url('resources/images/login.jpg');
		 	background-size: cover;
		 	background-position: center; 
  			background-repeat: no-repeat;
		 }
		 header {
			margin-bottom: auto;
			text-align:center;
			background: rgba(0, 0, 0, 0.3);
			height: 50px;
			width: 100%;
            		padding-right: 20px;
		}
		footer {
			margin-top: auto;
	            	height: 50px;
	            	bottom: 0;
	            	width: 100%;
	            	background: rgba(0, 0, 0, 0.3);
	            	color: #fff;
	            	text-align: center;
		}
</style>
</head>
<body>
	<header>
		<div class=for-padding>
			</div>
	</header>
	<p>${result}</p>
	<form class="form" action="login" method="post">
      <p class="login">Log in to VaccineApp</p>
      <div class="inputContainer">
      	<input placeholder="Enter your email@ddress" type="text" class="fInput email" name="mail">
        <input placeholder="Enter your password" type="Password" class="fInput pass" name="password">
        <div class="button-container">
    		<input type="submit" value="Login" class="submit">
    		<span class="span5"></span>
    		<span class="span6"></span>
    		<span class="span7"></span>
    		<span class="span8"></span>
		</div>

      </div>
      <a href="resetPassword" class="forget">Forget password?</a>
      <div class="con">
        <p>Don't have account?&nbsp;</p>
        <a href="getRegisterPage"> Register</a>
      </div>
      <span id="span1"></span>
      <span id="span2"></span>
      <span id="span3"></span>
      <span id="span4"></span>
    </form>
    <footer>
    	<div class=for-padding>
			</div>
    </footer>
</body>
</html>
