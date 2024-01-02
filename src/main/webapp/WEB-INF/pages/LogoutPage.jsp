<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<style type="text/css">
			body {
				background-color: #fff;
				margin: 0;
				text-align:center;
			    color: tomato;
				min-height: 100vh;
				display: flex;
				flex-direction: column;
			}
			header {
				margin-bottom: auto;
				background-color: #fff;
				min-height: 70px;
				padding: 5px;
			    display: flex;
			    border-bottom: 2px solid green;
			    font-size: 32px;
			    font-weight: bolder;
			    color: green;
			    text-align: center;
			    justify-content: center;
			    align-items: center;
			}
			footer {
				margin-top: auto;
				background-color: #578f70;
				min-height: 40px;
			}
			button {
		      padding: 8px 16px;
		      background-color: #2b446e;
		      color: white;
		      border: none;
		      border-radius: 4px;
		      cursor: pointer;
		    }
		    button:hover {
		      background-color: #45a049;
		    }
		    .login-button {
		      order: -1;
		    }
		    .for-padding {
				padding-left: 5%;
			    padding-right: 5%;
			}
			aside>h2{
				font-family: sans-serif;
				color: #0a1321;
				font-size: 50px;
			} 
			aside>p{
				position: relative;
				bottom: 16px;	
				color: #0a0f17;
			}
		</style>
</head>
<body>
	<header>
		&#x1F637; VaccineApp
	</header>
	<aside>
		<h2>Logged Out</h2>
		<p>Thank you for using VaccineApp</p>
		<a href="userLogin" class="login-button"><button>Sign In Again</button></a>
	</aside>
	<footer>
		<div class=for-padding></div>
	</footer>
</body>
</html>